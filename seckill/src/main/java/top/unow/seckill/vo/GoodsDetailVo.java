package top.unow.seckill.vo;

import top.unow.seckill.bean.User;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.seckill.vo
 *  @文件名:   GoodsDetailVo
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-04 21:07
 *  @描述：    TODO
 */
public class GoodsDetailVo {
    private int seckillStatus = 0;
    private int remainSeconds = 0;
    private GoodsVo goods ;
    private User user;

    public int getSeckillStatus() {
        return seckillStatus;
    }

    public void setSeckillStatus(int seckillStatus) {
        this.seckillStatus = seckillStatus;
    }

    public int getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(int remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public GoodsVo getGoods() {
        return goods;
    }

    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}