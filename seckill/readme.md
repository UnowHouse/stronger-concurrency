# 高并发秒杀系统

[感谢zaiyunduan123的分享](https://github.com/zaiyunduan123/springboot-seckill/)

1. 导入mysql文件
进入mysql控制台，用source命令导入
```mysql
source ./seckill.sql #sql文件路径
```

2. 开发技术

前端技术 ：Bootstrap + jQuery + Thymeleaf

后端技术 ：SpringBoot + MyBatis + MySQL

中间件技术 : Druid + Redis + RabbitMQ + Guava

3. maven依赖导入

- springboot 2.0.5体系
- thymeleaf
- mysql-connector
- druid
- redis
- fastjson
- commons-codec
- commons-lang3
- amqp
- guava

4. 自定义注解IsMobile

```java
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {IsMobileValidator.class}
)//引进校验器
public @interface IsMobile {
    boolean required() default true;//默认不能为空

    String message() default "手机号码格式错误";//校验不通过输出信息

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

```

5. 