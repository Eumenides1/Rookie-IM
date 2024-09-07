package com.rookie.stack.im.common.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author eumenides
 * @description 用户表
 * @date 2024/9/7
 */

@Data
@TableName("im_user")
@EqualsAndHashCode(callSuper = true)
public class User extends Model<User> {
    /**
     * id
     */
    @TableId(value = "id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户名
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 性别
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 头像
     */
    @TableField("head_image")
    private String headImage;

    /**
     * 头像缩略图
     */
    @TableField("head_image_thumb")
    private String headImageThumb;

    /**
     * 用户类型  1:普通用户 2:审核专用账户
     */
    @TableField("type")
    private Integer type;

    /**
     * 个性签名
     */
    @TableField("signature")
    private String signature;
    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 最后登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;

    /**
     * 用户状态 0：正常；-1：被禁用
     */
    @TableField("user_status")
    private Integer userStatus;

    /**
     * 创建时间
     */
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private Date createdTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
