package top.unow.seckill.bean;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.bean
 *  @文件名:   SeckillOrder
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-04 20:59
 *  @描述：    TODO
 */
public class SeckillOrder {
    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
