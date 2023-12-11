package org.nbd.model;

import lombok.Getter;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;

@Getter
public class RedisInit {
    private JedisPooled jedisPooled;

    public RedisInit() {
        this.initConnection();
    }

    public void initConnection()
    {
        JedisClientConfig clientConfig = DefaultJedisClientConfig.builder().build();
        jedisPooled = new JedisPooled(new HostAndPort("localhost", 6379), clientConfig);
    }


}
