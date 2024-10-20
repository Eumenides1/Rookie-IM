package com.rookie.stack.im.friend.domain.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "好友申请请求实体")
public class FriendApplyReq {
    // 申请信息可以为空
    @Schema(description = "申请信息")
    private String msg;
    @NotNull
    @Schema(description = "好友 uid")
    private Long targetUid;
}
