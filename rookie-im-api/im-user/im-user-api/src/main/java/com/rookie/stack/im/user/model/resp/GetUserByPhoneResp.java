package com.rookie.stack.im.user.model.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname GetUserByPhoneResp
 * @Description TODO
 * @Date 2024/9/29 11:33
 * @Created by liujiapeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserByPhoneResp {
    private Long id;

    private String password;
}
