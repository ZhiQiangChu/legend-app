-- ----------------------------
-- dp_user
-- ----------------------------
use dp_user;
-- 更新 `dp_base_user_info`,新增两个字段
ALTER TABLE `dp_base_user_info`
ADD COLUMN `user_type`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '0：注册用户   1：组织分配用户' AFTER `password_salt`,
ADD COLUMN `pwd_modify`  tinyint(1) NOT NULL DEFAULT 0 COMMENT '0：没有修改密码 1：修改过密码' AFTER `user_type`;

-- ----------------------------
-- user_app
-- ----------------------------
use user_app;
-- 更新`dp_app_user_info`,增加字段
ALTER TABLE `dp_app_user_info`
ADD COLUMN `full_name` varchar(32) NULL  COMMENT '用户姓名' AFTER `user_name`,
ADD COLUMN `position` varchar(32) NULL COMMENT '职位' AFTER `company_address`;

-- ----------------------------
-- dp_platform
-- ----------------------------
use dp_platform;
-- 修改`dp_user_app`,增加字段
ALTER TABLE `dp_user_app`
ADD COLUMN `type`  tinyint(2) NOT NULL DEFAULT 0 COMMENT '类型,0:用户添加 1：组织添加' AFTER `is_active`;

-- ----------------------------
-- dp_device
-- ----------------------------
use `dp_device`;
-- 更新表`d_bind_record`增加设备备注信息字段
ALTER TABLE `d_bind_record` ADD COLUMN `user_remark` VARCHAR(255) NULL COMMENT '设备备注信息';


