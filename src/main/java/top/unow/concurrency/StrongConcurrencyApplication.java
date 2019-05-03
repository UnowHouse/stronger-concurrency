package top.unow.concurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.concurrency
 *  @文件名:   StrongConcurrencyApplication
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-04-14 10:24
 *  @描述：    TODO
 */
@SpringBootApplication
public class StrongConcurrencyApplication {

    public static void main(String [] args){
        SpringApplication.run(StrongConcurrencyApplication.class,args);
    }
}
