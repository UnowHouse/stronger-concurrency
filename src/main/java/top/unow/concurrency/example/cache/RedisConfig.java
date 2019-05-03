package top.unow.concurrency.example.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.concurrency.example.cache
 *  @文件名:   RedisConfig
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-04-14 10:37
 *  @描述：    TODO
 */
@Configuration
public class RedisConfig {

    @Bean(name = "redisPool")
    public JedisPool jedisPool(@Value("${jedis.host}") String host,
                               @Value("${jedis.port}") int port) {
        return new JedisPool(host, port);
    }
}
