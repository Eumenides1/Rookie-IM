package com.rookie.stack.im.common.interceptor;

import com.rookie.stack.im.common.exception.BusinessException;
import com.rookie.stack.im.common.exception.HttpErrorEnum;
import com.rookie.stack.im.common.interceptor.base.BaseInterceptor;
import com.rookie.stack.im.common.risk.rule.RuleChainService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
@Component
public class IMInterceptor extends BaseInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取排好序的规则链
        List<RuleChainService> ruleChainServices = this.getRuleChainServices();
        //遍历规则链
        for (RuleChainService ruleChainService : ruleChainServices){
            HttpErrorEnum errorEnum = ruleChainService.execute(request, handler);
            if (!HttpErrorEnum.SUCCESS.getCode().equals(errorEnum.getCode())){
                throw new BusinessException(errorEnum);
            }
        }
        return true;
    }
}
