/*
 Navicat Premium Data Transfer

 Source Server         : Home
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 192.168.161.3:3306
 Source Schema         : examdb

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 22/08/2019 15:27:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `stu_id` varchar(11) DEFAULT NULL COMMENT '学号',
  `stu_name` varchar(20) DEFAULT NULL COMMENT '学生姓名',
  `last_time` datetime DEFAULT NULL COMMENT '最近登錄時間',
  `total` float(32,0) DEFAULT NULL COMMENT '分数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='成绩表';

-- ----------------------------
-- Table structure for login_history
-- ----------------------------
DROP TABLE IF EXISTS `login_history`;
CREATE TABLE `login_history` (
  `user_id` varchar(11) NOT NULL COMMENT '用戶ID',
  `access` char(1) DEFAULT '0' COMMENT '学生权限 默认0 - 无意义',
  `login_time` datetime DEFAULT NULL COMMENT '学生登录时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录记录';

-- ----------------------------
-- Records of login_history
-- ----------------------------
BEGIN;
INSERT INTO `login_history` VALUES ('15010157', '0', '2019-08-22 15:00:58');
INSERT INTO `login_history` VALUES ('0001', '1', '2019-08-22 15:01:26');
INSERT INTO `login_history` VALUES ('0001', '1', '2019-08-22 15:01:26');
INSERT INTO `login_history` VALUES ('0001', '1', '2019-08-22 15:01:26');
INSERT INTO `login_history` VALUES ('0001', '1', '2019-08-22 15:01:26');
COMMIT;

-- ----------------------------
-- Table structure for next_question
-- ----------------------------
DROP TABLE IF EXISTS `next_question`;
CREATE TABLE `next_question` (
  `q_id` varchar(11) NOT NULL DEFAULT '0' COMMENT '问题编号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='下个问题';

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `content` text COMMENT '题目内容',
  `answer` varchar(20) DEFAULT NULL COMMENT '题目答案',
  `score` int(11) DEFAULT NULL COMMENT '题目分数',
  `teacher_id` varchar(11) DEFAULT NULL COMMENT '教师编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `choice` varchar(255) NOT NULL COMMENT '选项',
  PRIMARY KEY (`id`),
  KEY `TeacherId` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='问题表';

-- ----------------------------
-- Records of question
-- ----------------------------
BEGIN;
INSERT INTO `question` VALUES (15, '1233', 'zheshi', 10, '0001', '2019-08-13 22:20:28', 'test');
INSERT INTO `question` VALUES (16, ' 這是一個測試題目', '3,', 90, '0001', '2019-08-13 22:22:22', '選項一@選項23@選項24@安達@');
INSERT INTO `question` VALUES (17, 'Excel 文件的扩展名是', '3,', 3, '0001', '2019-08-17 23:45:25', 'exe@bat@doc@xls@');
INSERT INTO `question` VALUES (18, '在 Excel 中用来求平均数的函数是', '1,', 10, '0001', '2019-08-18 05:21:35', 'SUM@AVERAGE@COUNT@MAX@');
INSERT INTO `question` VALUES (19, '按下（ A ） 键结合鼠标操作可选取多个不连续的单元格', '0,', 3, '0001', '2019-08-18 05:22:43', 'Ctr@Alt@Tab@Shift@');
INSERT INTO `question` VALUES (20, '若在数值单元格中出现一连串的“###”符号， 如果想正常显示则需要', '0,', 3, '0001', '2019-08-18 05:24:27', '重新输入数据 @调整单元格的宽度@删除这些符号@删除该单元格 @');
INSERT INTO `question` VALUES (23, '分类汇总前必须按照“分类的字段”先进行', '3,', 2, '0001', '2019-08-18 05:28:12', '汇总@排序@求和@筛选@');
INSERT INTO `question` VALUES (24, '在单元格内输入一个公式时， 应先键入符号', '2,', 5, '0001', '2019-08-18 05:28:47', '$@>@<@=@');
INSERT INTO `question` VALUES (25, '在 Excel 操作中,假设在 B5 单元格中存有一公式为=SUM(B2:B4),将其复制到 D5 单元格后， 公式将变成', '1,', 43, '0001', '2019-08-18 05:29:58', '=SUM(B2:B4)@=SUM(B2:D4)@=SUM(B2:D4)@=SUM(D2:B4) @');
INSERT INTO `question` VALUES (27, '有关 EXCEL 图表， 下面表述正确的是', '1,', 23, '0001', '2019-08-18 05:38:17', '要往图表增加一个系列， 必须重新建立图表 @修改了图表数据源单元格的数据， 图表会自动跟着更新@要修改图表的类型， 必须重新建立图表@修改了图表坐标轴的字体、 字号， 坐标轴标题就自动跟着变化@');
INSERT INTO `question` VALUES (28, '下列不是图片处理软件的是', '3,', 23, '0001', '2019-08-18 05:40:08', 'ACDSee@美图秀秀@我行我素@Word@');
COMMIT;

-- ----------------------------
-- Table structure for question_sets
-- ----------------------------
DROP TABLE IF EXISTS `question_sets`;
CREATE TABLE `question_sets` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `set_name` varchar(255) NOT NULL,
  `include` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='问题设置';

-- ----------------------------
-- Records of question_sets
-- ----------------------------
BEGIN;
INSERT INTO `question_sets` VALUES (10, '從', 'Q16Q15', NULL, NULL);
INSERT INTO `question_sets` VALUES (11, '计算机基础-半期试卷', 'Q28Q27Q25Q24Q23Q20Q19Q18Q17', NULL, '测试教师号');
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` varchar(11) NOT NULL COMMENT 'ID',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生信息';

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES ('1233', 'test', 'test');
INSERT INTO `student` VALUES ('123456', 'rewr', '7af635a7a0686304506c6cdf3610456b');
INSERT INTO `student` VALUES ('15010157', '测试学生号', 'ak470000');
COMMIT;

-- ----------------------------
-- Table structure for stugrade
-- ----------------------------
DROP TABLE IF EXISTS `stugrade`;
CREATE TABLE `stugrade` (
  `stu_id` varchar(11) NOT NULL COMMENT '学生编号',
  `set_id` int(11) NOT NULL COMMENT '问题设置编号',
  `right_count` int(11) NOT NULL COMMENT '正确题数',
  `wrong_count` int(11) NOT NULL COMMENT '错误题数',
  `total` int(11) NOT NULL COMMENT '总数',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `finish_time` datetime DEFAULT NULL COMMENT '结束时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` varchar(11) NOT NULL COMMENT '教师编号',
  `name` varchar(20) DEFAULT NULL COMMENT '教师姓名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师表';

-- ----------------------------
-- Records of teacher
-- ----------------------------
BEGIN;
INSERT INTO `teacher` VALUES ('0001', '测试教师号', '1234qwer');
INSERT INTO `teacher` VALUES ('0002', '测试股', '5c1f0f71ab3bc35c1cdd16194df88037');
COMMIT;

-- ----------------------------
-- Table structure for test_history
-- ----------------------------
DROP TABLE IF EXISTS `test_history`;
CREATE TABLE `test_history` (
  `stu_id` varchar(11) DEFAULT NULL COMMENT '学生编号',
  `q_set_id` int(11) NOT NULL,
  `q_id` int(11) DEFAULT NULL COMMENT '问题编号',
  `stu_choise` varchar(20) DEFAULT NULL COMMENT '学生选择',
  `stu_score` int(11) DEFAULT NULL COMMENT '学生分数',
  `test_time` datetime DEFAULT NULL COMMENT '测试时间',
  KEY `StuId` (`stu_id`),
  KEY `Qid` (`q_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试卷表';

-- ----------------------------
-- Records of test_history
-- ----------------------------
BEGIN;
INSERT INTO `test_history` VALUES ('15010157', 11, 17, '3,', 3, '2019-08-21 15:30:28');
INSERT INTO `test_history` VALUES ('15010157', 11, 18, '1,', 10, '2019-08-21 15:30:35');
INSERT INTO `test_history` VALUES ('15010157', 11, 19, '0,3,', 0, '2019-08-21 15:30:55');
INSERT INTO `test_history` VALUES ('15010157', 11, 20, '1,', 0, '2019-08-21 15:31:09');
INSERT INTO `test_history` VALUES ('15010157', 11, 23, '1,', 0, '2019-08-21 15:32:11');
INSERT INTO `test_history` VALUES ('15010157', 11, 24, '3,', 0, '2019-08-21 15:32:22');
INSERT INTO `test_history` VALUES ('15010157', 11, 25, '2,', 0, '2019-08-21 15:32:28');
INSERT INTO `test_history` VALUES ('15010157', 11, 27, '1,', 23, '2019-08-21 15:32:44');
INSERT INTO `test_history` VALUES ('15010157', 11, 28, '3,', 23, '2019-08-21 15:32:59');
COMMIT;

-- ----------------------------
-- View structure for gradeview
-- ----------------------------
DROP VIEW IF EXISTS `gradeview`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `gradeview` AS select `student`.`id` AS `stu_id`,`student`.`name` AS `stu_name`,max(`login_history`.`login_time`) AS `last_time`,sum(`test_history`.`stu_score`) AS `total` from ((`student` join `login_history`) join `test_history`) where ((`student`.`id` = `login_history`.`user_id`) and (`student`.`id` = `test_history`.`stu_id`)) group by `student`.`id`;

SET FOREIGN_KEY_CHECKS = 1;
