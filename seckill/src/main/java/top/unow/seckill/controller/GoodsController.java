package top.unow.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.unow.seckill.bean.User;
import top.unow.seckill.result.Result;
import top.unow.seckill.service.GoodsService;
import top.unow.seckill.vo.GoodsDetailVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.seckill.controller
 *  @文件名:   GoodsController
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-07 22:33
 *  @描述：    TODO
 */
@Controller
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @ResponseBody
    @RequestMapping(value = "/to_list", produces = "text/html")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, User user){
        String html = goodsService.goodsPage(request, response, model, user);
        return html;
    }

    @ResponseBody
    @RequestMapping(value = "/to_detail/{goodsId}", produces = "text/html")
    public String detail2(HttpServletRequest request, HttpServletResponse response, Model model, User user, @PathVariable("goodsId") long goodsId) {
        String html = goodsService.detailPage(request, response, model, user, goodsId);
        return html;
    }

    @RequestMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail(HttpServletRequest request, HttpServletResponse response, Model model, User user, @PathVariable("goodsId") long goodsId) {
        GoodsDetailVo vo = goodsService.getGoodsVoByGoodsId(request, response, model, user, goodsId);
        return Result.success(vo);
    }


}
