package top.unow.seckill.validator;

import org.apache.commons.lang3.StringUtils;
import top.unow.seckill.util.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
 *  @项目名：  stronger-concurrency
 *  @包名：    top.unow.seckill.validator
 *  @文件名:   IsMobileValidator
 *  @创建者:   ouyangxiong
 *  @创建时间:  2019-05-04 21:09
 *  @描述：     自定义手机格式校验器

 */
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
