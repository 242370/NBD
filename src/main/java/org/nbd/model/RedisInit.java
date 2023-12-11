package org.nbd.model;

import lombok.Getter;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class RedisInit {
    private JedisPooled jedisPooled;

    public RedisInit() {
        this.initConnection();
    }

    public void initConnection()
    {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("redis_connection_props")) {
            Properties props = new Properties();
            props.load(input);

            JedisClientConfig clientConfig = DefaultJedisClientConfig.builder().build();
            jedisPooled = new JedisPooled(new HostAndPort(props.getProperty("host"), Integer.parseInt(props.getProperty("port"))), clientConfig);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
