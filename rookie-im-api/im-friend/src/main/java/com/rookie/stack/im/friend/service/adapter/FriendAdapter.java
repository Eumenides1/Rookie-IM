package com.rookie.stack.im.friend.service.adapter;

import com.rookie.stack.im.friend.domain.entity.UserApply;
import com.rookie.stack.im.friend.domain.model.req.FriendApplyReq;

import static com.rookie.stack.im.friend.domain.enums.ApplyReadStatusEnum.UNREAD;
import static com.rookie.stack.im.friend.domain.enums.ApplyStatusEnum.WAIT_APPROVAL;
import static com.rookie.stack.im.friend.domain.enums.ApplyTypeEnum.ADD_FRIEND;

/**
 * @author eumenides
 * @description
 * @date 2024/10/13
 */
public class FriendAdapter {
    public static UserApply buildFriendApply(Long uid, FriendApplyReq request) {
        UserApply userApplyNew = new UserApply();
        userApplyNew.setUid(uid);
        userApplyNew.setMsg(request.getMsg());
        userApplyNew.setType(ADD_FRIEND.getCode());
        userApplyNew.setTargetId(request.getTargetUid());
        userApplyNew.setStatus(WAIT_APPROVAL.getCode());
        userApplyNew.setReadStatus(UNREAD.getCode());
        return userApplyNew;
    }
}
