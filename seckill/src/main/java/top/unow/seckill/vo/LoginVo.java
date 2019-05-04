package top.unow.seckill.vo;

import top.unow.seckill.validator.IsMobile;

import javax.validation.constraints.NotNull;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.seckill.vo
 *  @文件名:   LoginVo
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-04 21:07
 *  @描述：    TODO
 */
public class LoginVo {

    @NotNull
    @IsMobile  //因为框架没有校验手机格式注解，所以自己定义
    private String mobile;

    @NotNull
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}