package com.rookie.stack.im.friend.domain.model.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eumenides
 * @description 好友申请
 * @date 2024/10/13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendApplyResp {
    // 申请 id
    private Long applyId;
    // 申请人 id
    private Long uid;
    // 申请类型（1：加好友）
    private Integer type;
    // 申请信息
    private String msg;
    // 申请状态
    private Integer status;
}
