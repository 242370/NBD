package org.nbd.repos;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lombok.Getter;
import org.nbd.model.Accommodation;
import org.nbd.model.CashedAccommodation;
import org.nbd.model.RedisInit;
import redis.clients.jedis.exceptions.JedisConnectionException;

@Getter
public class AccommodationRepoRedis extends AccommodationRepo {
    private final AccommodationRepo repo = new AccommodationRepo();
    private final RedisInit redisInit;
    private final Jsonb jsonb;
    private final int expirationTime;
    private final String prefix = "Accommodation:";

    public AccommodationRepoRedis(int expirationTime) {
        redisInit = new RedisInit();
        jsonb = JsonbBuilder.create();
        this.expirationTime = expirationTime;
    }

    public void putInCache(CashedAccommodation accommodation) {
        try {
            redisInit.getJedisPooled().jsonSet(this.prefix + accommodation.getId(), this.jsonb.toJson(accommodation));
            redisInit.getJedisPooled().expire(this.prefix + accommodation.getId(), this.expirationTime);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public CashedAccommodation getFromCache(int id) throws Exception {
        Object json = redisInit.getJedisPooled().jsonGet(this.prefix + id);

        if (json == null) {
            throw new Exception("No such object in cache");
        }

        return jsonb.fromJson(jsonb.toJson(json), CashedAccommodation.class);
    }

    public void deleteFromCache(int id) {
        try {
            redisInit.getJedisPooled().jsonDel(this.prefix + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public CashedAccommodation convertToRedisObject(Accommodation hotel) {
        return new CashedAccommodation(hotel.getId(), hotel.getCapacity(), hotel.getPricePerPerson(), hotel.getRating(), hotel.getDestination());
    }

    public Accommodation convertToObjectRedis(CashedAccommodation hotel) {
        return new Accommodation(hotel.getId(), hotel.getCapacity(), hotel.getPricePerPerson(), hotel.getRating(), hotel.getDestination());
    }

    @Override
    public void add(Accommodation hotel) {
        super.add(hotel);
        this.putInCache(this.convertToRedisObject(hotel));
    }

    @Override
    public Accommodation getByID(int id) {
        Accommodation accommodation = null;
        try {
            accommodation = this.convertToObjectRedis(this.getFromCache(id));
        } catch (JedisConnectionException j_e) {
            System.out.println("Can't access cache, getting from database");
            accommodation = super.getByID(id);
        } catch (Exception e) {
            this.putInCache(this.convertToRedisObject(super.getByID(id)));
            try {
                accommodation = this.convertToObjectRedis(this.getFromCache(id));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return accommodation;
    }

    @Override
    public void remove(int id) {
        super.remove(id);
        this.deleteFromCache(id);
    }
}
