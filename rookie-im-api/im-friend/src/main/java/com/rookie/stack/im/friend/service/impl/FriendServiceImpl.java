package com.rookie.stack.im.friend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.rookie.stack.framework.common.exception.BusinessException;
import com.rookie.stack.framework.common.utils.AssertUtil;
import com.rookie.stack.im.context.holder.LoginUserContextHolder;
import com.rookie.stack.im.friend.dao.UserApplyDao;
import com.rookie.stack.im.friend.dao.UserFriendDao;
import com.rookie.stack.im.friend.domain.entity.UserApply;
import com.rookie.stack.im.friend.domain.entity.UserFriend;
import com.rookie.stack.im.friend.domain.model.req.FriendApplyReq;
import com.rookie.stack.im.friend.domain.model.req.FriendCheckReq;
import com.rookie.stack.im.friend.domain.model.resp.FriendCheckResp;
import com.rookie.stack.im.friend.exception.FriendErrorEnum;
import com.rookie.stack.im.friend.service.FriendService;
import com.rookie.stack.im.friend.service.adapter.FriendAdapter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author eumenides
 * @description
 * @date 2024/10/13
 */
@Service
@Slf4j
public class FriendServiceImpl implements FriendService {
    @Resource
    private UserFriendDao userFriendDao;
    @Resource
    private UserApplyDao userApplyDao;
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

    // TODO 添加分布式锁
    @Override
    public void apply(FriendApplyReq req) {
        // 1. 获取用户的 id
        Long userId = LoginUserContextHolder.getUserId();
        if (Objects.equals(userId, req.getTargetUid())) {
            throw new BusinessException(FriendErrorEnum.CAN_NOT_ADD_YOURSELF);
        }
        // 判断是否已经是好友了
        UserFriend userFriend = userFriendDao.getByFriend(userId, req.getTargetUid());
        AssertUtil.isEmpty(userFriend,"你们已经是好友啦！");
        // 是否有等待审批的记录
        //是否有待审批的申请记录(自己的)
        UserApply selfApproving = userApplyDao.getFriendApproving(userId, req.getTargetUid());
        if (Objects.nonNull(selfApproving)) {
            log.info("已有好友申请记录,uid:{}, targetId:{}", userId, req.getTargetUid());
            return;
        }
        //是否有待审批的申请记录(别人请求自己的)
        UserApply friendApproving = userApplyDao.getFriendApproving(req.getTargetUid(), userId);
        if (Objects.nonNull(friendApproving)) {
            // TODO 如果有别人请求自己的申请，那我再去添加对方 = 同意对方的好友请求
            return;
        }
        UserApply insert = FriendAdapter.buildFriendApply(userId, req);
        userApplyDao.save(insert);
        // TODO 发布好友申请事件
    }
}
