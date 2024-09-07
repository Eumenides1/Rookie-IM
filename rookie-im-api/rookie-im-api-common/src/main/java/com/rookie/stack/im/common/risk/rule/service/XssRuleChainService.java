package com.rookie.stack.im.common.risk.rule.service;

import cn.hutool.core.util.BooleanUtil;
import com.rookie.stack.im.common.exception.HttpErrorEnum;
import com.rookie.stack.im.common.risk.enums.RuleEnum;
import com.rookie.stack.im.common.risk.rule.RuleChainService;
import com.rookie.stack.im.common.risk.rule.base.BaseRuleChainService;
import com.rookie.stack.im.common.risk.utils.XssUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
@Component
public class XssRuleChainService extends BaseRuleChainService implements RuleChainService {

    private final Logger logger = LoggerFactory.getLogger(XssRuleChainService.class);

    @Value("${rookie.im.rule.xssRule.enabled}")
    private Boolean xssRuleEnabled;

    @Value("${rookie.im.rule.xssRule.order}")
    private Integer xssRuleOrder;

    @Override
    public HttpErrorEnum execute(HttpServletRequest request, Object handler) {
        //未开启XSS规则，直接通过校验
        if (BooleanUtil.isFalse(xssRuleEnabled)){
            return HttpErrorEnum.SUCCESS;
        }
        // 检查参数
        Map<String, String[]> paramMap =  request.getParameterMap();
        for(String[] values:paramMap.values()){
            for(String value:values){
                if(XssUtils.checkXss(value)){
                    return HttpErrorEnum.XSS_PARAM_ERROR;
                }
            }
        }
        // 检查body
        String body = getBody(request);
        if(XssUtils.checkXss(body)){
            return HttpErrorEnum.XSS_PARAM_ERROR;
        }
        return HttpErrorEnum.SUCCESS;
    }

    @Override
    public int getOrder() {
        return xssRuleOrder == null ? RuleEnum.XSS.getCode() : xssRuleOrder;
    }

    @Override
    public String getServiceName() {
        return RuleEnum.XSS.getMesaage();
    }

    private String getBody(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }catch (IOException e){
            logger.error("XssInterceptor.getBody|获取请求体异常:{}", e.getMessage());
        }
        return sb.toString();
    }
}
