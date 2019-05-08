package top.unow.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.unow.seckill.bean.User;
import top.unow.seckill.result.CodeMsg;
import top.unow.seckill.result.Result;
import top.unow.seckill.service.OrderService;
import top.unow.seckill.vo.OrderDetailVo;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.seckill.controller
 *  @文件名:   OrderController
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-08 23:03
 *  @描述：    TODO
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, User user,
                                      @RequestParam("orderId") long orderId) {
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }

        OrderDetailVo orderInfo = orderService.getOrderInfo(model, orderId);
        if(orderInfo == null){
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        return Result.success(orderInfo);

    }


}
