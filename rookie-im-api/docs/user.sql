-- 创建用户表 im_user
CREATE TABLE im_user (
     id BIGINT PRIMARY KEY COMMENT '唯一标识',
     user_name VARCHAR(255) COMMENT '用户名',
     nick_name VARCHAR(255) COMMENT '昵称',
     sex INT COMMENT '性别',
     head_image VARCHAR(255) COMMENT '头像',
     head_image_thumb VARCHAR(255) COMMENT '头像缩略图',
     type INT COMMENT '用户类型，1 表示普通用户，2 表示审核专用账户',
     signature VARCHAR(255) COMMENT '个性签名',
     password VARCHAR(255) COMMENT '密码',
     last_login_time DATETIME COMMENT '最后登录时间',
     user_status INT COMMENT '用户状态，0 表示正常，-1 表示被禁用',
     created_time DATETIME COMMENT '创建时间',
     update_time DATETIME COMMENT '更新时间'
) COMMENT = '用户信息表';

ALTER TABLE im_user
    ALTER COLUMN user_status SET DEFAULT 0;