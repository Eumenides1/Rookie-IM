DROP TABLE IF EXISTS `user_apply`;
CREATE TABLE `user_apply` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` bigint(20) NOT NULL COMMENT '申请人uid',
  `type` int(11) NOT NULL COMMENT '申请类型 1加好友',
  `target_id` bigint(20) NOT NULL COMMENT '接收人uid',
  `msg` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '申请信息',
  `status` int(11) NOT NULL COMMENT '申请状态 1待审批 2同意',
  `read_status` int(11) NOT NULL COMMENT '阅读状态 1未读 2已读',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_uid_target_id` (`uid`,`target_id`) USING BTREE,
  KEY `idx_target_id_read_status` (`target_id`,`read_status`) USING BTREE,
  KEY `idx_target_id` (`target_id`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE,
  KEY `idx_update_time` (`update_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户申请表';

DROP TABLE IF EXISTS `user_friend`;
CREATE TABLE `user_friend` (
   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
   `uid` bigint(20) NOT NULL COMMENT 'uid',
   `friend_uid` bigint(20) NOT NULL COMMENT '好友uid',
   `delete_status` int(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除(0-正常,1-删除)',
   `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
   `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
   PRIMARY KEY (`id`) USING BTREE,
   KEY `idx_uid_friend_uid` (`uid`,`friend_uid`) USING BTREE,
   KEY `idx_create_time` (`create_time`) USING BTREE,
   KEY `idx_update_time` (`update_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户联系人表';