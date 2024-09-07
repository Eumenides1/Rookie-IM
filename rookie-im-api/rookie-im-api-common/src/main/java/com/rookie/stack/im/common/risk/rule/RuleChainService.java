package com.rookie.stack.im.common.risk.rule;

import com.rookie.stack.im.common.exception.HttpErrorEnum;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
public interface RuleChainService {

    /**
     * 执行处理逻辑
     */
    HttpErrorEnum execute(HttpServletRequest request, Object handler);

    /**
     * 规则链中的每个规则排序
     */
    int getOrder();
}
