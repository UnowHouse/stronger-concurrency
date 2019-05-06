package top.unow.seckill.util;

import java.util.UUID;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.seckill.util
 *  @文件名:   UUIDUtil
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-05 13:04
 *  @描述：    唯一id生成类
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }}
