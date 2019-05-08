package top.unow.seckill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import top.unow.seckill.bean.OrderInfo;
import top.unow.seckill.mapper.GoodsMapper;
import top.unow.seckill.mapper.OrderMapper;
import top.unow.seckill.vo.GoodsVo;
import top.unow.seckill.vo.OrderDetailVo;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.seckill.service
 *  @文件名:   OrderService
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-08 23:04
 *  @描述：    TODO
 */
@Service
public class OrderService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    OrderMapper orderMapper;


    public OrderDetailVo getOrderInfo(Model model, long orderId) {

        OrderInfo order = orderMapper.getOrderById(orderId);
        if(order == null){
            return null;
        }
        Long goodsId = order.getGoodsId();
        GoodsVo goodsVo = goodsMapper.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setGoods(goodsVo);
        vo.setOrder(order);

        return vo;


    }
}
