/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : fight

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2020-06-11 18:57:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for facility
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
INSERT INTO `facility` VALUES ('22', 'ff', 'fff', '2019-03-23 15:16:53', '../../../static/upload/1553325410054.jpg', '44', '0');
INSERT INTO `facility` VALUES ('23', 'ff', 'fff', '2019-03-23 15:16:53', null, '31', '0');
INSERT INTO `facility` VALUES ('24', 'ff', 'fff', '2019-03-23 15:16:53', null, '-1', '0');
INSERT INTO `facility` VALUES ('25', 'ff', 'fff', '2019-03-23 15:16:53', null, '31', '0');
INSERT INTO `facility` VALUES ('26', 'ff', 'fff', '2019-03-23 15:16:53', null, '44', '1');
INSERT INTO `facility` VALUES ('27', 'ff', 'fff', '2019-03-23 15:16:53', null, '44', '1');
INSERT INTO `facility` VALUES ('28', 'ff', 'fff', '2019-03-23 15:16:53', null, '44', '1');
INSERT INTO `facility` VALUES ('29', 'ff', 'fff', '2019-03-23 15:16:53', null, '44', '1');
INSERT INTO `facility` VALUES ('30', '试试0', '121221', '2019-03-25 13:52:20', '../../../static/upload/1553493185336.png', '1', '1');

-- ----------------------------
-- Table structure for permission
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '用户中心', '1', null, 'page/user/userList.html', '0');
INSERT INTO `permission` VALUES ('2', '系统设置', '1', null, 'page/user/userList.html', '0');
INSERT INTO `permission` VALUES ('3', '设备管理', '1', null, 'page/user/userList.html', '0');
INSERT INTO `permission` VALUES ('4', '使用文档', '1', null, 'page/user/userList.html', '0');
INSERT INTO `permission` VALUES ('9', '设备列表', '1', null, 'page/facility/facilityList.html', '3');
INSERT INTO `permission` VALUES ('10', '用户列表', '1', null, 'page/user/userList.html', '1');
INSERT INTO `permission` VALUES ('11', '角色列表', '1', null, 'page/role/roleList.html', '1');
INSERT INTO `permission` VALUES ('12', '系统日志', '1', null, 'page/log/logList.html', '2');
INSERT INTO `permission` VALUES ('14', '帮助手册', '1', null, 'page/doc/useDoc.html', '4');
INSERT INTO `permission` VALUES ('15', '语料列表', '1', null, 'page/corpus/corpusList.html', '3');
INSERT INTO `permission` VALUES ('16', '语料审核', '1', null, 'page/corpus/corpusList.html', '3');

-- ----------------------------
-- Table structure for role
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
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `is_delete` int(2) DEFAULT '0' COMMENT '是否删除  0  否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

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
INSERT INTO `role_permission` VALUES ('11', '1', '15', '0');

-- ----------------------------
-- Table structure for sys_logs
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
) ENGINE=InnoDB AUTO_INCREMENT=608 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_logs
-- ----------------------------
INSERT INTO `sys_logs` VALUES ('525', '用户模块', '登录', '2020-06-09 16:21:05', null, '用户登录');
INSERT INTO `sys_logs` VALUES ('526', '用户模块', '登录', '2020-06-09 16:21:54', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('527', '日志模块', '查询', '2020-06-09 16:21:59', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('528', '日志模块', '查询', '2020-06-09 16:23:47', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('529', '用户模块', '查询', '2020-06-09 16:23:49', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('530', '角色模块', '查询', '2020-06-09 16:23:51', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('531', '用户模块', '查询', '2020-06-09 16:24:19', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('532', '角色模块', '查询', '2020-06-09 16:24:19', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('533', '用户模块', '查询', '2020-06-09 16:29:28', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('534', '用户模块', '查询', '2020-06-09 16:29:37', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('535', '用户模块', '查询', '2020-06-09 16:29:41', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('536', '角色模块', '查询', '2020-06-09 16:30:21', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('537', '用户模块', '删除', '2020-06-09 16:42:16', null, '用户退出');
INSERT INTO `sys_logs` VALUES ('538', '用户模块', '登录', '2020-06-10 08:39:37', null, '用户登录');
INSERT INTO `sys_logs` VALUES ('539', '用户模块', '登录', '2020-06-10 08:39:42', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('540', '用户模块', '查询', '2020-06-10 08:39:59', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('541', '角色模块', '查询', '2020-06-10 08:40:00', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('542', '用户模块', '登录', '2020-06-10 08:44:32', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('543', '用户模块', '删除', '2020-06-10 08:52:43', null, '用户退出');
INSERT INTO `sys_logs` VALUES ('544', '用户模块', '登录', '2020-06-10 08:59:31', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('545', '日志模块', '查询', '2020-06-10 09:00:19', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('546', '日志模块', '查询', '2020-06-10 09:13:35', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('547', '角色模块', '查询', '2020-06-10 09:14:30', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('548', '用户模块', '查询', '2020-06-10 09:49:32', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('549', '角色模块', '查询', '2020-06-10 09:49:34', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('550', '日志模块', '查询', '2020-06-10 09:49:37', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('551', '用户模块', '登录', '2020-06-10 10:00:31', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('552', '用户模块', '登录', '2020-06-10 11:04:32', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('553', '用户模块', '查询', '2020-06-10 11:05:18', '我的名字是超管', '获取所有用户');
INSERT INTO `sys_logs` VALUES ('554', '用户模块', '登录', '2020-06-10 11:24:39', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('555', '用户模块', '登录', '2020-06-10 11:25:53', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('556', '用户模块', '登录', '2020-06-10 11:30:44', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('557', '日志模块', '查询', '2020-06-10 11:44:46', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('558', '日志模块', '查询', '2020-06-10 11:44:53', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('559', '日志模块', '查询', '2020-06-10 11:44:54', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('560', '日志模块', '查询', '2020-06-10 11:44:54', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('561', '用户模块', '登录', '2020-06-10 15:51:48', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('562', '用户模块', '查询', '2020-06-10 15:52:17', '我的名字是超管', '获取所有用户');
INSERT INTO `sys_logs` VALUES ('563', '用户模块', '登录', '2020-06-10 16:00:57', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('564', '用户模块', '登录', '2020-06-10 16:07:33', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('565', '用户模块', '登录', '2020-06-10 16:12:49', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('566', '用户模块', '登录', '2020-06-10 16:13:22', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('567', '用户模块', '登录', '2020-06-10 16:31:28', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('568', '用户模块', '登录', '2020-06-10 16:31:28', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('569', '用户模块', '登录', '2020-06-10 16:33:00', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('570', '用户模块', '登录', '2020-06-10 16:34:17', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('571', '用户模块', '登录', '2020-06-10 16:38:06', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('572', '用户模块', '登录', '2020-06-10 16:38:26', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('573', '用户模块', '登录', '2020-06-10 16:40:49', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('574', '用户模块', '登录', '2020-06-10 16:43:05', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('575', '用户模块', '登录', '2020-06-10 16:43:54', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('576', '用户模块', '登录', '2020-06-10 16:43:54', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('577', '用户模块', '登录', '2020-06-11 10:30:58', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('578', '用户模块', '删除', '2020-06-11 10:31:22', null, '用户退出');
INSERT INTO `sys_logs` VALUES ('579', '用户模块', '登录', '2020-06-11 10:38:33', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('580', '用户模块', '登录', '2020-06-11 10:39:00', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('581', '用户模块', '删除', '2020-06-11 10:39:26', null, '用户退出');
INSERT INTO `sys_logs` VALUES ('582', '用户模块', '登录', '2020-06-11 10:40:08', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('583', '用户模块', '删除', '2020-06-11 10:40:12', null, '用户退出');
INSERT INTO `sys_logs` VALUES ('584', '用户模块', '登录', '2020-06-11 10:40:48', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('585', '用户模块', '登录', '2020-06-11 10:45:15', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('586', '用户模块', '查询', '2020-06-11 10:46:54', '我的名字是超管', '获取所有用户');
INSERT INTO `sys_logs` VALUES ('587', '用户模块', '登录', '2020-06-11 10:48:05', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('588', '用户模块', '登录', '2020-06-11 10:50:21', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('589', '用户模块', '登录', '2020-06-11 10:50:21', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('590', '用户模块', '登录', '2020-06-11 10:51:27', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('591', '用户模块', '登录', '2020-06-11 10:51:27', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('592', '用户模块', '查询', '2020-06-11 10:51:30', '我的名字是超管', '获取所有用户');
INSERT INTO `sys_logs` VALUES ('593', '用户模块', '登录', '2020-06-11 10:53:07', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('594', '用户模块', '查询', '2020-06-11 10:53:11', '我的名字是超管', '获取所有用户');
INSERT INTO `sys_logs` VALUES ('595', '用户模块', '登录', '2020-06-11 10:54:42', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('596', '用户模块', '查询', '2020-06-11 10:54:45', '我的名字是超管', '获取所有用户');
INSERT INTO `sys_logs` VALUES ('597', '用户模块', '登录', '2020-06-11 16:07:19', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('598', '用户模块', '删除', '2020-06-11 16:07:25', null, '用户退出');
INSERT INTO `sys_logs` VALUES ('599', '用户模块', '登录', '2020-06-11 16:07:26', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('600', '用户模块', '登录', '2020-06-11 16:07:48', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('601', '用户模块', '登录', '2020-06-11 16:16:25', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('602', '用户模块', '登录', '2020-06-11 16:16:25', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('603', '用户模块', '登录', '2020-06-11 16:26:29', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('604', '用户模块', '登录', '2020-06-11 16:26:29', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('605', '用户模块', '登录', '2020-06-11 16:28:36', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('606', '角色模块', '查询', '2020-06-11 16:58:20', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('607', '用户模块', '查询', '2020-06-11 16:58:30', '我的名字是超管', '用户分页查询');

-- ----------------------------
-- Table structure for user
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
-- Table structure for user_facility
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
-- Table structure for user_role
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
