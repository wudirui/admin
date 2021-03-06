/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : fight

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2020-07-02 18:41:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content
-- ----------------------------

-- ----------------------------
-- Table structure for corpus
-- ----------------------------
DROP TABLE IF EXISTS `corpus`;
CREATE TABLE `corpus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sentence_id` int(11) DEFAULT NULL,
  `recorder_name` char(11) DEFAULT NULL,
  `sex` varchar(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dialect` varchar(255) DEFAULT NULL,
  `audio` longtext,
  `status` char(11) DEFAULT NULL,
  `create_time` char(11) DEFAULT NULL,
  `del` char(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of corpus
-- ----------------------------
INSERT INTO `corpus` VALUES ('1', '46', '张瑞', '1', null, '1', '12', '1', null, '0');
INSERT INTO `corpus` VALUES ('2', '46', '2', null, null, null, '3qw', '2', null, '0');
INSERT INTO `corpus` VALUES ('3', '47', null, null, null, null, '12', '1', null, '0');
INSERT INTO `corpus` VALUES ('4', '4', null, null, null, null, null, '1', null, '0');
INSERT INTO `corpus` VALUES ('5', null, '5', null, null, null, null, null, null, '0');
INSERT INTO `corpus` VALUES ('6', null, null, null, null, null, null, null, null, '0');
INSERT INTO `corpus` VALUES ('7', null, null, null, null, null, null, null, null, '0');
INSERT INTO `corpus` VALUES ('8', null, null, null, null, null, null, '1', null, '0');
INSERT INTO `corpus` VALUES ('9', null, null, null, null, null, null, null, null, '0');
INSERT INTO `corpus` VALUES ('10', null, null, null, null, null, null, null, null, '0');
INSERT INTO `corpus` VALUES ('11', null, null, null, null, null, null, null, null, '0');
INSERT INTO `corpus` VALUES ('12', null, null, null, null, null, null, null, null, null);
INSERT INTO `corpus` VALUES ('13', null, null, null, null, null, null, null, null, null);
INSERT INTO `corpus` VALUES ('14', null, null, null, null, null, null, null, null, null);
INSERT INTO `corpus` VALUES ('15', null, null, null, null, null, null, null, null, null);

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of facility
-- ----------------------------
INSERT INTO `facility` VALUES ('21', '笔记本', 'msi  ge-60', '2019-03-23 14:12:38', '../../../static/upload/1553321568728.jpg', '1', '0');
INSERT INTO `facility` VALUES ('22', 'ff', 'fff', '2019-03-23 15:16:53', '../../../static/upload/1553325410054.jpg', '44', '0');
INSERT INTO `facility` VALUES ('23', 'ff', 'fff', '2019-03-23 15:16:53', null, '31', '0');
INSERT INTO `facility` VALUES ('24', 'ff', 'fff', '2019-03-23 15:16:53', null, '-1', '0');
INSERT INTO `facility` VALUES ('25', 'ff', 'fff', '2019-03-23 15:16:53', null, '31', '0');
INSERT INTO `facility` VALUES ('26', 'ff', 'fff', '2019-03-23 15:16:53', null, '44', '0');
INSERT INTO `facility` VALUES ('27', 'ff', 'fff', '2019-03-23 15:16:53', null, '44', '0');
INSERT INTO `facility` VALUES ('28', 'ff', 'fff', '2019-03-23 15:16:53', null, '44', '0');
INSERT INTO `facility` VALUES ('29', 'ff', 'fff', '2019-03-23 15:16:53', null, '44', '0');
INSERT INTO `facility` VALUES ('30', '试试0', '121221', '2019-03-25 13:52:20', '../../../static/upload/1553493185336.png', '1', '0');
INSERT INTO `facility` VALUES ('31', null, null, null, null, null, '21');
INSERT INTO `facility` VALUES ('32', null, null, null, null, null, '22');
INSERT INTO `facility` VALUES ('33', null, null, null, null, null, '23');
INSERT INTO `facility` VALUES ('34', null, null, null, null, null, '24');
INSERT INTO `facility` VALUES ('35', null, null, null, null, null, '25');
INSERT INTO `facility` VALUES ('36', null, null, null, null, null, '26');
INSERT INTO `facility` VALUES ('37', null, null, null, null, null, '27');
INSERT INTO `facility` VALUES ('38', null, null, null, null, null, '28');
INSERT INTO `facility` VALUES ('39', null, null, null, null, null, '29');
INSERT INTO `facility` VALUES ('40', null, null, null, null, null, '30');
INSERT INTO `facility` VALUES ('60', '笔记本', 'msi  ge-60', '2019-03-23 14:12:38', '../../../static/upload/1553321568728.jpg', '1', '0');
INSERT INTO `facility` VALUES ('61', 'ff', 'fff', '2019-03-23 15:16:53', '../../../static/upload/1553325410054.jpg', '44', '0');
INSERT INTO `facility` VALUES ('62', 'ff', 'fff', '2019-03-23 15:16:53', '', '31', '0');
INSERT INTO `facility` VALUES ('63', 'ff', 'fff', '2019-03-23 15:16:53', '', '-1', '0');
INSERT INTO `facility` VALUES ('64', 'ff', 'fff', '2019-03-23 15:16:53', '', '31', '0');
INSERT INTO `facility` VALUES ('65', 'ff', 'fff', '2019-03-23 15:16:53', '', '44', '0');
INSERT INTO `facility` VALUES ('66', 'ff', 'fff', '2019-03-23 15:16:53', '', '44', '0');
INSERT INTO `facility` VALUES ('67', 'ff', 'fff', '2019-03-23 15:16:53', '', '44', '0');
INSERT INTO `facility` VALUES ('68', 'ff', 'fff', '2019-03-23 15:16:53', '', '44', '0');

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
  `del` int(11) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '用户中心', '1', null, 'page/user/userList.html', '1', '0');
INSERT INTO `permission` VALUES ('2', '系统设置', '1', null, 'page/user/userList.html', '2', '0');
INSERT INTO `permission` VALUES ('3', '设备管理', '1', null, 'page/user/userList.html', '0', '0');
INSERT INTO `permission` VALUES ('4', '使用文档', '2', null, 'page/user/userList.html', '0', '0');
INSERT INTO `permission` VALUES ('9', '设备列表', '2', null, 'page/facility/facilityList.html', '3', '0');
INSERT INTO `permission` VALUES ('10', '用户列表', '2', null, 'page/user/userList.html', '1', '0');
INSERT INTO `permission` VALUES ('11', '角色列表', '2', null, 'page/role/roleList.html', '1', '0');
INSERT INTO `permission` VALUES ('12', '系统日志', '2', null, 'page/log/logList.html', '2', '0');
INSERT INTO `permission` VALUES ('14', '帮助手册', '2', null, 'page/doc/useDoc.html', '4', '0');
INSERT INTO `permission` VALUES ('15', '语料审核', '2', null, 'page/corpus/corpusList.html', '3', '0');
INSERT INTO `permission` VALUES ('16', '语料列表', '2', null, 'page/sentence/sentenceList.html', '3', '0');
INSERT INTO `permission` VALUES ('17', '菜单管理', '2', null, 'page/menu/menuList.html', '2', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT '0',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除 0 否 1 是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
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
INSERT INTO `role_permission` VALUES ('12', '1', '16', '0');
INSERT INTO `role_permission` VALUES ('13', '1', '17', '0');

-- ----------------------------
-- Table structure for sentence
-- ----------------------------
DROP TABLE IF EXISTS `sentence`;
CREATE TABLE `sentence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sentence` varchar(255) DEFAULT NULL,
  `del` varchar(11) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sentence
-- ----------------------------
INSERT INTO `sentence` VALUES ('46', 'hello world', '0', null);
INSERT INTO `sentence` VALUES ('47', '山东分第', '0', null);
INSERT INTO `sentence` VALUES ('48', '山东分第', '0', null);
INSERT INTO `sentence` VALUES ('49', '232.0', '0', null);
INSERT INTO `sentence` VALUES ('50', '地方', '0', null);
INSERT INTO `sentence` VALUES ('51', '山东大', '1', null);
INSERT INTO `sentence` VALUES ('52', 'hello world', '0', '2020-06-14 14:46:57');
INSERT INTO `sentence` VALUES ('53', '山东分第', '0', '2020-06-14 14:46:57');
INSERT INTO `sentence` VALUES ('54', '山东分第', '0', '2020-06-14 14:46:57');
INSERT INTO `sentence` VALUES ('55', '232.0', '0', '2020-06-14 14:46:57');
INSERT INTO `sentence` VALUES ('56', '地方', '0', '2020-06-14 14:46:57');
INSERT INTO `sentence` VALUES ('57', '山东大', '0', '2020-06-14 14:46:57');
INSERT INTO `sentence` VALUES ('58', 'hello world', '0', '2020-06-14 15:22:30');
INSERT INTO `sentence` VALUES ('59', '山东分第', '0', '2020-06-14 15:22:30');
INSERT INTO `sentence` VALUES ('60', '山东分第', '0', '2020-06-14 15:22:30');
INSERT INTO `sentence` VALUES ('61', '232.0', '0', '2020-06-14 15:22:30');
INSERT INTO `sentence` VALUES ('62', '地方', '0', '2020-06-14 15:22:30');
INSERT INTO `sentence` VALUES ('63', 'हैलो', '0', '2020-06-14 15:22:30');

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=986 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

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
INSERT INTO `sys_logs` VALUES ('608', '用户模块', '登录', '2020-06-12 15:58:38', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('609', '用户模块', '登录', '2020-06-12 17:30:22', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('610', '用户模块', '登录', '2020-06-12 19:55:29', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('611', '用户模块', '登录', '2020-06-12 22:51:43', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('612', '用户模块', '登录', '2020-06-13 11:13:04', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('613', '用户模块', '登录', '2020-06-13 11:14:32', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('614', '用户模块', '登录', '2020-06-13 11:14:32', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('615', '用户模块', '登录', '2020-06-13 11:27:41', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('616', '用户模块', '登录', '2020-06-13 11:27:41', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('617', '用户模块', '登录', '2020-06-13 11:29:51', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('618', '用户模块', '登录', '2020-06-13 11:29:51', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('619', '用户模块', '登录', '2020-06-13 11:31:21', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('620', '用户模块', '登录', '2020-06-13 11:31:21', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('621', '用户模块', '登录', '2020-06-13 11:32:38', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('622', '用户模块', '登录', '2020-06-13 11:32:38', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('623', '用户模块', '登录', '2020-06-13 11:32:38', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('624', '用户模块', '登录', '2020-06-13 11:41:23', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('625', '用户模块', '登录', '2020-06-13 11:41:23', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('626', '用户模块', '登录', '2020-06-13 11:43:00', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('627', '用户模块', '登录', '2020-06-13 11:43:00', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('628', '用户模块', '登录', '2020-06-13 11:43:00', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('629', '用户模块', '登录', '2020-06-13 11:45:14', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('630', '用户模块', '登录', '2020-06-13 11:45:14', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('631', '用户模块', '登录', '2020-06-13 11:49:45', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('632', '用户模块', '登录', '2020-06-13 11:49:45', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('633', '用户模块', '登录', '2020-06-13 11:50:47', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('634', '用户模块', '登录', '2020-06-13 11:51:48', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('635', '用户模块', '登录', '2020-06-13 11:51:48', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('636', '用户模块', '登录', '2020-06-13 11:55:06', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('637', '用户模块', '登录', '2020-06-13 11:55:06', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('638', '用户模块', '登录', '2020-06-13 11:59:23', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('639', '用户模块', '登录', '2020-06-13 11:59:23', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('640', '用户模块', '登录', '2020-06-13 12:03:24', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('641', '用户模块', '登录', '2020-06-13 12:03:24', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('642', '用户模块', '登录', '2020-06-13 12:05:25', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('643', '用户模块', '登录', '2020-06-13 12:07:12', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('644', '用户模块', '登录', '2020-06-13 12:07:12', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('645', '用户模块', '登录', '2020-06-13 12:09:59', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('646', '用户模块', '登录', '2020-06-13 12:09:59', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('647', '用户模块', '登录', '2020-06-13 12:12:08', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('648', '用户模块', '登录', '2020-06-13 12:12:08', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('649', '用户模块', '登录', '2020-06-13 12:16:34', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('650', '用户模块', '登录', '2020-06-13 12:16:34', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('651', '用户模块', '登录', '2020-06-13 12:29:26', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('652', '用户模块', '登录', '2020-06-13 12:32:29', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('653', '用户模块', '登录', '2020-06-13 12:33:25', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('654', '用户模块', '登录', '2020-06-13 12:37:07', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('655', '用户模块', '登录', '2020-06-13 12:41:33', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('656', '用户模块', '登录', '2020-06-13 12:47:33', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('657', '用户模块', '登录', '2020-06-13 12:48:18', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('658', '用户模块', '登录', '2020-06-13 12:56:23', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('659', '用户模块', '登录', '2020-06-13 12:57:01', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('660', '用户模块', '登录', '2020-06-13 12:57:45', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('661', '用户模块', '登录', '2020-06-13 13:01:26', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('662', '用户模块', '登录', '2020-06-13 13:03:56', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('663', '用户模块', '登录', '2020-06-13 13:08:12', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('664', '用户模块', '登录', '2020-06-13 13:08:58', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('665', '用户模块', '登录', '2020-06-13 16:03:20', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('666', '用户模块', '登录', '2020-06-13 16:09:36', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('667', '用户模块', '登录', '2020-06-13 16:18:35', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('668', '用户模块', '登录', '2020-06-13 16:25:29', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('669', '用户模块', '登录', '2020-06-13 16:26:39', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('670', '用户模块', '登录', '2020-06-13 16:29:14', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('671', '用户模块', '登录', '2020-06-13 16:31:57', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('672', '用户模块', '登录', '2020-06-13 16:33:30', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('673', '用户模块', '查询', '2020-06-13 16:42:37', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('674', '角色模块', '查询', '2020-06-13 16:42:40', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('675', '用户模块', '登录', '2020-06-13 16:46:18', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('676', '用户模块', '登录', '2020-06-13 16:46:41', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('677', '用户模块', '登录', '2020-06-13 16:50:51', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('678', '用户模块', '登录', '2020-06-13 16:55:58', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('679', '用户模块', '登录', '2020-06-13 17:01:55', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('680', '用户模块', '登录', '2020-06-13 17:05:25', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('681', '用户模块', '登录', '2020-06-13 17:10:06', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('682', '用户模块', '登录', '2020-06-13 17:18:18', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('683', '用户模块', '登录', '2020-06-13 17:20:23', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('684', '用户模块', '登录', '2020-06-13 17:32:24', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('685', '用户模块', '登录', '2020-06-13 17:35:37', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('686', '用户模块', '登录', '2020-06-13 17:37:48', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('687', '用户模块', '登录', '2020-06-13 17:39:41', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('688', '用户模块', '登录', '2020-06-13 17:42:22', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('689', '用户模块', '登录', '2020-06-13 17:43:51', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('690', '日志模块', '查询', '2020-06-13 17:46:48', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('691', '用户模块', '登录', '2020-06-13 17:51:13', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('692', '用户模块', '登录', '2020-06-13 17:51:13', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('693', '用户模块', '登录', '2020-06-13 17:59:11', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('694', '用户模块', '登录', '2020-06-13 18:09:12', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('695', '用户模块', '登录', '2020-06-13 18:16:50', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('696', '用户模块', '登录', '2020-06-13 18:19:54', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('697', '用户模块', '登录', '2020-06-13 18:21:37', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('698', '用户模块', '登录', '2020-06-13 18:25:49', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('699', '用户模块', '登录', '2020-06-13 18:27:28', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('700', '用户模块', '登录', '2020-06-13 18:28:48', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('701', '用户模块', '登录', '2020-06-13 18:30:36', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('702', '用户模块', '登录', '2020-06-13 18:31:48', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('703', '用户模块', '登录', '2020-06-13 18:35:56', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('704', '用户模块', '登录', '2020-06-13 18:46:01', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('705', '用户模块', '登录', '2020-06-13 18:48:16', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('706', '用户模块', '登录', '2020-06-13 18:49:09', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('707', '用户模块', '登录', '2020-06-13 18:51:29', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('708', '用户模块', '登录', '2020-06-13 18:56:08', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('709', '用户模块', '登录', '2020-06-13 18:57:54', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('710', '用户模块', '登录', '2020-06-13 18:59:08', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('711', '用户模块', '登录', '2020-06-13 19:00:13', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('712', '用户模块', '登录', '2020-06-13 19:06:36', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('713', '用户模块', '登录', '2020-06-13 19:07:39', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('714', '用户模块', '登录', '2020-06-13 19:10:13', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('715', '用户模块', '登录', '2020-06-13 19:15:29', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('716', '用户模块', '登录', '2020-06-13 19:19:32', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('717', '用户模块', '登录', '2020-06-13 19:20:13', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('718', '用户模块', '登录', '2020-06-13 19:21:49', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('719', '用户模块', '登录', '2020-06-13 19:22:03', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('720', '用户模块', '登录', '2020-06-13 19:25:00', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('721', '用户模块', '登录', '2020-06-13 20:57:11', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('722', '用户模块', '登录', '2020-06-13 20:58:18', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('723', '用户模块', '登录', '2020-06-13 21:03:12', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('724', '用户模块', '登录', '2020-06-13 21:04:26', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('725', '用户模块', '登录', '2020-06-13 21:07:10', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('726', '用户模块', '登录', '2020-06-13 21:11:12', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('727', '用户模块', '登录', '2020-06-13 21:15:56', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('728', '用户模块', '登录', '2020-06-13 21:17:37', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('729', '用户模块', '登录', '2020-06-13 21:18:28', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('730', '用户模块', '登录', '2020-06-13 21:20:44', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('731', '用户模块', '登录', '2020-06-13 21:21:27', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('732', '用户模块', '登录', '2020-06-13 21:21:54', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('733', '用户模块', '登录', '2020-06-13 21:23:09', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('734', '用户模块', '登录', '2020-06-13 21:43:55', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('735', '用户模块', '登录', '2020-06-13 21:45:37', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('736', '用户模块', '登录', '2020-06-13 21:49:09', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('737', '用户模块', '登录', '2020-06-13 21:52:49', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('738', '用户模块', '登录', '2020-06-13 21:57:57', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('739', '用户模块', '登录', '2020-06-13 22:08:27', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('740', '用户模块', '登录', '2020-06-13 23:02:51', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('741', '用户模块', '登录', '2020-06-13 23:07:43', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('742', '用户模块', '登录', '2020-06-13 23:09:01', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('743', '用户模块', '登录', '2020-06-13 23:09:45', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('744', '用户模块', '登录', '2020-06-13 23:13:30', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('745', '用户模块', '登录', '2020-06-13 23:15:14', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('746', '用户模块', '登录', '2020-06-13 23:16:18', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('747', '用户模块', '登录', '2020-06-13 23:20:40', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('748', '用户模块', '登录', '2020-06-13 23:24:20', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('749', '用户模块', '登录', '2020-06-13 23:25:29', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('750', '用户模块', '登录', '2020-06-13 23:31:57', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('751', '用户模块', '登录', '2020-06-13 23:34:25', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('752', '用户模块', '登录', '2020-06-13 23:35:26', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('753', '用户模块', '登录', '2020-06-13 23:36:30', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('754', '用户模块', '登录', '2020-06-13 23:37:32', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('755', '用户模块', '登录', '2020-06-13 23:38:16', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('756', '用户模块', '登录', '2020-06-13 23:39:08', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('757', '用户模块', '登录', '2020-06-13 23:40:38', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('758', '用户模块', '登录', '2020-06-13 23:42:44', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('759', '用户模块', '登录', '2020-06-13 23:43:55', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('760', '用户模块', '登录', '2020-06-13 23:46:53', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('761', '用户模块', '登录', '2020-06-13 23:49:05', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('762', '用户模块', '登录', '2020-06-13 23:50:28', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('763', '用户模块', '登录', '2020-06-13 23:51:32', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('764', '用户模块', '登录', '2020-06-13 23:53:05', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('765', '用户模块', '登录', '2020-06-13 23:55:08', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('766', '用户模块', '登录', '2020-06-14 00:06:52', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('767', '用户模块', '登录', '2020-06-14 00:08:53', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('768', '用户模块', '登录', '2020-06-14 00:10:28', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('769', '用户模块', '登录', '2020-06-14 00:17:54', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('770', '用户模块', '登录', '2020-06-14 00:19:43', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('771', '用户模块', '登录', '2020-06-14 00:21:57', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('772', '用户模块', '登录', '2020-06-14 08:30:03', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('773', '用户模块', '登录', '2020-06-14 08:31:17', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('774', '用户模块', '登录', '2020-06-14 08:52:04', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('775', '用户模块', '登录', '2020-06-14 08:53:50', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('776', '用户模块', '登录', '2020-06-14 08:59:39', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('777', '用户模块', '登录', '2020-06-14 09:03:44', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('778', '用户模块', '登录', '2020-06-14 09:06:38', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('779', '用户模块', '登录', '2020-06-14 09:10:53', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('780', '用户模块', '登录', '2020-06-14 09:12:02', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('781', '用户模块', '登录', '2020-06-14 09:13:55', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('782', '用户模块', '登录', '2020-06-14 09:19:15', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('783', '用户模块', '登录', '2020-06-14 09:21:46', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('784', '用户模块', '登录', '2020-06-14 09:38:45', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('785', '用户模块', '登录', '2020-06-14 09:40:11', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('786', '用户模块', '登录', '2020-06-14 09:45:15', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('787', '用户模块', '登录', '2020-06-14 09:54:39', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('788', '用户模块', '查询', '2020-06-14 10:01:05', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('789', '用户模块', '登录', '2020-06-14 10:04:27', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('790', '用户模块', '登录', '2020-06-14 10:07:48', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('791', '用户模块', '登录', '2020-06-14 10:21:25', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('792', '用户模块', '登录', '2020-06-14 10:25:00', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('793', '用户模块', '登录', '2020-06-14 10:27:32', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('794', '语料', '添加', '2020-06-14 10:27:41', '我的名字是超管', '语料添加/编辑');
INSERT INTO `sys_logs` VALUES ('795', '语料', '添加', '2020-06-14 10:30:06', '我的名字是超管', '语料添加/编辑');
INSERT INTO `sys_logs` VALUES ('796', '用户模块', '登录', '2020-06-14 10:30:19', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('797', '语料', '添加', '2020-06-14 10:30:33', '我的名字是超管', '语料添加/编辑');
INSERT INTO `sys_logs` VALUES ('798', '用户模块', '登录', '2020-06-14 10:39:55', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('799', '角色模块', '查询', '2020-06-14 10:49:59', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('800', '用户模块', '查询', '2020-06-14 10:50:24', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('801', '用户模块', '登录', '2020-06-14 10:53:44', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('802', '用户模块', '登录', '2020-06-14 10:58:09', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('803', '用户模块', '登录', '2020-06-14 11:06:33', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('804', '用户模块', '登录', '2020-06-14 11:08:05', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('805', '用户模块', '登录', '2020-06-14 11:11:06', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('806', '用户模块', '登录', '2020-06-14 11:31:43', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('807', '用户模块', '登录', '2020-06-14 11:39:39', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('808', '用户模块', '登录', '2020-06-14 11:41:12', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('809', '用户模块', '登录', '2020-06-14 11:45:11', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('810', '用户模块', '登录', '2020-06-14 11:54:13', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('811', '用户模块', '登录', '2020-06-14 12:03:50', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('812', '用户模块', '登录', '2020-06-14 12:14:06', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('813', '用户模块', '登录', '2020-06-14 12:27:09', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('814', '用户模块', '登录', '2020-06-14 12:34:35', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('815', '用户模块', '登录', '2020-06-14 12:39:03', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('816', '用户模块', '登录', '2020-06-14 12:42:38', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('817', '用户模块', '登录', '2020-06-14 12:44:03', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('818', '用户模块', '登录', '2020-06-14 12:46:43', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('819', '用户模块', '登录', '2020-06-14 12:48:59', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('820', '用户模块', '登录', '2020-06-14 12:56:01', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('821', '用户模块', '登录', '2020-06-14 12:58:40', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('822', '用户模块', '登录', '2020-06-14 13:00:46', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('823', '用户模块', '登录', '2020-06-14 13:05:06', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('824', '日志模块', '查询', '2020-06-14 13:05:13', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('825', '角色模块', '查询', '2020-06-14 13:05:15', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('826', '用户模块', '查询', '2020-06-14 13:05:16', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('827', '用户模块', '登录', '2020-06-14 13:13:38', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('828', '用户模块', '登录', '2020-06-14 13:20:52', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('829', '用户模块', '登录', '2020-06-14 13:25:56', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('830', '用户模块', '登录', '2020-06-14 13:28:03', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('831', '用户模块', '登录', '2020-06-14 13:37:02', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('832', '用户模块', '登录', '2020-06-14 13:40:10', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('833', '用户模块', '登录', '2020-06-14 13:40:58', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('834', '用户模块', '登录', '2020-06-14 13:51:14', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('835', '用户模块', '登录', '2020-06-14 13:55:01', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('836', '用户模块', '登录', '2020-06-14 13:59:03', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('837', '用户模块', '登录', '2020-06-14 14:01:32', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('838', '用户模块', '登录', '2020-06-14 14:08:10', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('839', '用户模块', '登录', '2020-06-14 14:09:55', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('840', '用户模块', '登录', '2020-06-14 14:15:56', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('841', '用户模块', '登录', '2020-06-14 14:17:51', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('842', '用户模块', '登录', '2020-06-14 14:21:27', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('843', '用户模块', '登录', '2020-06-14 14:23:35', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('844', '用户模块', '登录', '2020-06-14 14:24:44', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('845', '用户模块', '登录', '2020-06-14 14:28:09', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('846', '用户模块', '登录', '2020-06-14 14:30:57', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('847', '用户模块', '登录', '2020-06-14 14:33:43', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('848', '用户模块', '登录', '2020-06-14 14:37:57', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('849', '用户模块', '登录', '2020-06-14 14:45:49', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('850', '日志模块', '查询', '2020-06-14 14:49:30', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('851', '日志模块', '查询', '2020-06-14 14:49:34', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('852', '日志模块', '查询', '2020-06-14 14:49:38', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('853', '日志模块', '查询', '2020-06-14 14:49:39', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('854', '用户模块', '查询', '2020-06-14 14:49:44', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('855', '角色模块', '查询', '2020-06-14 14:49:46', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('856', '用户模块', '登录', '2020-06-14 17:02:04', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('857', '用户模块', '登录', '2020-06-15 16:58:30', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('858', '用户模块', '登录', '2020-06-15 17:04:09', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('859', '用户模块', '登录', '2020-06-15 17:11:07', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('860', '用户模块', '登录', '2020-06-15 17:25:38', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('861', '用户模块', '登录', '2020-06-15 17:33:17', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('862', '用户模块', '登录', '2020-06-15 17:38:07', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('863', '用户模块', '登录', '2020-06-15 17:40:13', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('864', '用户模块', '登录', '2020-06-15 17:45:13', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('865', '用户模块', '登录', '2020-06-15 17:48:50', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('866', '用户模块', '登录', '2020-06-15 17:55:39', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('867', '用户模块', '登录', '2020-06-15 18:05:27', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('868', '用户模块', '登录', '2020-06-17 16:09:21', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('869', '用户模块', '登录', '2020-06-17 16:14:12', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('870', '用户模块', '登录', '2020-06-17 16:18:38', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('871', '用户模块', '登录', '2020-06-17 16:19:39', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('872', '用户模块', '登录', '2020-06-17 16:20:46', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('873', '用户模块', '登录', '2020-06-17 16:32:34', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('874', '用户模块', '登录', '2020-06-17 16:33:13', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('875', '用户模块', '登录', '2020-06-17 16:34:51', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('876', '用户模块', '登录', '2020-06-17 16:38:04', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('877', '用户模块', '登录', '2020-06-17 16:45:20', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('878', '用户模块', '登录', '2020-06-17 16:47:34', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('879', '用户模块', '登录', '2020-06-17 16:49:13', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('880', '用户模块', '登录', '2020-06-17 16:51:00', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('881', '用户模块', '登录', '2020-06-17 16:53:36', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('882', '用户模块', '登录', '2020-06-17 16:54:31', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('883', '用户模块', '登录', '2020-06-17 16:55:07', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('884', '用户模块', '登录', '2020-06-17 16:55:40', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('885', '用户模块', '登录', '2020-06-18 14:49:22', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('886', '用户模块', '查询', '2020-06-18 14:49:59', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('887', '角色模块', '查询', '2020-06-18 14:50:08', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('888', '用户模块', '删除', '2020-06-18 14:50:20', '我的名字是超管', '用户批量删除');
INSERT INTO `sys_logs` VALUES ('889', '用户模块', '查询', '2020-06-18 14:50:20', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('890', '日志模块', '查询', '2020-06-18 14:50:31', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('891', '用户模块', '登录', '2020-06-22 14:22:04', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('892', '日志模块', '查询', '2020-06-22 14:22:21', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('893', '用户模块', '登录', '2020-06-28 17:23:27', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('894', '用户模块', '删除', '2020-06-28 17:25:12', null, '用户退出');
INSERT INTO `sys_logs` VALUES ('895', '用户模块', '登录', '2020-06-29 14:12:24', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('896', '用户模块', '登录', '2020-06-29 14:20:03', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('897', '用户模块', '查询', '2020-06-29 14:22:27', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('898', '角色模块', '查询', '2020-06-29 14:22:28', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('899', '用户模块', '查询', '2020-06-29 14:23:14', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('900', '用户模块', '登录', '2020-06-29 14:25:00', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('901', '用户模块', '登录', '2020-06-29 14:27:02', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('902', '用户模块', '登录', '2020-06-29 14:50:18', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('903', '用户模块', '登录', '2020-06-29 14:58:53', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('904', '日志模块', '查询', '2020-06-29 15:00:07', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('905', '用户模块', '登录', '2020-06-30 17:00:51', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('906', '用户模块', '登录', '2020-06-30 17:07:15', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('907', '用户模块', '登录', '2020-06-30 17:15:14', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('908', '用户模块', '登录', '2020-06-30 17:25:35', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('909', '用户模块', '登录', '2020-06-30 17:32:18', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('910', '用户模块', '登录', '2020-06-30 17:39:42', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('911', '日志模块', '查询', '2020-06-30 17:49:29', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('912', '用户模块', '登录', '2020-06-30 17:53:46', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('913', '用户模块', '登录', '2020-06-30 18:01:27', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('914', '用户模块', '登录', '2020-06-30 18:02:21', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('915', '用户模块', '登录', '2020-06-30 18:07:14', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('916', '用户模块', '登录', '2020-06-30 19:00:57', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('917', '日志模块', '查询', '2020-06-30 19:06:45', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('918', '用户模块', '登录', '2020-07-01 11:13:45', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('919', '日志模块', '查询', '2020-07-01 11:15:04', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('920', '角色模块', '查询', '2020-07-01 11:17:48', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('921', '用户模块', '查询', '2020-07-01 11:17:50', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('922', '日志模块', '查询', '2020-07-01 11:22:19', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('923', '日志模块', '查询', '2020-07-01 11:23:09', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('924', '日志模块', '查询', '2020-07-01 11:34:49', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('925', '用户模块', '登录', '2020-07-01 11:35:46', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('926', '用户模块', '登录', '2020-07-01 11:37:01', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('927', '日志模块', '查询', '2020-07-01 11:37:27', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('928', '用户模块', '登录', '2020-07-01 11:38:10', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('929', '角色模块', '查询', '2020-07-01 11:45:21', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('930', '日志模块', '查询', '2020-07-01 11:50:39', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('931', '用户模块', '登录', '2020-07-01 14:11:19', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('932', '日志模块', '查询', '2020-07-01 14:11:27', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('933', '角色模块', '查询', '2020-07-01 14:11:31', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('934', '用户模块', '登录', '2020-07-01 14:59:35', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('935', '用户模块', '登录', '2020-07-01 15:01:59', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('936', '用户模块', '登录', '2020-07-01 15:04:00', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('937', '用户模块', '登录', '2020-07-01 15:05:20', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('938', '用户模块', '登录', '2020-07-01 15:19:07', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('939', '日志模块', '查询', '2020-07-01 15:19:16', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('940', '角色模块', '查询', '2020-07-01 15:41:52', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('941', '用户模块', '登录', '2020-07-01 15:46:48', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('942', '用户模块', '登录', '2020-07-01 15:49:32', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('943', '用户模块', '登录', '2020-07-01 15:55:01', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('944', '用户模块', '登录', '2020-07-01 15:57:00', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('945', '用户模块', '登录', '2020-07-01 15:57:33', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('946', '用户模块', '登录', '2020-07-01 16:00:04', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('947', '用户模块', '登录', '2020-07-01 16:01:11', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('948', '用户模块', '登录', '2020-07-01 16:03:42', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('949', '用户模块', '登录', '2020-07-01 16:08:31', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('950', '用户模块', '登录', '2020-07-01 16:09:48', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('951', '用户模块', '查询', '2020-07-01 16:22:49', '我的名字是超管', '用户分页查询');
INSERT INTO `sys_logs` VALUES ('952', '角色模块', '查询', '2020-07-01 16:22:57', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('953', '角色模块', '查询', '2020-07-01 16:24:17', '我的名字是超管', '获取角色分页列表');
INSERT INTO `sys_logs` VALUES ('954', '用户模块', '登录', '2020-07-01 16:29:39', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('955', '用户模块', '登录', '2020-07-01 17:48:55', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('956', '用户模块', '登录', '2020-07-01 19:00:02', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('957', '用户模块', '登录', '2020-07-01 19:19:05', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('958', '用户模块', '登录', '2020-07-01 19:20:31', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('959', '用户模块', '登录', '2020-07-01 19:22:20', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('960', '用户模块', '登录', '2020-07-01 19:26:15', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('961', '用户模块', '登录', '2020-07-02 10:11:20', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('962', '用户模块', '登录', '2020-07-02 10:40:55', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('963', '用户模块', '登录', '2020-07-02 10:48:13', null, '用户登录');
INSERT INTO `sys_logs` VALUES ('964', '用户模块', '登录', '2020-07-02 10:48:20', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('965', '用户模块', '登录', '2020-07-02 10:57:11', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('966', '用户模块', '登录', '2020-07-02 10:57:42', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('967', '日志模块', '查询', '2020-07-02 10:57:58', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('968', '用户模块', '登录', '2020-07-02 11:01:41', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('969', '用户模块', '登录', '2020-07-02 11:03:14', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('970', '用户模块', '登录', '2020-07-02 11:10:30', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('971', '用户模块', '登录', '2020-07-02 11:15:33', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('972', '用户模块', '登录', '2020-07-02 11:18:15', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('973', '用户模块', '登录', '2020-07-02 11:23:11', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('974', '用户模块', '登录', '2020-07-02 11:26:01', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('975', '用户模块', '登录', '2020-07-02 11:27:06', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('976', '日志模块', '查询', '2020-07-02 11:27:36', '我的名字是超管', '分页获取日志列表');
INSERT INTO `sys_logs` VALUES ('977', '用户模块', '登录', '2020-07-02 11:29:17', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('978', '用户模块', '登录', '2020-07-02 11:31:35', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('979', '用户模块', '登录', '2020-07-02 12:44:08', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('980', '用户模块', '登录', '2020-07-02 13:37:28', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('981', '用户模块', '登录', '2020-07-02 13:43:44', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('982', '用户模块', '登录', '2020-07-02 13:46:44', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('983', '用户模块', '登录', '2020-07-02 13:50:40', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('984', '用户模块', '登录', '2020-07-02 13:55:18', '我的名字是超管', '用户登录');
INSERT INTO `sys_logs` VALUES ('985', '用户模块', '登录', '2020-07-02 16:58:35', '我的名字是超管', '用户登录');

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '我的名字是超管', 'aa.com', 'dddddddddd', 'd', null, '0', '1');
INSERT INTO `user` VALUES ('31', 'shebei', '123456', '我是设备管理员', '18337181234', '就住在那个屯', '我的老家 啊', null, '0', '0');
INSERT INTO `user` VALUES ('35', 'putong', '123456', '普通用户啊', '0371-99999999', '来最右，粘贴你在知乎复制的故事', '来知乎，分享你刚编的故事', null, '0', '0');
INSERT INTO `user` VALUES ('47', 'xinlaide', '123456', '我是新来的', '1', '134', '12', null, '1', '0');
INSERT INTO `user` VALUES ('48', '54', '123456', '4', '4', '4', '4', null, '1', '0');
INSERT INTO `user` VALUES ('49', 'fff', '123456', '3333', '3', '3', '3', null, '0', '0');
INSERT INTO `user` VALUES ('50', 'rizhi', '123456', '日志', '1', '1', '1', null, '1', '0');
INSERT INTO `user` VALUES ('51', 'rizhiguanli', '123456', 'rizhi', '13215488745', '111', '11', null, '0', '0');

-- ----------------------------
-- Table structure for user_facility
-- ----------------------------
DROP TABLE IF EXISTS `user_facility`;
CREATE TABLE `user_facility` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

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
