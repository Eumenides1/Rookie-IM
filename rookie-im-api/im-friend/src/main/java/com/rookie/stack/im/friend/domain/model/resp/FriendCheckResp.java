package com.rookie.stack.im.friend.domain.model.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname FriendCheckResp
 * @Description 好友校验
 * @Date 2024/9/29 14:39
 * @Created by liujiapeng
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendCheckResp {
    private Long id;
    private Boolean isFriend;
}

