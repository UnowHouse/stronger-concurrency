package top.unow.concurrency.example.cache.SeriesUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.concurrency.example.cache
 *  @文件名:   RedisStringUtils
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-04-14 10:56
 *  @描述：    TODO
 */
@Component
public class RedisStringUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * Set key and related value
     * @param key
     * @param value
     */
    public void set(String key, String value) {

        redisTemplate.opsForValue().set(key,value);

    }

    /**
     * gets the value of the appointed key
     * @param key
     * @return
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * return substring from the value of the appointed key
     * @param key
     * @param start
     * @param end
     * @return
     */
    public String getRange(String key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    /**
     * return the old value of appointed key, meanwhile update the key with other new value
     *
     * @param key
     * @param value
     * @return
     */
    public String getAndSet(String key, String value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * gets the bit from the value of key by appointed offset
     *
     * @param key
     * @param offset
     * @return
     */
    public Boolean getBit(String key, long offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    /**
     * multiple get
     *
     * @param keys
     * @return
     */
    public List<String> multiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * set ASCII code, transform the position bit to value from the parameter key, which the function of this method
     * the ASCII code of 'a' is 97, 01100001 it is if transform to binary
     * @param key
     * @param offset
     *            position
     * @param value
     *            true represent 1, false represent 0
     * @return
     */
    public boolean setBit(String key, long offset, boolean value) {
        return redisTemplate.opsForValue().setBit(key, offset, value);
    }

    /**
     * set key value, meanwhile set a deadline for it
     *
     * @param key
     * @param value
     * @param timeout
     * @param unit
     *            a day :TimeUnit.DAYS
     *            a hour:TimeUnit.HOURS
     *            a minute:TimeUnit.MINUTES
     *            a second:TimeUnit.SECONDS
     *            a millisecond:TimeUnit.MILLISECONDS
     */
    public void setEx(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * set key value when key isn't exist
     *
     * @param key
     * @param value
     * @return return false if exist before,return true if isn't exist before
     */
    public boolean setIfAbsent(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * over write the value of key by appointed position
     *
     * @param key
     * @param value
     * @param offset
     *
     */
    public void setRange(String key, String value, long offset) {
        redisTemplate.opsForValue().set(key, value, offset);
    }

    /**
     * get the length of the value of key
     *
     * @param key
     * @return
     */
    public Long size(String key) {
        return redisTemplate.opsForValue().size(key);
    }

    /**
     * setting key and value multiply
     *
     * @param maps
     */
    public void multiSet(Map<String, String> maps) {
        redisTemplate.opsForValue().multiSet(maps);
    }

    /**
     * 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在
     *
     * @param maps
     * @return 之前已经存在返回false,不存在返回true
     */
    public boolean multiSetIfAbsent(Map<String, String> maps) {
        return redisTemplate.opsForValue().multiSetIfAbsent(maps);
    }

    /**
     * set key auto increasing
     *
     * @param key
     * @param increment
     * @return
     */
    public Long incrBy(String key, long increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * set key auto increasing by float
     *
     * @param key
     * @param increment
     * @return
     */
    public Double incrByFloat(String key, double increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * append value in the end part of the value of key
     *
     * @param key
     * @param value
     * @return
     */
    public Integer append(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

}
