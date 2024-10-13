package com.rookie.stack.im.friend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.rookie.stack.im.context.holder.LoginUserContextHolder;
import com.rookie.stack.im.friend.dao.UserFriendDao;
import com.rookie.stack.im.friend.domain.entity.UserFriend;
import com.rookie.stack.im.friend.domain.model.req.FriendCheckReq;
import com.rookie.stack.im.friend.domain.model.resp.FriendCheckResp;
import com.rookie.stack.im.friend.service.FriendService;
import jakarta.annotation.Resource;

/**
 * @author eumenides
 * @description
 * @date 2024/10/13
 */
public class FriendServiceImpl implements FriendService {
    @Resource
    private UserFriendDao userFriendDao;
    @Override
    public FriendCheckResp check(FriendCheckReq request) {
        boolean isFriend = false;
        // 1. 获取用户的 id
        Long userId = LoginUserContextHolder.getUserId();
        // 2. 判断用户关系
        UserFriend userFriend = userFriendDao.getByFriend(userId, request.getFriendId());
        if (ObjectUtil.isNotEmpty(userFriend)) {
            isFriend = true;
        }
        return new FriendCheckResp(request.getFriendId(), isFriend);
    }
}
