package com.rookie.stack.im.friend.service;

import com.rookie.stack.framework.common.domain.model.req.PageBaseReq;
import com.rookie.stack.framework.common.domain.model.resp.PageBaseResp;
import com.rookie.stack.im.friend.domain.model.req.FriendApplyReq;
import com.rookie.stack.im.friend.domain.model.req.FriendCheckReq;
import com.rookie.stack.im.friend.domain.model.resp.FriendApplyResp;
import com.rookie.stack.im.friend.domain.model.resp.FriendCheckResp;

/**
 * @Classname FriendService
 * @Description TODO
 * @Date 2024/9/29 14:34
 * @Created by liujiapeng
 */
public interface FriendService {

    /**
     * 检查是否是自己好友
     * @param request 请求
     * @return {@link FriendCheckResp}
     */
    FriendCheckResp check(FriendCheckReq request);

    /**
     *
     * @param req
     */
    void apply(FriendApplyReq req);

    PageBaseResp<FriendApplyResp> pageApplyFriend(PageBaseReq req);
}
