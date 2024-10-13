package com.rookie.stack.im.friend.domain.model.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eumenides
 * @Classname FriendCheckReq
 * @Description TODO
 * @Date 2024/9/29 14:38
 * @Created by liujiapeng
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendCheckReq {

    @NotNull(message = "好友 ID 不能为空")
    private Long friendId;

}
