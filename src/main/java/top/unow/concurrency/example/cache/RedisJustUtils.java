package top.unow.concurrency.example.cache;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.concurrency.example.cache
 *  @文件名:   RedisUtils
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-04-14 10:37
 *  @描述：    TODO
 */
@Component
public class RedisJustUtils {
    @Resource(name = "redisPool")
    private JedisPool jedisPool;

    public void set(String key, String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }finally {
            if(jedis != null)
                jedis.close();
        }

    }

    public String get(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }finally {
            if(jedis != null)
                jedis.close();
        }
    }
}
