INSERT INTO tms.sys_role (role_key,role_name,role_level,create_date,update_date,creator_id,updater_id,remark) VALUES
	 ('root','超级管理员',0,NULL,NULL,NULL,NULL,NULL),
	 ('sys_admin','系统管理员',1,NULL,'2021-11-30 06:08:58',NULL,NULL,NULL),
	 ('senior','高管',10,NULL,'2021-11-30 06:09:19',NULL,NULL,NULL),
	 ('business_manager','商务经理',20,NULL,'2021-11-30 06:09:36',NULL,NULL,NULL),
	 ('project_manager','项目经理',30,NULL,'2021-11-30 06:09:56',NULL,NULL,NULL),
	 ('professional_manager','专业经理',40,NULL,'2021-12-15 05:50:54',NULL,NULL,NULL),
	 ('group_leader','小组长',50,NULL,'2021-11-29 09:56:44',NULL,NULL,NULL),
	 ('engineer','工程师',60,NULL,'2021-07-01 07:53:15',NULL,NULL,NULL),
	 ('qc_manager','QC经理',40,NULL,'2021-11-29 09:55:56',NULL,NULL,NULL),
	 ('other','其他',1000,NULL,NULL,NULL,NULL,NULL);
INSERT INTO tms.sys_role (role_key,role_name,role_level,create_date,update_date,creator_id,updater_id,remark) VALUES
	 ('qc','QC工程师',60,'2021-05-17 01:08:05','2021-07-12 02:59:37',NULL,NULL,NULL);