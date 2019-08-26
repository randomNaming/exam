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

 Date: 22/08/2019 15:29:00
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
-- View structure for gradeview
-- ----------------------------
DROP VIEW IF EXISTS `gradeview`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `gradeview` AS select `student`.`id` AS `stu_id`,`student`.`name` AS `stu_name`,max(`login_history`.`login_time`) AS `last_time`,sum(`test_history`.`stu_score`) AS `total` from ((`student` join `login_history`) join `test_history`) where ((`student`.`id` = `login_history`.`user_id`) and (`student`.`id` = `test_history`.`stu_id`)) group by `student`.`id`;

SET FOREIGN_KEY_CHECKS = 1;
