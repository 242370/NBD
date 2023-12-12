package org.nbd.repos;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.nbd.model.Accommodation;
import org.nbd.model.CashedAccommodation;
import org.nbd.model.RedisInit;

public class AccommodationRepoRedis {
    private RedisInit redisInit;
    private Jsonb jsonb;
    private int expirationTime;
    private String prefix = "Accommodation:";

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
}
