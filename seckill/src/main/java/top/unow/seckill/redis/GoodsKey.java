package top.unow.seckill.redis;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.seckill.redis
 *  @文件名:   GoodsKey
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-07 22:49
 *  @描述：    TODO
 */
public class GoodsKey extends BasePrefix {

    private GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodsKey getGoodsList = new GoodsKey(60, "gl");
    public static GoodsKey getGoodsDetail = new GoodsKey(60, "gd");
    public static GoodsKey getGoodsStock = new GoodsKey(0, "gs");
}
