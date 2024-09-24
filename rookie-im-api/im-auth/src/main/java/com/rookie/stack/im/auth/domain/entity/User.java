package com.rookie.stack.im.auth.domain.entity;

import lombok.Data;

/**
 * @author eumenides
 * @description
 * @date 2024/9/24
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
