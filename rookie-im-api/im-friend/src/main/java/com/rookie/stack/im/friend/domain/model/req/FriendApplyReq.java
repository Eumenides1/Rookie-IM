package com.rookie.stack.im.friend.domain.model.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eumenides
 * @description
 * @date 2024/10/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendApplyReq {
    // 申请信息可以为空
    private String msg;
    @NotNull
    private Long targetUid;
}
