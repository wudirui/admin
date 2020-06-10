/*
Navicat MySQL Data Transfer

Source Server         : q
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : fight

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2019-03-25 16:49:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `facility`
-- ----------------------------
DROP TABLE IF EXISTS `facility`;
CREATE TABLE `facility` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备id',
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `leave_date` datetime DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `is_delete` int(2) DEFAULT '0' COMMENT '是否删除 0 假 1 真',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of facility
-- ----------------------------
INSERT INTO `facility` VALUES ('21', '笔记本', 'msi  ge-60', '2019-03-23 14:12:38', '../../../static/upload/1553321568728.jpg', '1', '0');
INSERT INTO `facility` VALUES ('22', 'ff', 'fff', '2019-03-23 15:16:53', '../../../static/upload/1553325410054.jpg', '44', '1');
INSERT INTO `facility` VALUES ('23', 'ff', 'fff', '2019-03-23 15:16:53', null, '31', '1');
INSERT INTO `facility` VALUES ('24', 'ff', 'fff', '2019-03-23 15:16:53', null, '-1', '1');
INSERT INTO `facility` VALUES ('25', 'ff', 'fff', '2019-03-23 15:16:53', null, '31', '1');
INSERT INTO `facility` VALUES ('26', 'ff', 'fff', '2019-03-23 15:16:53', null, '44', '1');
INSERT INTO `facility` VALUES ('27', 'ff', 'fff', '2019-03-23 15:16:53', null, '44', '1');
INSERT INTO `facility` VALUES ('28', 'ff', 'fff', '2019-03-23 15:16:53', null, '44', '1');
INSERT INTO `facility` VALUES ('29', 'ff', 'fff', '2019-03-23 15:16:53', null, '44', '1');
INSERT INTO `facility` VALUES ('30', '试试0', '121221', '2019-03-25 13:52:20', '../../../static/upload/1553493185336.png', '1', '1');

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT '0' COMMENT '0 角色  1 菜单',
  `desc` varchar(50) DEFAULT NULL,
  `menu_url` varchar(50) DEFAULT NULL,
  `parent_id` int(11) DEFAULT '0' COMMENT '菜单 父级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '用户中心', '1', null, 'page/user/userList.html', '0');
INSERT INTO `permission` VALUES ('2', '系统设置', '1', null, 'page/user/userList.html', '0');
INSERT INTO `permission` VALUES ('3', '设备管理', '1', null, 'page/user/userList.html', '0');
INSERT INTO `permission` VALUES ('4', '使用文档', '1', null, 'page/user/userList.html', '0');
INSERT INTO `permission` VALUES ('9', '设备列表', '1', null, 'page/facility/corpusList.html', '3');
INSERT INTO `permission` VALUES ('10', '用户列表', '1', null, 'page/user/userList.html', '1');
INSERT INTO `permission` VALUES ('11', '角色列表', '1', null, 'page/role/roleList.html', '1');
INSERT INTO `permission` VALUES ('12', '系统日志', '1', null, 'page/log/logList.html', '2');
INSERT INTO `permission` VALUES ('14', '帮助手册', '1', null, 'page/doc/useDoc.html', '4');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT '0',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除 0 否 1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '超级管理员', '0');
INSERT INTO `role` VALUES ('2', '设备管理员', '设备管理员', '0');
INSERT INTO `role` VALUES ('3', '普通用户', '普通用户', '0');
INSERT INTO `role` VALUES ('14', '日志管理员', '管理日志', '0');
INSERT INTO `role` VALUES ('15', '越权 添加的 角色', '单打独斗', '0');
INSERT INTO `role` VALUES ('16', '日志管理1', '111', '0');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `is_delete` int(2) DEFAULT '0' COMMENT '是否删除  0  否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '9', '0');
INSERT INTO `role_permission` VALUES ('2', '1', '10', '0');
INSERT INTO `role_permission` VALUES ('3', '1', '11', '0');
INSERT INTO `role_permission` VALUES ('4', '1', '12', '0');
INSERT INTO `role_permission` VALUES ('5', '2', '9', '0');
INSERT INTO `role_permission` VALUES ('6', '1', '14', '0');
INSERT INTO `role_permission` VALUES ('7', '3', '14', '0');
INSERT INTO `role_permission` VALUES ('8', '2', '14', '0');
INSERT INTO `role_permission` VALUES ('9', '14', '12', '0');
INSERT INTO `role_permission` VALUES ('10', '16', '12', '0');

-- ----------------------------
-- Table structure for `sys_logs`
-- ----------------------------
DROP TABLE IF EXISTS `sys_logs`;
CREATE TABLE `sys_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module` varchar(255) DEFAULT NULL COMMENT '所属模块',
  `type` varchar(255) DEFAULT NULL COMMENT '操作类型',
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `log_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=525 DEFAULT CHARSET=utf8;
 

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `realname` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dept` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `is_delete` int(2) DEFAULT '0' COMMENT '是否删除 0 否 1 是',
  `sex` int(2) DEFAULT '0' COMMENT '0 男 1 女',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '我的名字是超管', 'aa.com', 'dddddddddd', 'd', null, '0', '1');
INSERT INTO `user` VALUES ('31', 'shebei', '123456', '我是设备管理员', '18337181234', '就住在那个屯', '我的老家 啊', null, '0', '0');
INSERT INTO `user` VALUES ('35', 'putong', '123456', '普通用户啊', '0371-99999999', '来最右，粘贴你在知乎复制的故事', '来知乎，分享你刚编的故事', null, '0', '0');
INSERT INTO `user` VALUES ('47', 'xinlaide', '123456', '我是新来的', '1', '134', '12', null, '1', '0');
INSERT INTO `user` VALUES ('48', '54', '123456', '4', '4', '4', '4', null, '1', '0');
INSERT INTO `user` VALUES ('49', 'fff', '123456', '3333', '3', '3', '3', null, '0', '0');
INSERT INTO `user` VALUES ('50', 'rizhi', '123456', '日志', '1', '1', '1', null, '0', '0');
INSERT INTO `user` VALUES ('51', 'rizhiguanli', '123456', 'rizhi', '13215488745', '111', '11', null, '0', '0');

-- ----------------------------
-- Table structure for `user_facility`
-- ----------------------------
DROP TABLE IF EXISTS `user_facility`;
CREATE TABLE `user_facility` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_facility
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', null, '1', '1');
INSERT INTO `user_role` VALUES ('2', null, '2', '2');
INSERT INTO `user_role` VALUES ('3', null, '3', '2');
INSERT INTO `user_role` VALUES ('4', null, '4', '3');
INSERT INTO `user_role` VALUES ('5', null, '30', '2');
INSERT INTO `user_role` VALUES ('6', null, '31', '2');
INSERT INTO `user_role` VALUES ('7', null, '32', '3');
INSERT INTO `user_role` VALUES ('8', null, '33', null);
INSERT INTO `user_role` VALUES ('9', null, '34', null);
INSERT INTO `user_role` VALUES ('10', null, '35', '3');
INSERT INTO `user_role` VALUES ('11', null, '36', '3');
INSERT INTO `user_role` VALUES ('12', null, '40', '3');
INSERT INTO `user_role` VALUES ('13', null, '41', '2');
INSERT INTO `user_role` VALUES ('14', null, '42', '1');
INSERT INTO `user_role` VALUES ('15', null, '43', '1');
INSERT INTO `user_role` VALUES ('16', null, '44', '1');
INSERT INTO `user_role` VALUES ('17', null, '45', '2');
INSERT INTO `user_role` VALUES ('18', null, '46', '3');
INSERT INTO `user_role` VALUES ('19', null, '47', '2');
INSERT INTO `user_role` VALUES ('20', null, '48', '3');
INSERT INTO `user_role` VALUES ('21', null, '49', '3');
INSERT INTO `user_role` VALUES ('22', null, '50', '14');
INSERT INTO `user_role` VALUES ('23', null, '51', '16');
