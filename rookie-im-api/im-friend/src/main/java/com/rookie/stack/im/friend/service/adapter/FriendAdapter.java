package com.rookie.stack.im.friend.service.adapter;

import com.rookie.stack.im.friend.domain.entity.UserApply;
import com.rookie.stack.im.friend.domain.model.req.FriendApplyReq;
import com.rookie.stack.im.friend.domain.model.resp.FriendApplyResp;

import java.util.List;
import java.util.stream.Collectors;

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
    public static List<FriendApplyResp> buildFriendApplyList(List<UserApply> records) {
        return records.stream().map(userApply -> {
            FriendApplyResp friendApplyResp = new FriendApplyResp();
            friendApplyResp.setUid(userApply.getUid());
            friendApplyResp.setType(userApply.getType());
            friendApplyResp.setApplyId(userApply.getId());
            friendApplyResp.setMsg(userApply.getMsg());
            friendApplyResp.setStatus(userApply.getStatus());
            return friendApplyResp;
        }).collect(Collectors.toList());
    }
}
