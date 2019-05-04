package top.unow.seckill.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.seckill.util
 *  @文件名:   ValidatorUtil
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-04 21:11
 *  @描述：    登录校验工具类
 */
public class ValidatorUtil {
    //默认以1开头后面加10个数字为手机号
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src){
        if(StringUtils.isEmpty(src)){
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }
}
