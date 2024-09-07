package com.rookie.stack.im.common.model.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eumenides
 * @description
 * @date 2024/9/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseSessionInfo {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 终端类型
     */
    private Integer terminal;
}
