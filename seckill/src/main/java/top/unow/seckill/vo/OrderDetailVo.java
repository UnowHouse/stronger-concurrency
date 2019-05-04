package top.unow.seckill.vo;

import top.unow.seckill.bean.OrderInfo;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.seckill.vo
 *  @文件名:   OrderDetailVo
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-04 21:08
 *  @描述：    TODO
 */
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo order;
    public GoodsVo getGoods() {
        return goods;
    }
    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }
    public OrderInfo getOrder() {
        return order;
    }
    public void setOrder(OrderInfo order) {
        this.order = order;
    }
}
