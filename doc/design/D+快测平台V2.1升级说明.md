
D+快测V2.1升级说明
=====================


--------------------------------------------------------
应用用户服务
------------
* 负责人:詹军政
* 更新说明: 增加应用用户信息(如所属行业行业,所属公司,公司地址等)的管理
* 升级步骤:
1. 登录生产环境mysql master节点
2. 执行数据库脚本
```sql
-- 创建数据库`user_app`
CREATE DATABASE IF NOT EXISTS `user_app`;

-- 创建应用用户表`dp_app_user_info`
CREATE TABLE IF NOT EXISTS `user_app`.`dp_app_user_info` (
  `user_id` CHAR(32)  NOT NULL COMMENT '用户ID',
  `user_name` VARCHAR(32) NOT NULL COMMENT '用户名称',
  `contact` VARCHAR(32) NULL COMMENT '联系人',
  `phone` VARCHAR(32) NULL COMMENT '联系人电话',
  `company_name` VARCHAR(128) NULL  COMMENT '公司名称',
  `company_address` VARCHAR(255) NULL COMMENT '公司地址',
  `qq` VARCHAR(32) NULL COMMENT 'QQ号码',
  `industry_ids` TEXT NULL COMMENT '用户所属行业ID，多个用,分割',
  `created_at` TIMESTAMP NOT NULL DEFAULT 0 COMMENT '创建时间',
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户所属行业ID，多个用,分割',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

```

--------------------------------------------------------




