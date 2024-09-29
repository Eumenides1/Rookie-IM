package com.rookie.stack.im.context.interceptor;

import com.rookie.stack.framework.common.constant.GlobalConstants;
import com.rookie.stack.im.context.holder.LoginUserContextHolder;
import lombok.extern.slf4j.Slf4j;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.Objects;

/**
 * @Classname FeignRequestInterceptor
 * @Description TODO
 * @Date 2024/9/29 10:07
 * @Created by liujiapeng
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor  {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 获取当前上下文中的用户 ID
        Long userId = LoginUserContextHolder.getUserId();

        // 若不为空，则添加到请求头中
        if (Objects.nonNull(userId)) {
            requestTemplate.header(GlobalConstants.USER_ID, String.valueOf(userId));
            log.info("########## feign 请求设置请求头 userId: {}", userId);
        }
    }
}
