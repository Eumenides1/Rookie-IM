CREATE TABLE `im_user` (
      `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
      `rookie_id` varchar(15) NOT NULL COMMENT 'Rookie生态ID(唯一凭证)，可用于后续系统联动',
      `password` varchar(64) DEFAULT NULL COMMENT '密码',
      `nickname` varchar(24) NOT NULL COMMENT '昵称',
      `avatar` varchar(120) DEFAULT NULL COMMENT '头像',
      `birthday` date DEFAULT NULL COMMENT '生日',
      `background_img` varchar(120) DEFAULT NULL COMMENT '背景图',
      `phone` varchar(11) NOT NULL COMMENT '手机号',
      `sex` tinyint DEFAULT '0' COMMENT '性别(0：女 1：男)',
      `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态(0：启用 1：禁用)',
      `introduction` varchar(100) DEFAULT NULL COMMENT '个人简介',
      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
      `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '逻辑删除(0：未删除 1：已删除)',
      PRIMARY KEY (`id`) USING BTREE,
      UNIQUE KEY `uk_rookie_id` (`rookie_id`),
      UNIQUE KEY `uk_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='IM用户表';
