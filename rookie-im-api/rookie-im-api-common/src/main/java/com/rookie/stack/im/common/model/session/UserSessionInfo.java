package com.rookie.stack.im.common.model.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
@Data
@NoArgsConstructor
public class UserSessionInfo extends BaseSessionInfo{
    /*
     * 用户名称
     */
    private String userName;

    /*
     * 用户昵称
     */
    private String nickName;
    public UserSessionInfo(Long userId, Integer terminal, String userName, String nickName) {
        super(userId, terminal);
        this.userName = userName;
        this.nickName = nickName;
    }
}
