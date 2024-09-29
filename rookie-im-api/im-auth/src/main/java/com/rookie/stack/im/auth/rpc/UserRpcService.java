package com.rookie.stack.im.auth.rpc;

import com.rookie.stack.framework.common.domain.response.ApiResult;
import com.rookie.stack.im.user.api.UserFeignApi;
import com.rookie.stack.im.user.model.req.RegisterUserReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @Classname UserRpcService
 * @Description TODO
 * @Date 2024/9/29 10:45
 * @Created by liujiapeng
 */
@Component
public class UserRpcService {
    @Resource
    private UserFeignApi userFeignApi;

    /**
     * 用户注册
     *
     * @param phone
     * @return
     */
    public Long registerUser(String phone) {
        RegisterUserReq req = new RegisterUserReq();
        req.setPhone(phone);
        ApiResult<Long> response = userFeignApi.registerUser(req);
        if (!response.getSuccess()) {
            return null;
        }
        return response.getData();
    }
}
