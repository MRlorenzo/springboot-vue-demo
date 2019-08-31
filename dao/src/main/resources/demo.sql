/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2019-08-31 16:55:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) NOT NULL COMMENT '请求路径',
  `component` varchar(255) NOT NULL COMMENT '使用的组件地址',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名字',
  `redirect` varchar(255) DEFAULT NULL COMMENT '重定向至',
  `always_show` int(1) DEFAULT NULL COMMENT '将始终显示根菜单',
  `hidden` int(1) DEFAULT NULL COMMENT '是否隐藏',
  `meta` text COMMENT '必须是Json格式的字符串，用来标识该项的一些要素',
  `pid` bigint(20) DEFAULT NULL COMMENT '父id',
  `is_del` int(1) DEFAULT '0' COMMENT '是否删除了',
  `sort` int(11) DEFAULT '0',
  `perms` varchar(255) DEFAULT NULL COMMENT '权限字符串',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '/permission', 'layout/Layout', '', '/permission/index', '1', null, '{\"title\":\"permission\",\"icon\":\"lock\",\"roles\":[\"admin\",\"editor\"]}', '0', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('2', 'page', 'views/permission/page', 'PagePermission', null, null, null, '{\"title\":\"pagePermission\",\"roles\":[\"admin\"]}', '1', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('3', 'directive', 'views/permission/directive', 'DirectivePermission', null, null, null, '{\"title\":\"directivePermission\"}', '1', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('4', 'role', 'views/permission/role', 'RolePermission', null, null, null, '{\"title\":\"rolePermission\",\"roles\":[\"admin\"]}', '1', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('5', '/icon', 'layout/Layout', null, null, null, null, null, '0', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('6', 'index', 'views/icons/index', 'Icons', null, null, null, '{\"title\":\"icons\",\"icon\":\"icon\",\"noCache\":true}', '5', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('7', '/components', 'layout/Layout', 'ComponentDemo', 'noRedirect', null, null, '{\"title\":\"components\",\"icon\":\"component\"}', '0', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('8', 'tinymce', 'views/components-demo/tinymce', 'TinymceDemo', null, null, null, '{\"title\":\"tinymce\"}', '7', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('9', 'markdown', 'views/components-demo/markdown', 'MarkdownDemo', null, null, null, '{\"title\":\"markdown\"}', '7', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('10', 'json-editor', 'views/components-demo/json-editor', 'JsonEditorDemo', null, null, null, '{\"title\":\"jsonEditor\"}', '7', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('11', 'split-pane', 'views/components-demo/split-pane', 'SplitpaneDemo', null, null, null, '{\"title\":\"splitPane\"}', '7', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('12', 'avatar-upload', 'views/components-demo/avatar-upload', 'AvatarUploadDemo', null, null, null, '{\"title\":\"avatarUpload\"}', '7', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('13', 'dropzone', 'views/components-demo/dropzone', 'DropzoneDemo', null, null, null, '{\"title\":\"dropzone\"}', '7', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('14', 'sticky', 'views/components-demo/sticky', 'StickyDemo', null, null, null, '{\"title\":\"sticky\"}', '7', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('15', 'count-to', 'views/components-demo/count-to', 'CountToDemo', null, null, null, '{\"title\":\"countTo\"}', '7', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('16', 'mixin', 'views/components-demo/mixin', 'ComponentMixinDemo', null, null, null, '{\"title\":\"componentMixin\"}', '7', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('17', 'back-to-top', 'views/components-demo/back-to-top', 'BackToTopDemo', null, null, null, '{\"title\":\"backToTop\"}', '7', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('18', 'drag-dialog', 'views/components-demo/drag-dialog', 'DragDialogDemo', null, null, null, '{\"title\":\"dragDialog\"}', '7', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('19', 'drag-select', 'views/components-demo/drag-select', 'DragSelectDemo', null, null, null, '{ title: \'dragSelect\' }', '7', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('20', 'dnd-list', 'views/components-demo/dnd-list', 'DndListDemo', null, null, null, '{\"title\":\"dndList\"}', '7', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('21', 'drag-kanban', 'views/components-demo/drag-kanban', 'DragKanbanDemo', null, null, null, '{\"title\":\"dragKanban\"}', '7', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('22', '/charts', 'layout/Layout', 'Charts', 'noRedirect', null, null, '{\"title\":\"charts\",\"icon\":\"chart\"}', '0', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('23', 'keyboard', 'views/charts/keyboard', 'KeyboardChart', null, null, null, '{\"title\":\"keyboardChart\",\"noCache\":true}', '22', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('24', 'line', 'views/charts/line', 'LineChart', null, null, null, '{\"title\":\"lineChart\",\"noCache\":true}', '22', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('25', 'mixchart', 'views/charts/mixChart', 'MixChart', null, null, null, '{\"title\":\"mixChart\",\"noCache\":true}', '22', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('26', '/tab', 'layout/Layout', null, null, null, null, null, '0', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('27', 'index', 'views/tab/index', 'Tab', null, null, null, '{\"title\":\"tab\",\"icon\":\"tab\"}', '26', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('28', '/error', 'layout/Layout', 'ErrorPages', 'noRedirect', null, null, '{\"title\":\"errorPages\",\"icon\":\"404\"}', '0', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('29', '401', 'views/error-page/401', 'Page401', null, null, null, '{\"title\":\"page401\",\"noCache\":true}', '28', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('30', '404', 'views/error-page/404', 'Page404', null, null, null, '{\"title\":\"page404\",\"noCache\":true}', '28', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('31', '/error-log', 'layout/Layout', null, 'noRedirect', null, null, null, '0', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('32', 'log', 'views/error-log/index', 'ErrorLog', null, null, null, '{\"title\":\"errorLog\",\"icon\":\"bug\"}', '31', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('33', '/excel', 'layout/Layout', 'Excel', '/excel/export-excel', null, null, '{\"title\":\"excel\",\"icon\":\"excel\"}', '0', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('34', 'export-excel', 'views/excel/export-excel', 'ExportExcel', null, null, null, '{\"title\":\"exportExcel\"}', '33', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('35', 'export-selected-excel', 'views/excel/select-excel', 'SelectExcel', null, null, null, '{\"title\":\"selectExcel\"}', '33', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('36', 'export-merge-header', 'views/excel/merge-header', 'MergeHeader', null, null, null, '{\"title\":\"mergeHeader\"}', '33', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('37', 'upload-excel', 'views/excel/upload-excel', 'UploadExcel', null, null, null, '{\"title\":\"uploadExcel\"}', '33', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('38', '/zip', 'layout/Layout', null, '/zip/download', '1', null, null, '0', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('39', 'download', 'views/zip/index', 'ExportZip', null, null, null, '{\"title\":\"exportZip\"}', '37', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('40', '/pdf', 'layout/Layout', null, '/pdf/index', null, null, null, '0', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('41', 'index', 'views/pdf/index', 'PDF', null, null, null, '{\"title\":\"pdf\",\"icon\":\"pdf\"}', '40', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('42', '/pdf/download', 'views/pdf/download', null, null, null, '1', null, '0', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('43', '/theme', 'layout/Layout', null, 'noRedirect', null, null, null, '0', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('44', 'index', 'views/theme/index', 'Theme', null, null, null, '{\"title\":\"theme\",\"icon\":\"theme\"}', '43', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('45', '/clipboard', 'layout/Layout', null, 'noRedirect', null, null, null, '0', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('46', 'index', 'views/clipboard/index', 'ClipboardDemo', null, null, null, '{\"title\":\"clipboardDemo\",\"icon\":\"clipboard\"}', '45', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('47', '/i18n', 'layout/Layout', null, null, null, null, null, '0', '0', '0', null);
INSERT INTO `sys_permission` VALUES ('48', 'index', 'views/i18n-demo/index', 'I18n', null, null, null, '{\"title\":\"i18n\",\"icon\":\"international\"}', '47', '0', '0', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_shift` int(1) DEFAULT '1' COMMENT '是否需要选择班次  0:不需要  1:需要',
  `is_del` int(1) DEFAULT '0' COMMENT '是否已删除 0否 1是',
  `department_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `sort` int(3) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '管理员', '1', '2017-04-03 14:50:06', '0', '0', '4', '1');
INSERT INTO `sys_role` VALUES ('41', 'hr', '测试角色', '1', '2019-08-30 21:02:11', '1', '0', '10', null);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('116', '41', '22');
INSERT INTO `sys_role_permission` VALUES ('117', '41', '23');
INSERT INTO `sys_role_permission` VALUES ('118', '41', '24');
INSERT INTO `sys_role_permission` VALUES ('119', '41', '25');
INSERT INTO `sys_role_permission` VALUES ('120', '41', '43');
INSERT INTO `sys_role_permission` VALUES ('121', '41', '44');
INSERT INTO `sys_role_permission` VALUES ('122', '41', '47');
INSERT INTO `sys_role_permission` VALUES ('123', '41', '48');
INSERT INTO `sys_role_permission` VALUES ('170', '1', '1');
INSERT INTO `sys_role_permission` VALUES ('171', '1', '2');
INSERT INTO `sys_role_permission` VALUES ('172', '1', '3');
INSERT INTO `sys_role_permission` VALUES ('173', '1', '4');
INSERT INTO `sys_role_permission` VALUES ('174', '1', '5');
INSERT INTO `sys_role_permission` VALUES ('175', '1', '6');
INSERT INTO `sys_role_permission` VALUES ('176', '1', '7');
INSERT INTO `sys_role_permission` VALUES ('177', '1', '8');
INSERT INTO `sys_role_permission` VALUES ('178', '1', '9');
INSERT INTO `sys_role_permission` VALUES ('179', '1', '10');
INSERT INTO `sys_role_permission` VALUES ('180', '1', '11');
INSERT INTO `sys_role_permission` VALUES ('181', '1', '12');
INSERT INTO `sys_role_permission` VALUES ('182', '1', '13');
INSERT INTO `sys_role_permission` VALUES ('183', '1', '14');
INSERT INTO `sys_role_permission` VALUES ('184', '1', '15');
INSERT INTO `sys_role_permission` VALUES ('185', '1', '16');
INSERT INTO `sys_role_permission` VALUES ('186', '1', '17');
INSERT INTO `sys_role_permission` VALUES ('187', '1', '18');
INSERT INTO `sys_role_permission` VALUES ('188', '1', '19');
INSERT INTO `sys_role_permission` VALUES ('189', '1', '20');
INSERT INTO `sys_role_permission` VALUES ('190', '1', '21');
INSERT INTO `sys_role_permission` VALUES ('191', '1', '22');
INSERT INTO `sys_role_permission` VALUES ('192', '1', '23');
INSERT INTO `sys_role_permission` VALUES ('193', '1', '24');
INSERT INTO `sys_role_permission` VALUES ('194', '1', '25');
INSERT INTO `sys_role_permission` VALUES ('195', '1', '26');
INSERT INTO `sys_role_permission` VALUES ('196', '1', '27');
INSERT INTO `sys_role_permission` VALUES ('197', '1', '28');
INSERT INTO `sys_role_permission` VALUES ('198', '1', '29');
INSERT INTO `sys_role_permission` VALUES ('199', '1', '30');
INSERT INTO `sys_role_permission` VALUES ('200', '1', '31');
INSERT INTO `sys_role_permission` VALUES ('201', '1', '32');
INSERT INTO `sys_role_permission` VALUES ('202', '1', '33');
INSERT INTO `sys_role_permission` VALUES ('203', '1', '34');
INSERT INTO `sys_role_permission` VALUES ('204', '1', '35');
INSERT INTO `sys_role_permission` VALUES ('205', '1', '36');
INSERT INTO `sys_role_permission` VALUES ('206', '1', '37');
INSERT INTO `sys_role_permission` VALUES ('207', '1', '38');
INSERT INTO `sys_role_permission` VALUES ('208', '1', '40');
INSERT INTO `sys_role_permission` VALUES ('209', '1', '41');
INSERT INTO `sys_role_permission` VALUES ('210', '1', '43');
INSERT INTO `sys_role_permission` VALUES ('211', '1', '44');
INSERT INTO `sys_role_permission` VALUES ('212', '1', '45');
INSERT INTO `sys_role_permission` VALUES ('213', '1', '46');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `free_pwd` varchar(255) DEFAULT NULL COMMENT '免密登录密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `surname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '姓',
  `given_names` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名',
  `passport` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '身份证',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `address` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '地址',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `head_portrait` varchar(255) DEFAULT NULL COMMENT '头像',
  `department_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `hiredate` date DEFAULT NULL COMMENT '入职时间',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `free_pwd` (`free_pwd`)
) ENGINE=InnoDB AUTO_INCREMENT=654 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '3611d57e7945cad573681c576fa9ae5edb5bcc8a59cf09dbfc220923fa5bffe1', '6959493507098', 'root@github.io', '15612345678', '哈', '哈哈', '\\20180614\\68abb88adadf45b9b3f98661389ec757.jpg', '1997-05-19', '详细信息', '1', null, '2016-11-11 11:11:11', '\\20180614\\380e37e2b8814174901d5b8cf7753019.jpg', '0', null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=910 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('66', '1', '1');

-- ----------------------------
-- Table structure for tb_department
-- ----------------------------
DROP TABLE IF EXISTS `tb_department`;
CREATE TABLE `tb_department` (
  `department_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(255) DEFAULT NULL COMMENT '部门中文名称',
  `en_name` varchar(255) DEFAULT NULL COMMENT '部门英文名称',
  `department_code` varchar(255) DEFAULT NULL COMMENT '部门编号',
  `sort` int(3) DEFAULT NULL COMMENT '排序字段',
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';

-- ----------------------------
-- Records of tb_department
-- ----------------------------
INSERT INTO `tb_department` VALUES ('1', '账房', 'Cage', 'Cage', '2');
INSERT INTO `tb_department` VALUES ('2', '财务', 'Finance', 'Finance', '4');
INSERT INTO `tb_department` VALUES ('3', '场面', 'Table Games', 'Table Games', '3');
INSERT INTO `tb_department` VALUES ('4', '董事长', 'President', 'President', '1');
INSERT INTO `tb_department` VALUES ('8', '客服', 'Customer service', 'CS', '30');
INSERT INTO `tb_department` VALUES ('9', '市场', 'Market', 'MK', '6');
INSERT INTO `tb_department` VALUES ('10', '人事', 'HR', 'HR', '5');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('1', '\r\nFAc7B2aa-2d7c-B90c-FE1b-D92345', '3338.50', 'success', '2019-08-16 05:28:11', 'Mark Taylor');
INSERT INTO `tb_order` VALUES ('2', 'd19a7Df2-82E5-3Ca9-E4eD-65FAD68b23Ff', '12398.00', 'success', '2019-08-01 05:28:48', 'Mark Smith');
INSERT INTO `tb_order` VALUES ('3', 'F9F7E8A8-5bb3-eCa7-FACE-671125B36Aee', '13411.00', 'pending', '2019-08-17 05:29:56', 'Anna Jones');
INSERT INTO `tb_order` VALUES ('4', 'D4eDFA7D-B69c-16b1-9C1E-Dea7Ff2dAbEb', '11919.19', 'pending', '2019-08-11 05:30:01', 'Steven Taylor');
