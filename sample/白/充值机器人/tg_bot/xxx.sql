/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 8.0.29 : Database - tg-app-hw-db_bot
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tg-app-hw-db_bot` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `tg-app-hw-db_bot`;

/*Table structure for table `tb_bot_admin_manager` */

DROP TABLE IF EXISTS `tb_bot_admin_manager`;

CREATE TABLE `tb_bot_admin_manager` (
  `tg__bot_admin_id` int NOT NULL AUTO_INCREMENT COMMENT '机器人管理员主键ID',
  `tb_bot_instance_id` int NOT NULL COMMENT '机器人实例ID',
  `tg_user_id` varchar(64) NOT NULL COMMENT '用户tg账号',
  `grant_end_date_time` timestamp NULL DEFAULT NULL,
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`tg__bot_admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机器人的管理员表';

/*Table structure for table `tb_bot_instance_manager` */

DROP TABLE IF EXISTS `tb_bot_instance_manager`;

CREATE TABLE `tb_bot_instance_manager` (
  `tb_bot_instance_id` int NOT NULL AUTO_INCREMENT COMMENT '机器人实例ID',
  `tg_bot_create_user_id` varchar(64) DEFAULT NULL COMMENT '机器人的添加人',
  `tg_bot_gs_user_id` varchar(128) DEFAULT NULL COMMENT '机器人的归属人',
  `bot_user_name` varchar(64) NOT NULL COMMENT '机器人用户名',
  `bot_token` varchar(128) NOT NULL COMMENT '机器token',
  `bot_no` varchar(64) DEFAULT NULL COMMENT '机器人型号',
  `bot_remark` varchar(64) DEFAULT NULL COMMENT '机器人描述',
  `bot_qy_status` int DEFAULT '1' COMMENT '机器人启用状态(1: 启用  0:停用)',
  `run_status` int DEFAULT '0' COMMENT '机器人运行状态(1: 运行中 0:停止)',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`tb_bot_instance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机器人实例管理';

/*Table structure for table `tb_in_qun_msg_manager` */

DROP TABLE IF EXISTS `tb_in_qun_msg_manager`;

CREATE TABLE `tb_in_qun_msg_manager` (
  `tb_in_qun_msg_manager_id` int NOT NULL AUTO_INCREMENT COMMENT '进群消息模板ID',
  `tg_qun_id` varchar(64) DEFAULT NULL COMMENT 'tg群ID',
  `tb_bot_instance_id` int DEFAULT NULL COMMENT '机器人实例ID',
  `msg_num` int DEFAULT '1' COMMENT '消息序号',
  `msg_context` text COMMENT '消息内容',
  `is_enable` tinyint(1) DEFAULT '1' COMMENT '是否开启进群欢迎',
  `msg_type` int DEFAULT NULL COMMENT '消息类型(1:文本  2:图片)',
  `buttons` text COMMENT '附加按钮',
  `delete_seconds` int DEFAULT NULL COMMENT '多长时间自动删除消息(秒)',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`tb_in_qun_msg_manager_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='进群欢迎消息管理';

/*Table structure for table `tb_keyword_reply_manager` */

DROP TABLE IF EXISTS `tb_keyword_reply_manager`;

CREATE TABLE `tb_keyword_reply_manager` (
  `tb_keyword_id` int NOT NULL AUTO_INCREMENT COMMENT '群内关键词主键',
  `tg_qun_id` varchar(64) DEFAULT NULL COMMENT 'tg群ID',
  `tb_bot_instance_id` int DEFAULT NULL COMMENT '机器人实例ID',
  `key_word` varchar(256) NOT NULL COMMENT '关键词',
  `reply_context` text NOT NULL COMMENT '回复内容',
  `reply_mode` int NOT NULL DEFAULT '1' COMMENT '回复模式(1:普通返回 2:回复模式)',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`tb_keyword_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='群内关键词回复表';

/*Table structure for table `tb_pri_keyword_reply_manager` */

DROP TABLE IF EXISTS `tb_pri_keyword_reply_manager`;

CREATE TABLE `tb_pri_keyword_reply_manager` (
  `tb_pri_keyword_id` int NOT NULL AUTO_INCREMENT COMMENT '群内关键词主键',
  `tb_bot_instance_id` int DEFAULT NULL COMMENT '机器人实例ID',
  `key_word` varchar(256) NOT NULL COMMENT '关键词',
  `reply_context` text NOT NULL COMMENT '回复内容',
  `reply_mode` int NOT NULL DEFAULT '1' COMMENT '回复模式(1:普通返回 2:回复模式)',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`tb_pri_keyword_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='私聊关键词回复表';

/*Table structure for table `tb_qun_config_manager` */

DROP TABLE IF EXISTS `tb_qun_config_manager`;

CREATE TABLE `tb_qun_config_manager` (
  `tb_qun_config_id` int NOT NULL AUTO_INCREMENT COMMENT '群配置管理表主键',
  `tg_qun_id` varchar(64) DEFAULT NULL COMMENT 'tg群ID',
  `is_del_inqun_msg` int NOT NULL DEFAULT '0' COMMENT '是否删除进群通知(1:删除 0:不删除)',
  `is_del_tuiqun_msg` int NOT NULL DEFAULT '0' COMMENT '是否删除退群通知(1:删除 0:不删除)',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`tb_qun_config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='群配置管理表';

/*Table structure for table `tb_qun_manager` */

DROP TABLE IF EXISTS `tb_qun_manager`;

CREATE TABLE `tb_qun_manager` (
  `tg_qun_id` varchar(64) NOT NULL COMMENT 'tg群ID',
  `qun_name` varchar(1024) DEFAULT NULL COMMENT '群名称',
  `qun_type` int NOT NULL COMMENT '群类型(1:group 2:supergroup 3:private 4:channel)',
  `qun_user_name` varchar(512) DEFAULT NULL COMMENT '群用户名',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`tg_qun_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='tg群管理';

/*Table structure for table `tb_qun_schedule_msg_manager` */

DROP TABLE IF EXISTS `tb_qun_schedule_msg_manager`;

CREATE TABLE `tb_qun_schedule_msg_manager` (
  `tb_qun_schedule_msg_manager_id` int NOT NULL AUTO_INCREMENT COMMENT '群定时消息ID',
  `tg_qun_id` varchar(64) DEFAULT NULL COMMENT 'tg群ID',
  `tb_bot_instance_id` int DEFAULT NULL COMMENT '机器人实例ID',
  `last_send_time` timestamp NULL DEFAULT NULL COMMENT '上一次发送时间',
  `next_send_time` timestamp NULL DEFAULT NULL COMMENT '下次发送时间',
  `msg_type` int DEFAULT NULL COMMENT '消息类型(1:文本  2:图片 3:视频)',
  `interval_seconds` int DEFAULT NULL COMMENT '重复间隔(秒)',
  `msg_context` text COMMENT '消息内容',
  `buttons` text COMMENT '附加按钮',
  `delete_seconds` int DEFAULT NULL COMMENT '多长时间自动删除消息(秒)',
  `is_enable` tinyint(1) DEFAULT NULL COMMENT '是否有效',
  `remark` text COMMENT '备注',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`tb_qun_schedule_msg_manager_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='群定时消息管理';

/*Table structure for table `tb_schedule_group_msg_manager` */

DROP TABLE IF EXISTS `tb_schedule_group_msg_manager`;

CREATE TABLE `tb_schedule_group_msg_manager` (
  `schedule_job_id` int NOT NULL AUTO_INCREMENT COMMENT '定时调度任务ID',
  `tg_user_id` varchar(64) DEFAULT NULL COMMENT '用户tg账号',
  `tg_qun_id` varchar(64) DEFAULT NULL COMMENT 'tg群ID',
  `tb_bot_instance_id` int DEFAULT NULL COMMENT '机器人实例ID',
  `msg_context` text COMMENT '消息类容',
  `job_status` int NOT NULL DEFAULT '0' COMMENT '任务状态(1启用 0停用)',
  `job_cron` varchar(128) NOT NULL COMMENT '任务时间表达式',
  `user_cron` varchar(128) NOT NULL COMMENT '用户定时表达式',
  `job_group` varchar(256) DEFAULT NULL COMMENT '任务组',
  `job_name` varchar(256) DEFAULT NULL COMMENT '任务名称',
  `job_class_path` varchar(256) NOT NULL COMMENT '任务全类名',
  `job_describe` varchar(256) DEFAULT NULL COMMENT '任务功能描述',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`schedule_job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时群消息管理';

/*Table structure for table `tb_schedule_job_manager` */

DROP TABLE IF EXISTS `tb_schedule_job_manager`;

CREATE TABLE `tb_schedule_job_manager` (
  `schedule_job_id` int NOT NULL AUTO_INCREMENT COMMENT '定时调度任务ID',
  `tg_user_id` varchar(64) DEFAULT NULL COMMENT '用户tg账号',
  `schedule_msg_id` int DEFAULT NULL COMMENT '定时消息ID',
  `job_group` varchar(256) NOT NULL COMMENT '任务组',
  `job_name` varchar(256) NOT NULL COMMENT '任务名称',
  `job_cron` varchar(128) NOT NULL COMMENT '任务时间表达式',
  `job_class_path` varchar(256) NOT NULL COMMENT '任务全类名',
  `job_status` int NOT NULL DEFAULT '0' COMMENT '任务状态(1启用 0停用)',
  `job_describe` varchar(256) DEFAULT NULL COMMENT '任务功能描述',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`schedule_job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

/*Table structure for table `tb_schedule_msg_manager` */

DROP TABLE IF EXISTS `tb_schedule_msg_manager`;

CREATE TABLE `tb_schedule_msg_manager` (
  `schedule_msg_id` int NOT NULL AUTO_INCREMENT COMMENT '定时消息ID',
  `tg_user_id` varchar(64) DEFAULT NULL COMMENT '用户tg账号',
  `tb_bot_instance_id` int DEFAULT NULL COMMENT '机器人实例ID',
  `qun_chat_id` varchar(128) DEFAULT NULL COMMENT '群会话ID',
  `msg_context` text COMMENT '消息内容',
  `msg_type` int DEFAULT NULL COMMENT '消息类型(1:文本  2:图片)',
  `buttons` text COMMENT '附加按钮',
  `delete_seconds` int DEFAULT NULL COMMENT '多长时间自动删除消息(秒)',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`schedule_msg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='定时消息管理';

/*Table structure for table `tb_sys_param_manager` */

DROP TABLE IF EXISTS `tb_sys_param_manager`;

CREATE TABLE `tb_sys_param_manager` (
  `tg_param_id` int NOT NULL AUTO_INCREMENT COMMENT '参数ID',
  `key_` varchar(1024) DEFAULT NULL COMMENT '键',
  `value` varchar(1024) DEFAULT NULL COMMENT '值',
  `remark` text COMMENT '备注',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`tg_param_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

/*Table structure for table `tb_user_botinstance_qun_manager` */

DROP TABLE IF EXISTS `tb_user_botinstance_qun_manager`;

CREATE TABLE `tb_user_botinstance_qun_manager` (
  `tb_user_botinstance_qun_id` int NOT NULL AUTO_INCREMENT COMMENT '用户_机器人实例_群关系主键',
  `tg_user_id` varchar(64) DEFAULT NULL COMMENT '用户tg账号',
  `tb_bot_instance_id` int DEFAULT NULL COMMENT '机器人实例ID',
  `tg_qun_id` varchar(64) DEFAULT NULL COMMENT 'tg群ID',
  `bot_role` int NOT NULL COMMENT '机器人角色(1:群主 2:管理员 3:成员)',
  `is_anonymous` tinyint(1) NOT NULL COMMENT '保持匿名',
  `can_manage_chat` tinyint(1) NOT NULL COMMENT '管理群',
  `can_delete_messages` tinyint(1) NOT NULL COMMENT '删除消息',
  `can_restrict_members` tinyint(1) NOT NULL COMMENT '封禁该用户',
  `can_promote_members` tinyint(1) NOT NULL COMMENT '添加新管理员',
  `can_change_info` tinyint(1) NOT NULL COMMENT '修改群组信息',
  `can_invite_users` tinyint(1) NOT NULL COMMENT '生成邀请链接',
  `can_pin_messages` tinyint(1) NOT NULL COMMENT '置顶消息',
  `can_manage_video_chats` tinyint(1) NOT NULL COMMENT '管理视频通话',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`tb_user_botinstance_qun_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户_机器人实例_群_关系管理';

/*Table structure for table `tb_user_cun_qu_manager` */

DROP TABLE IF EXISTS `tb_user_cun_qu_manager`;

CREATE TABLE `tb_user_cun_qu_manager` (
  `tg_user_cun_qu_uuid` varchar(64) NOT NULL COMMENT '用户存取记录UUID',
  `tg_user_id` varchar(64) NOT NULL COMMENT 'tg用户账号',
  `tg_user_msg_id` int NOT NULL COMMENT 'tg用户消息Id',
  `kefu_qun_id` varchar(64) NOT NULL COMMENT '客服群ID',
  `qun_msg_id` int NOT NULL COMMENT '群消息Id',
  `bind_id` varchar(1024) NOT NULL COMMENT '绑定ID',
  `cun_qu_type` int NOT NULL COMMENT '存取类型(1:存  2:取)',
  `cun_account_type` int DEFAULT NULL COMMENT '存入的账户类型(1:USDT 2:汇旺)',
  `cun_cru_usdt_account_info` text COMMENT '存入时的账户信息',
  `cun_cru_hw_account_info` text COMMENT '存入时的汇旺账户信息',
  `cun_file_id` varchar(512) DEFAULT NULL COMMENT '存入时的fileID',
  `qu_account_type` int DEFAULT NULL COMMENT '取出的账户类型(1:USDT 2:汇旺)',
  `qu_amount` double DEFAULT NULL COMMENT '取金额',
  `qu_cru_usdt_account_info` text COMMENT '取出时的USDT账户信息',
  `qu_cru_hw_account_info` text COMMENT '取出时的汇旺账户信息',
  `order_status` int NOT NULL COMMENT '订单状态(1:已提交 2:正在处理 3:已完成)',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`tg_user_cun_qu_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户存取记录表管理';

/*Table structure for table `tb_user_ext_info_manager` */

DROP TABLE IF EXISTS `tb_user_ext_info_manager`;

CREATE TABLE `tb_user_ext_info_manager` (
  `tg_user_id` varchar(64) NOT NULL COMMENT 'tg用户账号',
  `bind_id` varchar(1024) DEFAULT NULL COMMENT '绑定ID',
  `qu_hw_info` text COMMENT '取汇旺信息',
  `qu_usdt_info` text COMMENT '取USDT信息',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`tg_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户拓展信息表管理';

/*Table structure for table `tb_user_manager` */

DROP TABLE IF EXISTS `tb_user_manager`;

CREATE TABLE `tb_user_manager` (
  `tg_user_id` varchar(64) NOT NULL COMMENT '用户tg账号',
  `user_first_name` varchar(1024) DEFAULT NULL COMMENT '用户姓',
  `is_bot` tinyint(1) DEFAULT NULL COMMENT '是否机器人',
  `user_last_name` varchar(1024) DEFAULT NULL COMMENT '用户名称',
  `user_name` varchar(128) DEFAULT NULL COMMENT '用户名',
  `language_code` varchar(128) DEFAULT NULL COMMENT '语言环境',
  `user_role` int NOT NULL DEFAULT '1' COMMENT '用户角色(1.普通用户 2,代理 3:超级管理员)',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`tg_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户管理';

/*Table structure for table `tb_user_qun_manager` */

DROP TABLE IF EXISTS `tb_user_qun_manager`;

CREATE TABLE `tb_user_qun_manager` (
  `user_qun_id` int NOT NULL AUTO_INCREMENT COMMENT '用户与群关系主键',
  `tg_user_id` varchar(64) DEFAULT NULL COMMENT '用户tg账号',
  `tg_qun_id` varchar(64) DEFAULT NULL COMMENT 'tg群ID',
  `create_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(128) NOT NULL DEFAULT 'system' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_qun_id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户_群_关系管理';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
