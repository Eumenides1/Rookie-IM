package com.rookie.stack.im.user.api;

import com.rookie.stack.framework.common.domain.response.ApiResult;
import com.rookie.stack.im.user.constant.ApiConstants;
import com.rookie.stack.im.user.model.req.RegisterUserReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Classname UserFeignApi
 * @Description TODO
 * @Date 2024/9/29 10:41
 * @Created by liujiapeng
 */
@FeignClient(name = ApiConstants.SERVICE_NAME)
public interface UserFeignApi {

    String PREFIX = "/user";

    /**
     * 用户注册
     *
     * @param req
     * @return
     */
    @PostMapping(value = PREFIX + "/register")
    ApiResult<Long> registerUser(@RequestBody RegisterUserReq req);

}
