package com.rookie.stack.im.common.risk.rule.base;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.rookie.stack.im.common.config.JwtProperties;
import com.rookie.stack.im.common.exception.BusinessException;
import com.rookie.stack.im.common.exception.HttpErrorEnum;
import com.rookie.stack.im.common.model.constants.IMApiConstants;
import com.rookie.stack.im.common.model.session.UserSessionInfo;
import com.rookie.stack.im.common.risk.rule.RuleChainService;
import com.rookie.stack.im.common.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author eumenides
 * @description 基础的规则类实现，抽象类
 * @date 2024/9/7
 */
public abstract class BaseRuleChainService implements RuleChainService {
    private final Logger logger = LoggerFactory.getLogger(BaseRuleChainService.class);

    protected static final int DEFAULT_WINDOWS_SIZE = 50;
    protected static final int DEFAULT_WINDOWS_PERIOD = 1000;

    @Resource
    private JwtProperties jwtProperties;

    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST_IP = "127.0.0.1";
    // 客户端与服务器同为一台机器，获取的 ip 有时候是 ipv6 格式
    private static final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
    private static final String SEPARATOR = ",";

    public BaseRuleChainService(){
        logger.info("IMBaseRuleChainService|当前规则服务|{}", this.getServiceName());
    }
    /**
     * 获取ip地址
     */
    protected String getIp(HttpServletRequest request){
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (LOCALHOST_IP.equalsIgnoreCase(ip) || LOCALHOST_IPV6.equalsIgnoreCase(ip)) {
                // 根据网卡取本机配置的 IP
                InetAddress iNet = null;
                try {
                    iNet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    logger.error("BaseRuleChainService.getIp|获取客户端ip地址异常|{}", e.getMessage());
                }
                if (iNet != null) {
                    ip = iNet.getHostAddress();
                }
            }
        }
        // 对于通过多个代理的情况，分割出第一个 IP
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(SEPARATOR) > 0) {
                ip = ip.substring(0, ip.indexOf(SEPARATOR));
            }
        }
        return LOCALHOST_IPV6.equals(ip) ? LOCALHOST_IP : ip;
    }

    protected UserSessionInfo getUserSessionInfo(HttpServletRequest request){
        String token = request.getHeader(IMApiConstants.ACCESS_TOKEN);
        if (StrUtil.isEmpty(token)) {
            logger.error("BaseRuleChainService|未登录，url|{}",request.getRequestURI());
            throw new BusinessException(HttpErrorEnum.NO_LOGIN);
        }
        //验证 token
        if(!JwtUtils.checkSign(token, jwtProperties.getAccessTokenSecret())){
            logger.error("BaseRuleChainService|token已失效，url|{}",request.getRequestURI());
            throw new BusinessException(HttpErrorEnum.INVALID_TOKEN);
        }
        // 存放session
        String strJson = JwtUtils.getInfo(token);
        if (StrUtil.isEmpty(strJson)){
            logger.error("BaseRuleChainService|token已失效，url|{}",request.getRequestURI());
            throw new BusinessException(HttpErrorEnum.INVALID_TOKEN);
        }
        return JSON.parseObject(strJson, UserSessionInfo.class);
    }

    /**
     * 当前服务的服务名称
     */
    public abstract String getServiceName();
}
