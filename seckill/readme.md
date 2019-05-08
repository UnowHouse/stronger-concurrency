# 高并发秒杀系统

[感谢zaiyunduan123的分享](https://github.com/zaiyunduan123/springboot-seckill/)

## 开发准备

### 1. 导入mysql文件
进入mysql控制台，用source命令导入
```mysql
source ./seckill.sql #sql文件路径
```

### 2. 开发技术

前端技术 ：Bootstrap + jQuery + Thymeleaf

后端技术 ：SpringBoot + MyBatis + MySQL

中间件技术 : Druid + Redis + RabbitMQ + Guava

### 3. maven依赖导入

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

### 4. 统一请求响应体

- 响应格式

```java
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    /**
     *  成功时候的调用
     * */
    public static  <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    /**
     *  失败时候的调用
     * */
    public static  <T> Result<T> error(CodeMsg codeMsg){
        return new Result<T>(codeMsg);
    }
    
    //....
}
```

- 响应状态码与信息设置

```java
public class CodeMsg {

    private int code;
    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    
    //通用的错误码
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    public static CodeMsg ACCESS_LIMIT_REACHED= new CodeMsg(500104, "访问高峰期，请稍等！");
    
    //.... 
}
```

### 5. 全局异常设置



- 全局异常响应体设置
    
    定义统一的异常返回体格式。
    
```java
public class GlobalException extends RuntimeException {

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
       
}
```

- 全局异常捕捉器
    思路：
        1. 捕获异常，判断其异常类型，若属于 GlobalException，则直接返回
        2. 若捕获的异常属于`org.springframework.validation.BindException`类型,则将异常信息`error.getDefaultMessage()`填充进自定义的Result请求响应体，将其返回。
        3. 不满足1和2的异常，直接返回自定义的服务器异常`CodeMsg.SERVER_ERROR`

```java
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)//拦截所有异常
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        if(e instanceof GlobalException) {
            GlobalException ex = (GlobalException)e;
            return Result.error(ex.getCodeMsg());
        }else if(e instanceof BindException) {
            BindException ex = (BindException)e;
            List<ObjectError> errors = ex.getAllErrors();//绑定错误返回很多错误，是一个错误列表，只需要第一个错误
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));//给状态码填充参数
        }else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }

    }
}
```

### 6. 检验手机号注解

基于spring的@Valid注解进行数据校验

知识补充：
    @Valid注解可以实现数据的验证，你可以定义实体，在实体的属性上添加校验规则，而在API接收数据时添加；
    @Valid关键字，这时你的实体将会开启一个校验的功能；
    
- 自定义注解IsMobile

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
- 检验工具类
```java
public class ValidatorUtil {
    //默认以1开头后面加10个数字为手机号
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src){
        if(StringUtils.isEmpty(src)){
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        //检验返回false时，@valid校验注解体系抛出BindException异常
        return m.matches();
    }
}

```

- 设置校验器

```java
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    //初始化
    @Override
    public void initialize(IsMobile isMobile) {
        required = isMobile.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidatorUtil.isMobile(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}

```

### 7. 缓存操作

- redis配置

```java
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {
    private String host;
    private int port;
    private int timeout;
    private String password;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait;
}
```
```yaml
redis:
 host: 127.0.0.1
 port: 6379
 timeout: 10
 poolMaxTotal: 1000
 poolMaxIdle: 500
 poolMaxWait: 500
```

- redis连接池

```java
@Service
public class RedisPoolFactory {

    @Autowired
    RedisConfig  redisConfig;

    /**
     * 将redis连接池注入spring容器
     * @return
     */
    @Bean
    public JedisPool JedisPoolFactory(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(redisConfig.getPoolMaxIdle());
        config.setMaxTotal(redisConfig.getPoolMaxTotal());
        config.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        JedisPool jp = new JedisPool(config, redisConfig.getHost(), redisConfig.getPort(),
                redisConfig.getTimeout()*1000, redisConfig.getPassword(), 0);
        return jp;
    }

}

```

- redisKeyPrefix
```java
public interface KeyPrefix {
    /**
     * 有效期
     * @return
     */
    int expireSeconds();

    /**
     * 前缀
     * @return
     */
    String getPrefix();
}

```

- redis服务类

```java
@Service
public class RedisService {
    @Autowired
    JedisPool jedisPool;

    /**
     * 从redis连接池获取redis实例
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz){
        //...
    }

    /**
     * 存储对象
     */
    public <T> Boolean set(KeyPrefix prefix, String key, T value){
        //...
    }

    /**
     * 删除
     */
    public boolean delete(KeyPrefix prefix, String key){
        //...
    }

    //...

}
```


## 服务模块开发

### 1. 登陆模块

使用JSR303自定义校验器，实现对用户账号、密码的验证，使得验证逻辑从业务代码中脱离出来。


- 登陆数据体

```java
public class LoginVo {

    @NotNull
    @IsMobile  //因为框架没有校验手机格式注解，所以自己定义
    private String mobile;

    @NotNull
    private String password;
    
}
```

- 所需工具类

    1. MD5Util
    2. UUIDUtil

- 控制器

```java
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
```

- Service层

   思路：
    1. 根据mobile，从缓存中取，无则从数据库中查询，有则存入缓存，无则表示用户不存在；
    2. 取到用户数据后，将请求参数到密码进行MD5处理，然后与查询到的密码进行匹配；
    3. 通过后通过UUIDUtil生成唯一的token，将其放回，同时将该tooken添加到cookie中
    
```java
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisService redisService;

    public static final String COOKIE_NAME_TOKEN = "token";
    
    public String login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        User user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);

        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成唯一id作为token
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return token;
    }
    
    public User getById(long id) {
            //对象缓存
            User user = redisService.get(UserKey.getById, "" + id, User.class);
            if (user != null) {
                return user;
            }
            //取数据库
            user = userMapper.getById(id);
            //再存入缓存
            if (user != null) {
                redisService.set(UserKey.getById, "" + id, user);
            }
            return user;
        }
}
```

### 2. 商品展示模版

- 页面缓存+对象缓存

    1. 页面缓存：通过在手动渲染得到的html页面缓存到redis
    2. 对象缓存：包括对用户信息、商品信息、订单信息和token等数据进行缓存，利用缓存来减少对数据库的访问，大大加快查询速度。

- 页面静态化
    
    对商品详情进行页面静态化处理，页面是存在html，动态数据是通过接口从服务端获取，实现前后端分离，静态页面无需连接数据库打开速度较动态页面会有明显提高。
    
