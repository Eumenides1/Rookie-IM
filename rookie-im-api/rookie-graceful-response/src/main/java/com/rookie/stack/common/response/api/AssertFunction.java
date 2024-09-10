package com.rookie.stack.common.response.api;

/**
 * @author eumenides
 * @description
 * @date 2024/9/10
 */
@FunctionalInterface //函数式接口
public interface AssertFunction {
    /**
     * 执行断言判断逻辑，应抛出运行时异常
     */
    void doAssert();
}
