package com.rookie.stack.common.response.defaults;

import com.rookie.stack.common.response.api.ResponseFactory;
import com.rookie.stack.common.response.api.ResponseStatusFactory;
import com.rookie.stack.common.response.config.GracefulResponseProperties;
import com.rookie.stack.common.response.data.Response;
import com.rookie.stack.common.response.data.ResponseStatus;
import com.rookie.stack.common.response.exception.GracefulResponseException;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author eumenides
 * @description
 * @date 2024/9/10
 */
public class DefaultResponseFactory implements ResponseFactory {
    private final Logger logger = LoggerFactory.getLogger(DefaultResponseFactory.class);

    private static final Integer RESPONSE_STYLE_0 = 0;

    private static final Integer RESPONSE_STYLE_1 = 1;

    @Resource
    private ResponseStatusFactory responseStatusFactory;

    @Resource
    private GracefulResponseProperties properties;

    /**
     * 创建一个空的 Response 实例。首先检查是否配置了
     * responseClassFullName(即自定义的响应类的全限定名），如果配置了则反射创建对应的 Response 实例。
     * 如果没有配置，则调用 generateDefaultResponse() 方法创建默认的 Response。
     */
    @Override
    public Response newEmptyInstance() {
        try {
            String responseClassFullName = properties.getResponseClassFullName();
            //配置了Response的全限定名，即自定义了Response，用配置的进行返回
            if (StringUtils.hasLength(responseClassFullName)) {
                Object newInstance = Class.forName(responseClassFullName).getConstructor().newInstance();
                return (Response) newInstance;
            } else {
                //没有配Response的全限定名，则创建DefaultResponse
                return generateDefaultResponse();
            }
        } catch (Exception e) {
            throw new GracefulResponseException("创建空的Response失败", e);
        }
    }

    /**
     * 根据配置的 responseStyle 返回相应的 Response 实现类。
     * 默认的风格为 DefaultResponseImplStyle1，
     * 如果 responseStyle 为 0 则返回 DefaultResponseImplStyle0。
     * 若风格不支持，则抛出 IllegalArgumentException。
     */
    private Response generateDefaultResponse() {
        Integer responseStyle = properties.getResponseStyle();
        //默认的Response style，5.0以上是Style0，5.0（包括5.0）之后是Style1
        if (Objects.isNull(responseStyle)) {
            return new DefaultResponseImplStyle1();
        }
        if (RESPONSE_STYLE_0.equals(responseStyle)) {
            return new DefaultResponseImplStyle0();
        } else if (RESPONSE_STYLE_1.equals(responseStyle)) {
            return new DefaultResponseImplStyle1();
        } else {
            logger.error("不支持的Response style类型,responseStyle={}", responseStyle);
            throw new IllegalArgumentException("不支持的Response style类型");
        }
    }

    @Override
    public Response newInstance(ResponseStatus responseStatus) {
        Response bean = this.newEmptyInstance();
        bean.setStatus(responseStatus);
        return bean;
    }

    @Override
    public Response newInstance(ResponseStatus statusLine, Object data) {
        Response bean = this.newInstance(statusLine);
        bean.setPayload(data);
        return bean;
    }

    @Override
    public Response newSuccessInstance() {
        Response emptyInstance = this.newEmptyInstance();
        emptyInstance.setStatus(responseStatusFactory.defaultSuccess());
        return emptyInstance;
    }

    @Override
    public Response newSuccessInstance(Object data) {
        Response bean = this.newSuccessInstance();
        bean.setPayload(data);
        return bean;
    }

    @Override
    public Response newFailInstance() {
        Response bean = this.newEmptyInstance();
        bean.setStatus(responseStatusFactory.defaultError());
        return bean;
    }
}
