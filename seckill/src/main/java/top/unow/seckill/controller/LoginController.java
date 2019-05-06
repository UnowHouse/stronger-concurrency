package top.unow.seckill.controller;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.seckill.controller
 *  @文件名:   LoginController
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-05 14:39
 *  @描述：    TODO
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import top.unow.seckill.result.Result;
import top.unow.seckill.service.UserService;
import top.unow.seckill.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping("/to_login")
    public ModelAndView toLogin(Model model) {
        return new ModelAndView("login");
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {//加入JSR303参数校验
        log.info(loginVo.toString());
        String token = userService.login(response, loginVo);
        return Result.success(token);
    }
}
