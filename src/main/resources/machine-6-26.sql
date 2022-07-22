/*
Navicat MySQL Data Transfer

Source Server         : beauty_deviceX
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : beauty_deviceX

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2021-02-21 11:25:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS `beauty_deviceX`;
CREATE DATABASE beauty_deviceX;

USE beauty_deviceX;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `province_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of city
-- ----------------------------

-- ----------------------------
-- Table structure for machine_info
-- ----------------------------
DROP TABLE IF EXISTS `machine_info`;
CREATE TABLE `machine_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `machine_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备类型',
  `machine_function` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `machine_param` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备id，关键值',
  `machine_attribute` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int NOT NULL DEFAULT -1,
  `used_duration` int NULL DEFAULT NULL COMMENT '使用时长',
  `machine_brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `machine_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备状态',
  `machine_work_time_once` int NULL DEFAULT NULL COMMENT '单次使用时长',
  `machine_provice_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `machine_provice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `machine_city_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `machine_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cooperation_mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `online_status` int NOT NULL DEFAULT -1,
  `lastlogin_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lock_status` int NOT NULL DEFAULT 0,
  `store_id` int NOT NULL ,
  `other_parm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `device_sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `left_time` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`machine_param`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


INSERT INTO `machine_info` (`machine_type`, `machine_function`, `machine_param`, `machine_attribute`, `user_id`, `used_duration`, `machine_brand`, `user_name`, `machine_status`, `machine_work_time_once`, `machine_provice_id`, `machine_provice`, `machine_city_id`, `machine_city`, `create_time`, `cooperation_mode`, `online_status`, `lastlogin_time`, `lock_status`, `store_id`, `other_parm`,`device_sn`,`left_time`) VALUES ('white', 'tt', 'erewr324235', '352', 7, 1, 'oppo', 'ytuo', '1', 10, '1', 'shanghai', '2', 'shanghai', '2022-06-20', '1', -1, '2022-06-19', 0, 1, 'other','',0);
INSERT INTO `machine_info` (`machine_type`, `machine_function`, `machine_param`, `machine_attribute`, `user_id`, `used_duration`, `machine_brand`, `user_name`, `machine_status`, `machine_work_time_once`, `machine_provice_id`, `machine_provice`, `machine_city_id`, `machine_city`, `create_time`, `cooperation_mode`, `online_status`, `lastlogin_time`, `lock_status`, `store_id`, `other_parm`,`device_sn`,`left_time`) VALUES ('fx', 'tt', '45rfefdgsdfg', '352', 7, 1, 'oppo', 'ytuo', '1', 10, '1', 'shanghai', '2', 'shanghai', '2022-06-20', '1', -1, '2022-06-19', 0, 1, 'other','',0);


-- ----------------------------
-- Records of machine_info
-- ----------------------------

-- ----------------------------
-- Table structure for work_records
-- ----------------------------
DROP TABLE IF EXISTS `work_records`;
CREATE TABLE `work_records`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `used_duration` int(11) NULL DEFAULT NULL COMMENT '使用时长',
  `machine_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of province
-- ----------------------------

-- ----------------------------
-- Table structure for stores
-- ----------------------------
DROP TABLE IF EXISTS `t_stores`;
CREATE TABLE `t_stores`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contact_person` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contact_phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `agent_id` int NOT NULL DEFAULT -1 COMMENT '代理商id',
  `create_time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `store_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `device_count` int NOT NULL DEFAULT 0,
  `version` int NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Records of t_stores
-- ----------------------------
INSERT INTO `t_stores` VALUES (1, 's1', 'liaoning', '张三', '18800009999', 7, '2022年6月20日23:40:50', '2022年6月20日23:40:53', 0, 0);
INSERT INTO `t_stores` VALUES (2, 's2', 'liaoning', '李四', '18800001111',7, '2022年6月20日23:41:01', '2022年6月20日23:41:04', 0, 0);
INSERT INTO `t_stores` VALUES (3, 's3', 'hubei', '王五', '18800002222',10, '2022年6月20日23:41:01', '2022年6月20日23:41:04', 0, 0);


-- ----------------------------
-- Table structure for t_orders
-- ----------------------------
DROP TABLE IF EXISTS `t_orders`;
CREATE TABLE `t_orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_content` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `machine_param` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'device id',
  `machine_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci ,
  `store_id` int(11) NOT NULL  COMMENT 'store id',
  `st_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci  ,
  `price` int(11) NOT NULL DEFAULT 0,
  `order_status` int(11) NOT NULL DEFAULT 1,
  `pay_status` int(11) NOT NULL DEFAULT 1,
  `operation_time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `agent_id` int NOT NULL DEFAULT -1 COMMENT '代理商id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stores_count` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (7, 'guox', 'guox@126.com', '18640880000', 'beijing', '$2a$10$7XHoUDOhHao5KNBw6a6h9.OAugCAZEvXFbO9S/jqINfPn4.eZvq3C', 'admin', 'user', 'xx科技有限公司',0);
INSERT INTO `user_info` VALUES (8, 'ytuo', 'guo@126.com', '18640880000', 'shanghai', '$2a$10$ZhJ1jpbnwKPgV1tz7yil1.fwQyoPeoVNQ3sK2wkJbArhiVi3OcPDa', 'user', 'user', 'tt科技有限公司',0);
INSERT INTO `user_info` VALUES (9, 'guo', 'g@126.com', '18810500248', 'beijing', '$2a$10$uS6P6KJV8FGacOe6UfEVM.c2AV1FEVyFSEGJoWIjg4Zi.Qc5/nIBi', 'admin', 'admin', 'tt科技有限公司',0);
INSERT INTO `user_info` VALUES (10, 'wsq', 'g@126.com', '18810500248', 'beijing', '$2a$10$uS6P6KJV8FGacOe6UfEVM.c2AV1FEVyFSEGJoWIjg4Zi.Qc5/nIBi', 'admin', 'admin', 'tt科技有限公司',0);

-- ----------------------------
-- Table structure for work_records
-- ----------------------------
DROP TABLE IF EXISTS `work_records`;
CREATE TABLE `work_records`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `used_duration` int NULL DEFAULT NULL COMMENT 'used time',
  `machine_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of work_records
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

