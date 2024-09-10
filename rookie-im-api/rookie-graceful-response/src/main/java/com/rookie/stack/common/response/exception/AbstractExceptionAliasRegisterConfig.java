package com.rookie.stack.common.response.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author eumenides
 * @description
 * @date 2024/9/10
 */
public abstract class AbstractExceptionAliasRegisterConfig implements ApplicationContextAware {
    private final Logger logger = LoggerFactory.getLogger(AbstractExceptionAliasRegisterConfig.class);

    /**
     * 注册异常别名
     * @param register
     */
    protected abstract void registerAlias(ExceptionAliasRegister register);
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            ExceptionAliasRegister aliasRegister = applicationContext.getBean(ExceptionAliasRegister.class);
            this.registerAlias(aliasRegister);
        } catch (Exception e) {
            logger.warn("未从ApplicationContext中获取到ExceptionAliasRegister实例， @ExceptionAliasFor注解将无效", e);
        }
    }
}
