package com.rookie.stack.im.friend.controller;
import com.rookie.stack.framework.common.domain.response.ApiResult;
import com.rookie.stack.im.friend.domain.model.req.FriendCheckReq;
import com.rookie.stack.im.friend.domain.model.resp.FriendCheckResp;
import com.rookie.stack.im.friend.service.FriendService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eumenides
 * @description
 * @date 2024/10/13
 */
@RestController
@RequestMapping("/friend")
public class UserFriendController {
    @Resource
    private FriendService friendService;
    @GetMapping("/check")
    public ApiResult<FriendCheckResp> check(@Valid  FriendCheckReq req){
        return ApiResult.success(friendService.check(req));
    }
}
