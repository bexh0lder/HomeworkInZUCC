/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : course

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2020-03-11 13:38:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `eID` varchar(9) NOT NULL,
  `storeId` varchar(10) DEFAULT NULL,
  `emp_eID` varchar(9) DEFAULT NULL,
  `eName` varchar(20) DEFAULT NULL,
  `eAge` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`eID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
