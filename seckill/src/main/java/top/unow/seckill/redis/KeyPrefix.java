package top.unow.seckill.redis;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.seckill.redis
 *  @文件名:   KeyPrefix
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-05 11:51
 *  @描述：    缓冲key前缀
 */
public interface KeyPrefix {
    /**
     * 有效期
     * @return
     */
    int expireSeconds();

    /**
     * 前缀
     * @return
     */
    String getPrefix();
}
