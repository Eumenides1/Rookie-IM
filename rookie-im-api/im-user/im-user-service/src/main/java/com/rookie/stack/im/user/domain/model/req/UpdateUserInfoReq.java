package com.rookie.stack.im.user.domain.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Classname UpdateUserInfoReq
 * @Description TODO
 * @Date 2024/9/27 14:26
 * @Created by liujiapeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "更新用户信息实体")
public class UpdateUserInfoReq {

    /**
     * 头像
     */
    @Schema(description = "用户头像路径")
    private String avatarUrl;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 性别
     */
    @Schema(description = "用户性别")
    private Integer sex;

    /**
     * 生日
     */
    @Schema(description = "用户生日")
    private LocalDate birthday;

    /**
     * 个人介绍
     */
    @Schema(description = "用户个人简介")
    private String introduction;

}
