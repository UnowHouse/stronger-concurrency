package top.unow.seckill.bean;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.bean
 *  @文件名:   Goods
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-04 20:58
 *  @描述：    TODO
 */
public class Goods {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGoodsName() {
        return goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public String getGoodsTitle() {
        return goodsTitle;
    }
    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }
    public String getGoodsImg() {
        return goodsImg;
    }
    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }
    public String getGoodsDetail() {
        return goodsDetail;
    }
    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }
    public Double getGoodsPrice() {
        return goodsPrice;
    }
    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
    public Integer getGoodsStock() {
        return goodsStock;
    }
    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }
}