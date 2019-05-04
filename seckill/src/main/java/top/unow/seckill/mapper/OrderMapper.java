package top.unow.seckill.mapper;

import org.apache.ibatis.annotations.*;
import top.unow.seckill.bean.OrderInfo;
import top.unow.seckill.bean.SeckillOrder;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.seckill.mapper
 *  @文件名:   OrderMapper
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-04 21:05
 *  @描述：    TODO
 */
@Mapper
public interface OrderMapper {


    @Select("select * from sk_order where user_id = #{userId} and goods_id = #{goodsId}")
    public SeckillOrder getOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);


    /**
     * 通过@SelectKey使insert成功后返回主键id，也就是订单id
     * @param orderInfo
     * @return
     */
    @Insert("insert into sk_order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
    public long insert(OrderInfo orderInfo);


    @Insert("insert into sk_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    public int insertSeckillOrder(SeckillOrder order);

    @Select("select * from sk_order_info where id = #{orderId}")
    public OrderInfo getOrderById(@Param("orderId") long orderId);

}
