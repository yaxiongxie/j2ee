/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : platform

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-06-24 10:52:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for core_auth
-- ----------------------------
DROP TABLE IF EXISTS `core_auth`;
CREATE TABLE `core_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `function_name` varchar(255) DEFAULT NULL,
  `function_url` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_auth
-- ----------------------------

-- ----------------------------
-- Table structure for core_department
-- ----------------------------
DROP TABLE IF EXISTS `core_department`;
CREATE TABLE `core_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_department
-- ----------------------------

-- ----------------------------
-- Table structure for core_person
-- ----------------------------
DROP TABLE IF EXISTS `core_person`;
CREATE TABLE `core_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `ismarray` int(11) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `home_address` varchar(255) DEFAULT NULL,
  `recent_address` varchar(255) DEFAULT NULL,
  `profession_name` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `position_name` varchar(255) DEFAULT NULL,
  `pid` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_person
-- ----------------------------

-- ----------------------------
-- Table structure for core_person_role
-- ----------------------------
DROP TABLE IF EXISTS `core_person_role`;
CREATE TABLE `core_person_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_person_role
-- ----------------------------

-- ----------------------------
-- Table structure for core_role
-- ----------------------------
DROP TABLE IF EXISTS `core_role`;
CREATE TABLE `core_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_role
-- ----------------------------
INSERT INTO `core_role` VALUES ('1', 'test', '2016-06-24 10:22:51');
INSERT INTO `core_role` VALUES ('2', 'test', '2016-06-24 10:24:00');
INSERT INTO `core_role` VALUES ('3', 'test', '2016-06-24 10:32:24');
INSERT INTO `core_role` VALUES ('4', 'test', '2016-06-24 10:32:51');
INSERT INTO `core_role` VALUES ('5', 'test', '2016-06-24 10:33:07');
INSERT INTO `core_role` VALUES ('6', 'test', '2016-06-24 10:34:46');
INSERT INTO `core_role` VALUES ('7', 'test', '2016-06-24 10:36:05');

-- ----------------------------
-- Table structure for core_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `core_role_auth`;
CREATE TABLE `core_role_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `auth_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_role_auth
-- ----------------------------

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', 'xieyaxiong');
INSERT INTO `test` VALUES ('2', 'xieyaxiong');
INSERT INTO `test` VALUES ('3', 'xieyaxiong');
INSERT INTO `test` VALUES ('4', 'xieyaxiong');
INSERT INTO `test` VALUES ('5', 'xieyaxiong');
INSERT INTO `test` VALUES ('6', 'xieyaxiong');
INSERT INTO `test` VALUES ('7', 'xieyaxiong');
INSERT INTO `test` VALUES ('8', 'xieyaxiong');
INSERT INTO `test` VALUES ('9', 'xieyaxiong');
INSERT INTO `test` VALUES ('10', 'xieyaxiong');
INSERT INTO `test` VALUES ('11', 'xieyaxiong');
