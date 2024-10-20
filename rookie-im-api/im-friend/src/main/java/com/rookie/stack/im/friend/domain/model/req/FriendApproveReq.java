package com.rookie.stack.im.friend.domain.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "好友申请记录请求实体")
public class FriendApproveReq {
    @NotNull
    @Schema(description = "好友申请 ID")
    private Long applyId;
}
