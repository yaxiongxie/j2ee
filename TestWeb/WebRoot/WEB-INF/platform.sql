/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : platform

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-07-06 14:47:55
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
  `groupname` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_auth
-- ----------------------------
INSERT INTO `core_auth` VALUES ('1', 'coreperson', 'xx', 'systemmanager', '2016-07-04 16:37:01');
INSERT INTO `core_auth` VALUES ('2', 'corerole', 'xx', 'systemmanager', '2016-07-04 16:37:29');
INSERT INTO `core_auth` VALUES ('3', 'test1', 'yy', 'testgroup', '2016-07-04 16:38:24');
INSERT INTO `core_auth` VALUES ('4', 'test2', 'yy', 'testgroup', '2016-07-04 16:38:38');

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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_department
-- ----------------------------
INSERT INTO `core_department` VALUES ('1', 'company', '', '-1', '0', '2016-07-05 16:22:33');
INSERT INTO `core_department` VALUES ('5', '456123', '', '0', '0', '2016-06-29 10:28:24');
INSERT INTO `core_department` VALUES ('8', '456', '', '0', '0', '2016-06-29 10:39:05');
INSERT INTO `core_department` VALUES ('13', '7777', '', '0', '0', '2016-06-29 10:39:27');
INSERT INTO `core_department` VALUES ('23', '123', '', '20', '0', '2016-06-29 15:17:44');
INSERT INTO `core_department` VALUES ('27', 'department1', '', '1', '0', '2016-07-05 16:22:42');
INSERT INTO `core_department` VALUES ('28', 'department2', '', '1', '0', '2016-07-05 16:22:51');
INSERT INTO `core_department` VALUES ('30', 'department3', '', '1', '0', '2016-07-05 16:23:00');

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
  `birthday` varchar(255) DEFAULT NULL,
  `position_name` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_person
-- ----------------------------
INSERT INTO `core_person` VALUES ('28', '123', '123', '', '2', '123', '', '123', '123', '123', '2016-07-05 17:48:25', '1', '1');
INSERT INTO `core_person` VALUES ('29', '111', '111', '', '2', '111', '', '111', '111', '111', '2016-07-05 17:48:30', '1', '1');
INSERT INTO `core_person` VALUES ('30', '222', '222', null, '1', '222', null, '222', '222', '222', '2016-07-05 16:24:05', '27', '1');
INSERT INTO `core_person` VALUES ('31', '234', '234', null, '1', '234', null, '234', '234', '234', '2016-07-05 16:24:19', '27', '1');
INSERT INTO `core_person` VALUES ('32', '789', '789', null, '1', '789', null, '789', '789', '789', '2016-07-05 16:24:55', '28', '1');
INSERT INTO `core_person` VALUES ('33', '777', '777', null, '1', '7777', null, '777', '777', '777', '2016-07-05 16:25:08', '28', '1');

-- ----------------------------
-- Table structure for core_person_role
-- ----------------------------
DROP TABLE IF EXISTS `core_person_role`;
CREATE TABLE `core_person_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_person_role
-- ----------------------------
INSERT INTO `core_person_role` VALUES ('13', '11', '28');
INSERT INTO `core_person_role` VALUES ('14', '11', '29');
INSERT INTO `core_person_role` VALUES ('15', '11', '30');
INSERT INTO `core_person_role` VALUES ('16', '11', '31');
INSERT INTO `core_person_role` VALUES ('17', '11', '32');
INSERT INTO `core_person_role` VALUES ('18', '11', '33');
INSERT INTO `core_person_role` VALUES ('19', '13', '28');
INSERT INTO `core_person_role` VALUES ('20', '13', '29');
INSERT INTO `core_person_role` VALUES ('21', '13', '30');
INSERT INTO `core_person_role` VALUES ('22', '13', '31');
INSERT INTO `core_person_role` VALUES ('27', '17', '30');
INSERT INTO `core_person_role` VALUES ('28', '17', '31');
INSERT INTO `core_person_role` VALUES ('29', '17', '32');
INSERT INTO `core_person_role` VALUES ('30', '17', '33');
INSERT INTO `core_person_role` VALUES ('31', '18', '30');
INSERT INTO `core_person_role` VALUES ('32', '18', '31');

-- ----------------------------
-- Table structure for core_role
-- ----------------------------
DROP TABLE IF EXISTS `core_role`;
CREATE TABLE `core_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pcount` int(11) DEFAULT NULL,
  `names` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_role
-- ----------------------------
INSERT INTO `core_role` VALUES ('11', 'xieyaxiong', '6', '123,111,222,234,789,777', '2016-07-06 14:06:18');
INSERT INTO `core_role` VALUES ('13', 'xieyaxiong', '4', '123,111,222,234', '2016-07-05 11:12:27');

-- ----------------------------
-- Table structure for core_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `core_role_auth`;
CREATE TABLE `core_role_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `auth_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_role_auth
-- ----------------------------
INSERT INTO `core_role_auth` VALUES ('15', '13', '1');
INSERT INTO `core_role_auth` VALUES ('16', '13', '2');
INSERT INTO `core_role_auth` VALUES ('20', '11', '1');
INSERT INTO `core_role_auth` VALUES ('21', '11', '2');
INSERT INTO `core_role_auth` VALUES ('22', '11', '3');
INSERT INTO `core_role_auth` VALUES ('23', '11', '4');

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
