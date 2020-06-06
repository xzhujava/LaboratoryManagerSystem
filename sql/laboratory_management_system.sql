/*
 Navicat Premium Data Transfer

 Source Server         : xzhu
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 47.101.55.218:3306
 Source Schema         : laboratory_management_system

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 06/06/2020 17:45:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for laboratory
-- ----------------------------
DROP TABLE IF EXISTS `laboratory`;
CREATE TABLE `laboratory`  (
  `laboratory_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '实验室id',
  `laboratory_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '实验室名称',
  `laboratory_location` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '实验室地址',
  `status` int(10) DEFAULT NULL COMMENT '实验室状态 0：可预约 1：不可预约',
  PRIMARY KEY (`laboratory_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '实验室表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of laboratory
-- ----------------------------
INSERT INTO `laboratory` VALUES ('1', '实验室一', 'SY001', 0);
INSERT INTO `laboratory` VALUES ('2', '实验室二', 'SY002', 0);
INSERT INTO `laboratory` VALUES ('3', '实验室三', 'SY103', 0);
INSERT INTO `laboratory` VALUES ('4', '实验室四', 'SY204', 0);
INSERT INTO `laboratory` VALUES ('5', '实验室五', 'SY505', 0);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜单id',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  `menu_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单的前端id值',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('2', '实验室信息', 'laboratory');
INSERT INTO `menu` VALUES ('3', '学生管理', 'student');
INSERT INTO `menu` VALUES ('4', '预约记录管理', 'reservation');
INSERT INTO `menu` VALUES ('5', '实验室管理', 'admin-laboratory');

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation`  (
  `reservation_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '实验室预约记录id',
  `laboratory_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '实验室id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户id',
  `reservation_time` datetime(0) DEFAULT NULL COMMENT '实验室预约时间',
  `create_time` datetime(0) DEFAULT NULL COMMENT '预约记录时间',
  `score` double DEFAULT NULL COMMENT '实验成绩',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `status` int(10) DEFAULT NULL COMMENT '预约状态 0：已预约 1：已完成使用',
  PRIMARY KEY (`reservation_id`) USING BTREE,
  INDEX `laboratory_id`(`laboratory_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`laboratory_id`) REFERENCES `laboratory` (`laboratory_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '实验室预约记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reservation
-- ----------------------------
INSERT INTO `reservation` VALUES ('9775badffc62464d89d6098f61282746', '1', '662706c1240248709453242ba41c14ac', '2020-06-07 05:13:50', '2020-06-06 17:14:06', 0, '我要做实验真的做实验', 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色id',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `role_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色权限字符串',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '学生', 'student', '学生角色');
INSERT INTO `role` VALUES ('2', '管理员', 'admin', '管理员角色');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `menu_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`menu_id`, `role_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '2');
INSERT INTO `sys_role_menu` VALUES ('4', '2');
INSERT INTO `sys_role_menu` VALUES ('5', '2');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户id',
  PRIMARY KEY (`role_id`, `user_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `sys_role_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('2', '1');
INSERT INTO `sys_role_user` VALUES ('1', '662706c1240248709453242ba41c14ac');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户姓名',
  `user_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '学号或工号',
  `user_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户密码',
  `user_sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户性别',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张工', '20200604003', 'e10adc3949ba59abbe56e057f20f883e', '男', '系统管理员');
INSERT INTO `user` VALUES ('662706c1240248709453242ba41c14ac', 'Jack', '20200606001', 'e10adc3949ba59abbe56e057f20f883e', '男', '经济管理学院电子商务');

SET FOREIGN_KEY_CHECKS = 1;
