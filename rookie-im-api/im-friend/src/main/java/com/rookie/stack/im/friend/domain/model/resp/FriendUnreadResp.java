package com.rookie.stack.im.friend.domain.model.resp;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendUnreadResp {
    private Integer unReadCount;
}
