package com.rookie.stack.im.common.interceptor.base;

import cn.hutool.core.collection.CollectionUtil;
import com.rookie.stack.im.common.risk.rule.RuleChainService;
import jakarta.annotation.Resource;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
public abstract class BaseInterceptor implements HandlerInterceptor {
    @Resource
    private List<RuleChainService> ruleChainServices;

    protected List<RuleChainService> getRuleChainServices(){
        if (CollectionUtil.isEmpty(ruleChainServices)){
            return Collections.emptyList();
        }
        return ruleChainServices.stream().sorted(Comparator.comparing(RuleChainService::getOrder)).collect(Collectors.toList());
    }
}
