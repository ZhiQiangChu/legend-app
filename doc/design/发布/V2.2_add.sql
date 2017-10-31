-- ----------------------------
-- dp_user
-- ----------------------------
use dp_user;
-- 创建表 `dp_organization`
CREATE TABLE IF NOT EXISTS `dp_organization` (
  `id` char(32) NOT NULL COMMENT '组织ID',
  `platform_id` char(32) NOT NULL COMMENT '平台ID',
  `name` varchar(255) NOT NULL COMMENT '组织名称',
  `short_name` varchar(32) DEFAULT NULL COMMENT '组织简称',
  `description` varchar(512) DEFAULT NULL COMMENT '组织介绍',
  `type` tinyint(4) unsigned DEFAULT '0' COMMENT '组织分类（0：默认/1：区域/2：企业/3：事业单位/4：社会团体/5：机关/6：其它机构/7：部门/8：团队）',
  `pid` char(32) NOT NULL DEFAULT '0' COMMENT '上级组织ID',
  `area_name` varchar(64) DEFAULT NULL COMMENT '区域名称',
  `area_code` char(12) DEFAULT NULL COMMENT '区域编码',
  `contact` varchar(32) DEFAULT NULL COMMENT '联系人',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `phone` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `fax` varchar(32) DEFAULT NULL COMMENT '传真',
  `is_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否激活',
  `level` tinyint(5) NOT NULL DEFAULT '0' COMMENT '组织级别',
  `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `updated_at` timestamp NULL DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`),
  KEY `index_platform_id` (`platform_id`),
  KEY `index_pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织表';

-- 创建表 `dp_organization_user`
CREATE TABLE IF NOT EXISTS `dp_organization_user` (
  `id` char(32) NOT NULL COMMENT '组织用户ID',
  `org_id` char(32) NOT NULL COMMENT '组织ID',
  `user_id` char(32) NOT NULL COMMENT '用户ID',
  `is_admin` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否管理员',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `updated_at` timestamp NULL DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_org_user` (`org_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织用户关系表';

-- ----------------------------
-- dp_platform
-- ----------------------------
use dp_platform;
-- 创建表`dp_organization_app`
CREATE TABLE IF NOT EXISTS `dp_organization_app` (
  `id` char(32) NOT NULL COMMENT '组织应用ID',
  `org_id` char(32) NOT NULL COMMENT '组织ID',
  `app_id` char(32) NOT NULL COMMENT '应用ID',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `updated_at` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- dp_message
-- ----------------------------
-- 先创建数据库`dp_message`
CREATE DATABASE `dp_message`;
use dp_message;

-- Table `message` 消息表
CREATE TABLE `dp_message`.`message` (
  `id` char(32) NOT NULL COMMENT '消息ID',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `msg_type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '消息分类：0：通告，1：消息，2：提醒, 3:即时,4:站内信',
  `priority` tinyint(3) NOT NULL DEFAULT '2' COMMENT '优先级：0:紧急,1:重要,2:普通',
  `sender_type` tinyint(3) DEFAULT NULL COMMENT '发送对象类型:0:系统，1：客服，2：用户',
  `receiver_type` tinyint(4) NOT NULL COMMENT '接收对象类型:0:全体，1：平台，2：应用，3：分组，4：指定用户，5：机构',
  `reply_msg_id` char(32) DEFAULT NULL COMMENT '回复消息编号',
  `platform_id` char(32) DEFAULT NULL COMMENT '平台编号',
  `app_id` char(32) DEFAULT NULL COMMENT '应用编号',
  `group_id` char(32) DEFAULT NULL COMMENT '用户分组编号',
  `org_id` char(32) DEFAULT NULL COMMENT '组织ID',
  `sender_id` char(32) DEFAULT NULL COMMENT '发送人用户ID',
  `is_revoked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否撤销',
  `revoked_at` timestamp NULL DEFAULT NULL COMMENT '撤销时间',
  `is_removed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `removed_at` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `sel_email` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否有选择邮件发送',
  `sel_sms` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否有选择短信发送',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Table `user_msg_subscribe` 用户标记表
CREATE TABLE `dp_message`.`user_msg_subscribe` (
  `msg_id` char(32) NOT NULL COMMENT '消息ID',
  `user_id` char(32) NOT NULL COMMENT '用户ID',
  `is_read` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否阅读',
  `read_at` timestamp NULL DEFAULT NULL COMMENT '阅读时间',
  `is_removed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `remove_at` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`msg_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




