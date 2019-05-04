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