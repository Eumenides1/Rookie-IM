package com.rookie.stack.im.common.risk.rule.service;

import com.rookie.stack.im.common.exception.BusinessException;
import com.rookie.stack.im.common.exception.HttpErrorEnum;
import com.rookie.stack.im.common.model.constants.IMApiConstants;
import com.rookie.stack.im.common.model.session.UserSessionInfo;
import com.rookie.stack.im.common.risk.enums.RuleEnum;
import com.rookie.stack.im.common.risk.rule.RuleChainService;
import com.rookie.stack.im.common.risk.rule.base.BaseRuleChainService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

/**
 * @author eumenides
 * @description 前置 token 校验
 * @date 2024/9/8
 */
@Component
public class AuthRuleChainService extends BaseRuleChainService implements RuleChainService {
    private final Logger logger = LoggerFactory.getLogger(AuthRuleChainService.class);

    @Value("${rookie.im.rule.authRule.order}")
    private Integer authRuleOrder;

    @Override
    public HttpErrorEnum execute(HttpServletRequest request, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return HttpErrorEnum.SUCCESS;
        }
        UserSessionInfo userSession = this.getUserSessionInfo(request);
        if (userSession == null){
            logger.error("AuthRuleChainService|未登录，url|{}",request.getRequestURI());
            throw new BusinessException(HttpErrorEnum.NO_LOGIN);
        }
        request.setAttribute(IMApiConstants.SESSION, userSession);
        return HttpErrorEnum.SUCCESS;
    }

    @Override
    public int getOrder() {
        return authRuleOrder == null ? RuleEnum.AUTH.getCode() : authRuleOrder;
    }

    @Override
    public String getServiceName() {
        return RuleEnum.AUTH.getMesaage();
    }
}
