package top.unow.seckill.exception;

import top.unow.seckill.result.CodeMsg;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.exception
 *  @文件名:   GlobalException
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-04 21:01
 *  @描述：    TODO
 */
public class GlobalException extends RuntimeException {

    private static final long servialVersionUID = 1L;

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
