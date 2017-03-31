package com.common.validator;

import com.exception.ServiceException;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * Created by Administrator on 2016/4/13.
 */
public class UserValidator extends Validator {

    //校验手机号码正则表达式
    private static final String MOBILE_PATTERN = "\\b(1[3,5,7,8,9]\\d{9})\\b";

    protected void validate(Controller controller) {
        validateRequiredString("username", "errorkey", "请输入用户名!");
        validateRequiredString("password", "errorkey", "请输入密码!");

    }

    protected void handleError(Controller controller) {
        throw new ServiceException(controller.getAttr("errorkey").toString());
    }
}
