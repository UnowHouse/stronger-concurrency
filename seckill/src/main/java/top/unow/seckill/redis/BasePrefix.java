package top.unow.seckill.redis;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.seckill.redis
 *  @文件名:   BasePrefix
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-05 11:50
 *  @描述：    TODO
 */
public class BasePrefix implements KeyPrefix {
    private int expireSeconds;

    private String prefix;

    public BasePrefix(String prefix){
        this(0, prefix);//默认0代表永不过期
    }

    public BasePrefix(int expireSeconds, String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();//拿到参数类类名
        return className + ":" + prefix;
    }
}
