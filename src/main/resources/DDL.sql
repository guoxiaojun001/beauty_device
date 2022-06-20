/*
Navicat MySQL Data Transfer

Source Server         : beauty_device
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : beauty_device

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2021-02-21 11:25:50
*/


SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS `beauty_device`;
CREATE DATABASE beauty_device;

USE beauty_device;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `province_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for machine_infos
-- ----------------------------
DROP TABLE IF EXISTS `machine_info`;
CREATE TABLE `machine_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `machine_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT  NULL COMMENT '设备类型',
  `machine_function` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `machine_param` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备id，关键值',
  `machine_attribute` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NOT NULL DEFAULT -1,
  `used_duration` int(11) NULL DEFAULT NULL COMMENT '使用时长',
  `machine_brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT  NULL COMMENT '品牌',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `machine_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT  NULL COMMENT '设备状态',
  `machine_work_time_once` int(11) NULL DEFAULT NULL COMMENT '单次使用时长',
  `machine_provice_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `machine_provice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `machine_city_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `machine_city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cooperation_mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `online_status` int(10) NOT NULL DEFAULT -1,
  `lastlogin_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
  `lock_status` int(11) NOT NULL DEFAULT -1,
  `store_id` int(10) NOT NULL DEFAULT -1,
  `other_parm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,


  PRIMARY KEY (`machine_param`),
  KEY `id` (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for stores
-- ----------------------------
DROP TABLE IF EXISTS `stores`;
CREATE TABLE `stores`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contact_person` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `agent_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '代理商id',
  `create_time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `store_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `device_count` int(11) NOT NULL DEFAULT 0,
  `version` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_content` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `machine_param` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备id',
  `price` int(11) NOT NULL DEFAULT 0,
  `order_status` int(11) NOT NULL DEFAULT -1,
  `pay_status` int(11) NOT NULL DEFAULT -1,
  `operation_time` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for user_info`id`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

