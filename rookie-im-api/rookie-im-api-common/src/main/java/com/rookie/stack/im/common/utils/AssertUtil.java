package com.rookie.stack.im.common.utils;

import cn.hutool.core.util.ObjectUtil;
import com.rookie.stack.im.common.exception.BusinessErrorEnum;
import com.rookie.stack.im.common.exception.BusinessException;
import com.rookie.stack.im.common.exception.ErrorEnum;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * @author eumenides
 * @description 断言工具类
 * @date 2024/9/7
 */
public class AssertUtil {

    //如果不是非空对象，则抛异常
    public static void isNotEmpty(Object obj, String msg) {
        if (isEmpty(obj)) {
            throwException(msg);
        }
    }

    //如果不是非空对象，则抛异常
    public static void isNotEmpty(Object obj, ErrorEnum errorEnum, Object... args) {
        if (isEmpty(obj)) {
            throwException(errorEnum, args);
        }
    }

    public static void isEmpty(Object obj, String msg){
        if (isEmpty(obj)) {
            throwException(msg);
        }
    }

    public static void isTrue(boolean expression, String msg){
        if (!expression) {
            throwException(msg);
        }
    }

    public static void isTrue(boolean expression,ErrorEnum errorEnum ,Object... args){
        if (!expression) {
            throwException(errorEnum, args);
        }
    }

    public static void isFalse(boolean expression, String msg){
        if (expression) {
            throwException(msg);
        }
    }

    public static void isFalse(boolean expression,ErrorEnum errorEnum ,Object... args){
        if (expression) {
            throwException(errorEnum, args);
        }
    }


    public static void isEmpty(Object obj, ErrorEnum errorEnum, Object... args) {
        if (!isEmpty(obj)) {
            throwException(errorEnum, args);
        }
    }
    private static void throwException(String msg) {
        throwException(null, msg);

    }
    public static void throwException (ErrorEnum errorEnum, Object... arg) {

        if (Objects.isNull(errorEnum)) {
            errorEnum = BusinessErrorEnum.BUSINESS_ERROR;
        }
        throw new BusinessException(errorEnum.getErrorCode(), MessageFormat.format(errorEnum.getErrorMsg(), arg));
    }


    public static boolean isEmpty(Object object){
        return ObjectUtil.isEmpty(object);
    }

}
