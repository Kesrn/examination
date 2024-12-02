/*
 Navicat Premium Data Transfer

 Source Server         : localhostOne
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : yly

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 01/08/2021 12:08:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for yly_healthy
-- ----------------------------
DROP TABLE IF EXISTS `yly_healthy`;
CREATE TABLE `yly_healthy`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `create_by` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `update_by` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yly_healthy
-- ----------------------------
INSERT INTO `yly_healthy` VALUES ('522425198109113949', '2021-03-23 22:16:59', '2021-03-23 22:16:59', '翟常轩', '翟常轩', '', 'userfiles/pic/2021/03/健康档案模板.doc');

-- ----------------------------
-- Table structure for yly_meal_info
-- ----------------------------
DROP TABLE IF EXISTS `yly_meal_info`;
CREATE TABLE `yly_meal_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `week` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `breakfast` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lunch` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dinner` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yly_meal_info
-- ----------------------------
INSERT INTO `yly_meal_info` VALUES (1, '星期一', '馒头', '馒头', '');
INSERT INTO `yly_meal_info` VALUES (2, '星期二', NULL, NULL, NULL);
INSERT INTO `yly_meal_info` VALUES (3, '星期三', NULL, NULL, NULL);
INSERT INTO `yly_meal_info` VALUES (4, '星期四', NULL, NULL, NULL);
INSERT INTO `yly_meal_info` VALUES (5, '星期五', NULL, NULL, NULL);
INSERT INTO `yly_meal_info` VALUES (6, '星期六', NULL, NULL, NULL);
INSERT INTO `yly_meal_info` VALUES (7, '星期日', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for yly_out_info
-- ----------------------------
DROP TABLE IF EXISTS `yly_out_info`;
CREATE TABLE `yly_out_info`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `out_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `out_reason` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `out_time_start` datetime NOT NULL,
  `out_time_end` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `create_by` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_del` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `out_days` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yly_out_info
-- ----------------------------
INSERT INTO `yly_out_info` VALUES ('1', '1', '1213213', '2021-01-24 00:00:00', '2021-01-25 00:00:00', '2021-01-24 00:24:24', '张明明', '0', '1天');
INSERT INTO `yly_out_info` VALUES ('522425198109113949', '1', 'mdasd a', '2021-01-26 00:00:00', '2021-01-28 00:00:00', '2021-01-26 23:23:47', '张明明', '2', '2天');

-- ----------------------------
-- Table structure for yly_question_back
-- ----------------------------
DROP TABLE IF EXISTS `yly_question_back`;
CREATE TABLE `yly_question_back`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `question_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `question_desc` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `question_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `create_by` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contact_info` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_del` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `question_pic` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yly_question_back
-- ----------------------------
INSERT INTO `yly_question_back` VALUES (8, '0', 'dsdasdasd', '1', '2020-12-27 16:56:21', '张明明', '倒萨大萨达傻吊阿萨德啊', '0', 'userfiles/pic/2020/12/659341.jpg');
INSERT INTO `yly_question_back` VALUES (9, '3', '还行没啥意见', '1', '2021-01-16 14:43:57', '张明明', '1873264817811', '0', 'userfiles/pic/2021/01/268302.png');
INSERT INTO `yly_question_back` VALUES (10, '1', '用着不太友好！', '2', '2021-01-16 21:50:25', '张明明', '526983659', '0', 'userfiles/pic/2021/01/789936.jpg');

-- ----------------------------
-- Table structure for yly_room_info
-- ----------------------------
DROP TABLE IF EXISTS `yly_room_info`;
CREATE TABLE `yly_room_info`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `room_no` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bed_one` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bed_two` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `create_by` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `update_time` datetime NOT NULL,
  `update_by` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_del` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cert_on_one` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cert_on_two` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yly_room_info
-- ----------------------------
INSERT INTO `yly_room_info` VALUES (1, 'RMe19c', '1', '1', '2021-01-24 22:30:39', 'admin', '2021-01-24 22:30:39', 'admin', NULL, '1', NULL, NULL);
INSERT INTO `yly_room_info` VALUES (2, 'RM2ef08045', '1', '1', '2021-01-24 22:46:09', 'admin', '2021-01-24 22:46:09', 'admin', NULL, '0', NULL, NULL);
INSERT INTO `yly_room_info` VALUES (3, 'RM066a05fe', '2', '1', '2021-01-26 22:07:20', '翟常轩', '2021-01-24 22:47:37', 'admin', '', '0', '522425198109113949', NULL);
INSERT INTO `yly_room_info` VALUES (4, 'RM5b026edb', '1', '1', '2021-01-26 21:34:25', '翟常轩', '2021-01-26 10:29:12', '翟常轩', '入住成功！', '1', '', NULL);

-- ----------------------------
-- Table structure for yly_the_old_info
-- ----------------------------
DROP TABLE IF EXISTS `yly_the_old_info`;
CREATE TABLE `yly_the_old_info`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `old_man_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `home_address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `contact_info_of_child` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `out_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `room_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `worker_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `create_by` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_del` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cert_no` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yly_the_old_info
-- ----------------------------
INSERT INTO `yly_the_old_info` VALUES (2, '何芳华', '1', '40', '光明西道', '18732648177', NULL, 'RM066a05feOne', '18732648178', '2021-01-16 23:31:34', '翟常轩', '45456465465', '0', '522425198109113949', '2021-01-17');
INSERT INTO `yly_the_old_info` VALUES (3, '翟常轩', '0', '0', '啦啦啦啦啦', '15232225247', NULL, NULL, NULL, '2021-03-24 22:41:05', '尹冠玉', '大大王多', '0', '13280119960811421X', '0199-08-01');

-- ----------------------------
-- Table structure for yly_user
-- ----------------------------
DROP TABLE IF EXISTS `yly_user`;
CREATE TABLE `yly_user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` char(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `update_date` datetime NOT NULL,
  `update_by` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yly_user
-- ----------------------------
INSERT INTO `yly_user` VALUES (1, 'zcx', 'f75f0892f793b2533e95b40098b9e599', '1', 'emN4', '0', 'admin', '18732648171', '2020-11-28 23:38:08.000000', '123', '2020-11-28 23:38:16.000000', '1', '1');
INSERT INTO `yly_user` VALUES (2, '葛辰宇', '8480ce8bd1262269be8cd97d5137fcbf', '0', 'Z2N5', '0', 'admin', '15003368802', '2020-11-29 15:26:41.664000', '1', '2021-01-16 15:07:12.983000', '翟常轩', 'ssss');
INSERT INTO `yly_user` VALUES (4, '翟常轩', '099297cc82570c86576776602eed484d', '0', '57+f5bi46L2p', '0', 'admin', '18732648178', '2020-12-06 21:39:18.901000', 'zcx', '2020-12-26 21:05:01.604000', '翟常轩', '大叔大婶');
INSERT INTO `yly_user` VALUES (6, '卢旭', '2a36e708b5842cc16b31b0df42e354ac', '0', '5Y2i5pet', '1', 'user', '18732648178', '2020-12-25 17:31:59.613000', '卢旭', '2020-12-25 17:31:59.613000', '卢旭', '翟常轩');
INSERT INTO `yly_user` VALUES (7, '张明明', '8dda1e55141746db31f3be0f7418cc45', '0', '5byg5piO5piO', '1', 'user', '18732648177', '2020-12-26 16:28:49.048000', '张明明', '2020-12-26 16:28:49.048000', '张明明', NULL);
INSERT INTO `yly_user` VALUES (8, '孔明', 'ffdb66b04d648829583bd9d5156fd5ed', '0', '5a2U5piO', '1', 'user', '13463160767', '2021-02-17 20:47:36.531000', '孔明', '2021-02-17 20:47:36.531000', '孔明', NULL);
INSERT INTO `yly_user` VALUES (9, '尹冠玉', '9abe5489ea9f30a89bd98793b820bfe9', '0', '5bC55Yag546J', '1', 'user', '15232225247', '2021-03-21 19:56:13.472000', '尹冠玉', '2021-03-21 19:56:13.472000', '尹冠玉', NULL);

-- ----------------------------
-- Table structure for yly_worker_info
-- ----------------------------
DROP TABLE IF EXISTS `yly_worker_info`;
CREATE TABLE `yly_worker_info`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `work_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `work_sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `work_age` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `work_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `working_time` datetime NOT NULL,
  `is_del` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yly_worker_info
-- ----------------------------
INSERT INTO `yly_worker_info` VALUES (1, '翟兆革', '0', '53', '18732648178', '1', '2021-01-26 09:37:39', '0', '----------------------------------');
INSERT INTO `yly_worker_info` VALUES (2, '张大壮', '0', '39', '13082070188', '0', '2021-02-21 21:16:44', '0', NULL);

SET FOREIGN_KEY_CHECKS = 1;
