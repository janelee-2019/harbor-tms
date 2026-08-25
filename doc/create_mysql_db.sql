SET NAMES utf8mb4;

-- ============================================================
-- 1. 创建数据库 edms 并指定字符集
--    账号：root
--    密码：root
-- ============================================================
CREATE
    DATABASE IF NOT EXISTS `tms` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE
    `tms`;

-- tms.sys_api_log definition

CREATE TABLE `sys_api_log` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                               `user_id` bigint NOT NULL COMMENT '用户 ID',
                               `http_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'HTTP Method',
                               `request_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求 URL',
                               `client_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户端 IP 地址',
                               `client_os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户端操作系统',
                               `json_result` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT 'JSON 响应',
                               `request_param` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '请求参数',
                               `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
                               `message` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '返回信息',
                               `create_date` datetime DEFAULT NULL COMMENT '创建时间',
                               `user_agent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浏览器 UA 信息',
                               `client_browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户端浏览器',
                               `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日志标签',
                               `module` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '模块',
                               `duration` bigint DEFAULT NULL COMMENT '执行时长（毫秒）',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='接口操作日志表';


-- tms.sys_captcha definition

CREATE TABLE `sys_captcha` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                               `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '验证码标识',
                               `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '验证码',
                               `expire_date` datetime NOT NULL COMMENT '验证码过期时间',
                               `create_date` datetime NOT NULL COMMENT '验证码创建时间',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='图形验证码表';


-- tms.sys_company definition

CREATE TABLE `sys_company` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                               `company_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司代码',
                               `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司名称',
                               `company_short_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司简称',
                               `company_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司类型',
                               `company_reg_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司工商登记号',
                               `company_country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司所在国家',
                               `company_province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司所在省份',
                               `company_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司所在城市',
                               `company_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司详细地址',
                               `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='公司信息表';


-- tms.sys_dept definition

CREATE TABLE `sys_dept` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                            `dept_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门编号',
                            `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
                            `parent_id` bigint NOT NULL COMMENT '父级部门 ID',
                            `dept_level` bigint NOT NULL DEFAULT '0' COMMENT '部门级别',
                            `ancestors` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门祖级列表',
                            `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
                            `company_id` bigint NOT NULL COMMENT '公司 ID',
                            `dept_leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门领导',
                            PRIMARY KEY (`id`),
                            KEY `fk_sys_dept_company_id` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='部门信息表';


-- tms.sys_dict_data definition

CREATE TABLE `sys_dict_data` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                                 `dict_label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典标签',
                                 `dict_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值',
                                 `dict_sort` bigint DEFAULT '0' COMMENT '字典排序',
                                 `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
                                 `dict_type_id` bigint NOT NULL COMMENT '字典类型 ID',
                                 PRIMARY KEY (`id`),
                                 KEY `fk_sys_dict_data_type_id` (`dict_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典数据表';


-- tms.sys_dict_type definition

CREATE TABLE `sys_dict_type` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                                 `dict_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典 Key',
                                 `dict_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
                                 `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `uni_dict_key` (`dict_key`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典类型表';


-- tms.sys_function definition

CREATE TABLE `sys_function` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                                `function_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '功能权限字符串',
                                `function_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能名称',
                                `parent_id` bigint NOT NULL COMMENT '父级功能 ID',
                                `function_sort` bigint DEFAULT '0' COMMENT '功能排序',
                                `function_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能类型',
                                `function_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '功能状态',
                                `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
                                `ancestors` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能祖级列表',
                                `function_level` bigint NOT NULL DEFAULT '0' COMMENT '功能级别',
                                `vue_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                `vue_component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                `vue_redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                `vue_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                `vue_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                `vue_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                `vue_affix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                `vue_badge` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='功能信息表';


-- tms.sys_login_log definition

CREATE TABLE `sys_login_log` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                                 `login_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户登录名',
                                 `client_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户端 IP 地址',
                                 `client_browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户端浏览器',
                                 `client_os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户端操作系统',
                                 `login_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录状态',
                                 `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '服务端返回信息',
                                 `login_date` datetime DEFAULT NULL COMMENT '登录日期',
                                 `user_agent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浏览器 UA 信息',
                                 `user_id` bigint DEFAULT NULL COMMENT '用户 ID',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户登录日志表';


-- tms.sys_role definition

CREATE TABLE `sys_role` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                            `role_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
                            `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
                            `role_level` bigint DEFAULT '0' COMMENT '角色等级',
                            `create_date` datetime DEFAULT NULL COMMENT '角色信息创建时间',
                            `update_date` datetime DEFAULT NULL COMMENT '角色信息最后更新时间',
                            `creator_id` bigint DEFAULT NULL COMMENT '角色信息创建者',
                            `updater_id` bigint DEFAULT NULL COMMENT '角色信息最后更新者',
                            `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uni_role_key` (`role_key`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色信息表';


-- tms.sys_role_function definition

CREATE TABLE `sys_role_function` (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                                     `role_id` bigint NOT NULL COMMENT '角色 ID',
                                     `function_id` bigint NOT NULL COMMENT '功能 ID',
                                     PRIMARY KEY (`id`),
                                     KEY `fk_sys_role_function_role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2930 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色与功能关联表';


-- tms.sys_setting definition

CREATE TABLE `sys_setting` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                               `setting_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                               `setting_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                               `setting_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                               `setting_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='参数设置表';


-- tms.sys_token definition

CREATE TABLE `sys_token` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                             `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户令牌',
                             `user_id` bigint NOT NULL COMMENT '用户 ID',
                             `expire_date` datetime NOT NULL COMMENT '令牌过期时间',
                             `create_date` datetime NOT NULL COMMENT '令牌创建时间',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1207 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户令牌表';


-- tms.sys_user definition

CREATE TABLE `sys_user` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                            `login_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户登录名',
                            `login_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户登录密码',
                            `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码加密盐',
                            `last_login_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户最后登录 IP',
                            `last_login_date` datetime DEFAULT NULL COMMENT '用户最后登录时间',
                            `create_date` datetime DEFAULT NULL COMMENT '用户信息创建时间',
                            `update_date` datetime DEFAULT NULL COMMENT '用户信息最后更新时间',
                            `password_update_date` datetime DEFAULT NULL COMMENT '用户密码最后更新时间',
                            `creator_id` bigint DEFAULT NULL COMMENT '用户信息创建者',
                            `updater_id` bigint DEFAULT NULL COMMENT '用户信息最后更新者',
                            `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
                            `account_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '帐号状态',
                            `employee_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '员工号',
                            `employee_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '员工姓名',
                            `employee_tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '员工电话',
                            `employee_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '员工 Email',
                            `employee_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '员工地址',
                            `employee_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '员工照片',
                            `employee_sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '员工性别',
                            `dept_id` bigint NOT NULL COMMENT '部门 ID',
                            `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uni_user_login_name` (`login_name`),
                            KEY `fk_sys_user_dept_id` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=375 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息表';


-- tms.sys_user_role definition

CREATE TABLE `sys_user_role` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                                 `user_id` bigint NOT NULL COMMENT '用户 ID',
                                 `role_id` bigint NOT NULL COMMENT '角色 ID',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=485 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户与角色关联表';


-- tms.tsk_batch definition

CREATE TABLE `tsk_batch` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                             `sr_id` bigint NOT NULL COMMENT '服务请求 ID',
                             `sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内部序号',
                             `package_id` bigint NOT NULL COMMENT '包 ID',
                             `package_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '包序号',
                             `batch_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '批次号',
                             `group_id` bigint NOT NULL COMMENT '小组 ID',
                             `batch_quantity` bigint DEFAULT NULL COMMENT '批次中图纸或文档数量',
                             `batch_spec_mh` decimal(18,2) DEFAULT NULL COMMENT '批次额定工时',
                             `batch_issue_mh` decimal(18,2) DEFAULT NULL COMMENT '批次下发工时',
                             `batch_target_date` datetime DEFAULT NULL COMMENT '批次目标完成时间',
                             `batch_completed_date` datetime DEFAULT NULL COMMENT '批次完成时间',
                             `batch_start_date` datetime DEFAULT NULL COMMENT '批次任务开始时间',
                             `qc_rating` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'QC 评分',
                             `qc_completed_date` datetime DEFAULT NULL COMMENT 'QC 完成时间',
                             `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
                             `issue_date` datetime DEFAULT NULL COMMENT '下发时间',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             `issuer_id` bigint DEFAULT NULL COMMENT '下发者',
                             `create_by` bigint DEFAULT NULL COMMENT '创建人',
                             `update_by` bigint DEFAULT NULL COMMENT '更新人',
                             `batch_work_days` bigint DEFAULT NULL COMMENT '工作日天数',
                             `qc_id` bigint DEFAULT NULL COMMENT '质控员',
                             `is_issued` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否已下发',
                             `is_distributed` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否已分图纸',
                             `is_rated` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否已评分',
                             `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识 (0:未删除, 1:已删除)',
                             PRIMARY KEY (`id`),
                             KEY `fk_tsk_batch_package_id` (`package_id`),
                             KEY `fk_tsk_batch_sr_id` (`sr_id`),
                             KEY `fk_tsk_batch_group_id` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=488 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='批次信息表';


-- tms.tsk_drawing definition

CREATE TABLE `tsk_drawing` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                               `sr_id` bigint NOT NULL COMMENT '服务请求 ID',
                               `sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内部序号',
                               `package_id` bigint NOT NULL COMMENT '包 ID',
                               `package_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '包序号',
                               `batch_id` bigint NOT NULL COMMENT '批次 ID',
                               `batch_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '批次号',
                               `drawing_id` bigint NOT NULL COMMENT '图纸内部 ID',
                               `drawing_file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图纸文件名',
                               `drawing_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图号',
                               `drawing_file_dir` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图纸所在文件夹',
                               `drawing_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图纸范畴',
                               `drawing_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图纸类型',
                               `draft_mh` decimal(18,2) DEFAULT NULL COMMENT '绘图工时',
                               `drafter_id` bigint DEFAULT NULL COMMENT '绘图员 ID',
                               `proofread_mh` decimal(18,2) DEFAULT NULL COMMENT '校对工时',
                               `proofreader_id` bigint DEFAULT NULL COMMENT '校对员 ID',
                               `qc_id` bigint DEFAULT NULL COMMENT '初级质控员 ID',
                               `sqc_id` bigint DEFAULT NULL COMMENT '高级质控员 ID',
                               `drawing_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图纸状态',
                               `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                               `create_by` bigint DEFAULT NULL COMMENT '创建人',
                               `update_by` bigint DEFAULT NULL COMMENT '更新人',
                               `dc_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'DC 序号',
                               `drawing_file_path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图纸文件绝对路径',
                               `proc_inst_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '流程 ID',
                               `draft_received_date` datetime DEFAULT NULL COMMENT '绘图接收时间',
                               `proofread_received_date` datetime DEFAULT NULL COMMENT '校对接收时间',
                               `qc_received_date` datetime DEFAULT NULL COMMENT 'QC 接收时间',
                               `sqc_received_date` datetime DEFAULT NULL COMMENT 'SQC 接收时间',
                               `draft_issued_date` datetime DEFAULT NULL COMMENT '绘图下发时间',
                               `proofread_issued_date` datetime DEFAULT NULL COMMENT '校对下发时间',
                               `qc_issued_date` datetime DEFAULT NULL COMMENT 'QC 下发时间',
                               `sqc_issued_date` datetime DEFAULT NULL COMMENT 'SQC 下发时间',
                               `draft_issuer_id` bigint DEFAULT NULL COMMENT '绘图下发者',
                               `proofread_issuer_id` bigint DEFAULT NULL COMMENT '校对下发者',
                               `qc_issuer_id` bigint DEFAULT NULL COMMENT 'QC 下发者',
                               `sqc_issuer_id` bigint DEFAULT NULL COMMENT 'SQC 下发者',
                               `draft_submitted_date` datetime DEFAULT NULL COMMENT '绘图提交时间',
                               `proofread_submitted_date` datetime DEFAULT NULL COMMENT '校对提交时间',
                               `qc_submitted_date` datetime DEFAULT NULL COMMENT 'QC 提交时间',
                               `sqc_submitted_date` datetime DEFAULT NULL COMMENT 'SQC 提交时间',
                               `task_due_date` datetime DEFAULT NULL COMMENT '图纸任务截止日期',
                               `qc_rating` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'QC 评分',
                               `qc_rating_date` datetime DEFAULT NULL COMMENT 'QC 评分时间',
                               `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识 (0:未删除, 1:已删除)',
                               PRIMARY KEY (`id`),
                               KEY `fk_tsk_drawing_batch_id` (`batch_id`),
                               KEY `fk_tsk_drawing_package_id` (`package_id`),
                               KEY `fk_tsk_drawing_sr_id` (`sr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17149 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='图纸信息表';


-- tms.tsk_package definition

CREATE TABLE `tsk_package` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                               `sr_id` bigint NOT NULL COMMENT '服务请求 ID',
                               `package_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '包序号',
                               `package_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '包类型',
                               `team_id` bigint NOT NULL COMMENT '负责团队 ID',
                               `package_quantity` bigint DEFAULT NULL COMMENT '包中的图纸或文档数量',
                               `package_mh` decimal(18,2) DEFAULT NULL COMMENT '包工时',
                               `package_start_date` datetime DEFAULT NULL COMMENT '包任务开始时间',
                               `package_completed_date` datetime DEFAULT NULL COMMENT '包任务完成时间',
                               `package_work_days` bigint DEFAULT NULL COMMENT '工作日天数',
                               `issue_date` datetime DEFAULT NULL COMMENT '包下发时间',
                               `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                               `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                               `issuer_id` bigint DEFAULT NULL COMMENT '下发者',
                               `create_by` bigint DEFAULT NULL COMMENT '创建人',
                               `update_by` bigint DEFAULT NULL COMMENT '更新人',
                               `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
                               `sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内部序号',
                               `package_target_date` datetime DEFAULT NULL COMMENT '包任务目标完成时间',
                               `is_issued` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否已下发',
                               `is_distributed` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否已分批次',
                               `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识 (0:未删除, 1:已删除)',
                               PRIMARY KEY (`id`),
                               KEY `fk_tsk_package_sr_id` (`sr_id`),
                               KEY `fk_tsk_package_team_id` (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='分包信息表';


-- tms.tsk_project_summary definition

CREATE TABLE `tsk_project_summary` (
                                       `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
                                       `sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内部序号',
                                       `sr_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '服务请求号',
                                       `dc_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'DC 序号',
                                       `affiliate_id` bigint DEFAULT NULL COMMENT '服务发起公司',
                                       `sr_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '服务请求类型',
                                       `sr_received_date` datetime DEFAULT NULL COMMENT '服务请求接收时间',
                                       `tp_submitted_date` datetime DEFAULT NULL COMMENT '技术标提交时间',
                                       `tp_approved_date` datetime DEFAULT NULL COMMENT '技术标批准时间',
                                       `cp_submitted_date` datetime DEFAULT NULL COMMENT '商务标提交时间',
                                       `cp_approved_date` datetime DEFAULT NULL COMMENT '商务标批准时间',
                                       `amcc_submitted_date` datetime DEFAULT NULL COMMENT '成果物提交时间',
                                       `amcc_approved_date` datetime DEFAULT NULL COMMENT '成果物批准时间',
                                       `sr_start_date` datetime DEFAULT NULL COMMENT '服务请求开始时间',
                                       `sr_target_date` datetime DEFAULT NULL COMMENT '服务请求目标完成时间',
                                       `sr_completed_date` datetime DEFAULT NULL COMMENT '服务请求完成时间',
                                       `sr_plan_weeks` bigint DEFAULT NULL COMMENT '服务请求计划周期（周）',
                                       `task_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '任务状态',
                                       `site_visit` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否需要监工',
                                       `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态',
                                       `epm_current_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '服务请求方当前状态',
                                       `approved_mh` decimal(18,2) DEFAULT NULL COMMENT '批准工时',
                                       `consumed_mh` decimal(18,2) DEFAULT NULL COMMENT '已消耗工时',
                                       `remaining_mh` decimal(18,2) DEFAULT NULL COMMENT '剩余工时',
                                       `task_progress` decimal(18,2) DEFAULT NULL COMMENT '任务执行进度',
                                       `budget_awarded` decimal(18,2) DEFAULT NULL COMMENT '预算',
                                       `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                       `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                       `create_by` bigint DEFAULT NULL COMMENT '创建人',
                                       `update_by` bigint DEFAULT NULL COMMENT '更新人',
                                       `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
                                       `is_distributed` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否已分包',
                                       `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                       `project_title` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                       `po_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                       `sps_manager` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                       `last_submit_date` datetime DEFAULT NULL,
                                       `sr_actual_execution_days` bigint DEFAULT NULL,
                                       `after_last_submit_to_sabic_for_approval_day` bigint DEFAULT NULL,
                                       `execution_sr_duration_day` bigint DEFAULT NULL,
                                       `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识 (0:未删除, 1:已删除)',
                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY `uni_tsk_proj_sum_sn` (`sn`),
                                       KEY `fk_tsk_project_summary_affiliate_id` (`affiliate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=486 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='项目摘要表';