package com.rookie.stack.im.friend.domain.model.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eumenides
 * @description 申请好友审批 request
 * @date 2024/10/14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendApproveReq {
    @NotNull
    private Long applyId;
}
