use `dp_device`;
-- 增加设备备注信息字段
ALTER TABLE `d_bind_record` ADD COLUMN `user_remark` VARCHAR(255) NULL COMMENT '设备备注信息';