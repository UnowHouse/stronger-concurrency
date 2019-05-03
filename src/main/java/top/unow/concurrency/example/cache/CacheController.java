package top.unow.concurrency.example.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.concurrency.example.cache
 *  @文件名:   CacheController
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-04-14 10:37
 *  @描述：    TODO
 */
@RestController
@RequestMapping("cache")
public class CacheController {

    @Autowired
    private RedisJustUtils redisJustUtils;

    @GetMapping("justSet")
    public String set(@RequestParam("key")String key, @RequestParam("value")String value){
        redisJustUtils.set(key,value);
        return "SUCCESS";
    }

    @GetMapping("hello")
    public String hello(){
        return  "HELLO";
    }

    @GetMapping("justGet")
    public String get(@RequestParam("key")String key){
        return redisJustUtils.get(key);

    }
}
