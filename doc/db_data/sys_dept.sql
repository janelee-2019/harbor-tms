INSERT INTO tms.sys_dept (dept_code,dept_name,parent_id,dept_level,ancestors,remark,company_id,dept_leader) VALUES
	 ('B','乙方',0,0,'0',NULL,1,NULL),
	 ('A','甲方',0,0,'0',NULL,9,NULL),
	 (NULL,'子公司-1',2,0,'0,2',NULL,9,NULL),
	 (NULL,'子公司-2',2,0,'0,2',NULL,9,NULL),
	 (NULL,'子公司-3',2,0,'0,2',NULL,9,NULL),
	 (NULL,'子公司-4',2,0,'0,2',NULL,9,NULL),
	 (NULL,'子公司-5',2,0,'0,2',NULL,9,NULL),
	 (NULL,'子公司-6',2,0,'0,2',NULL,9,NULL),
	 (NULL,'子公司-7',2,0,'0,2',NULL,9,NULL),
	 (NULL,'子公司-8',2,0,'0,2',NULL,9,NULL);
INSERT INTO tms.sys_dept (dept_code,dept_name,parent_id,dept_level,ancestors,remark,company_id,dept_leader) VALUES
	 (NULL,'子公司-9',2,0,'0,2',NULL,9,NULL),
	 (NULL,'子公司-10',2,0,'0,2',NULL,9,NULL),
	 (NULL,'子公司-11',2,0,'0,2',NULL,9,NULL),
	 (NULL,'子公司-12',2,0,'0,2',NULL,9,NULL),
	 (NULL,'子公司-13',2,0,'0,2',NULL,9,NULL),
	 (NULL,'子公司-14',2,0,'0,2',NULL,9,NULL),
	 (NULL,'子公司-15',2,0,'0,2',NULL,9,NULL),
	 (NULL,'子公司-16',2,0,'0,2',NULL,9,NULL),
	 (NULL,'子公司-17',2,0,'0,2',NULL,9,NULL),
	 (NULL,'项目部',1,10,'0,1',NULL,1,NULL);
INSERT INTO tms.sys_dept (dept_code,dept_name,parent_id,dept_level,ancestors,remark,company_id,dept_leader) VALUES
	 (NULL,'电仪专业',42,20,'0,1,42',NULL,1,NULL),
	 (NULL,'机械&土建专业',42,20,'0,1,42',NULL,1,NULL),
	 (NULL,'管道专业',42,20,'0,1,42',NULL,1,NULL),
	 (NULL,'工艺&工厂信息专业',42,20,'0,1,42',NULL,1,NULL),
	 (NULL,'工程部',1,10,'0,1',NULL,1,NULL),
	 (NULL,'数字化',51,20,'0,1,51',NULL,1,NULL),
	 (NULL,'数字处理',52,30,'0,1,51,52',NULL,1,NULL),
	 (NULL,'Drafting-1组',57,40,'0,1,51,52,57',NULL,1,NULL),
	 (NULL,'Drafting-2组',57,40,'0,1,51,52,57',NULL,1,NULL),
	 (NULL,'培训部',57,0,'0,1,51,52,57',NULL,1,NULL);
INSERT INTO tms.sys_dept (dept_code,dept_name,parent_id,dept_level,ancestors,remark,company_id,dept_leader) VALUES
	 (NULL,'Drafting-3组',57,6,'0,1,51,52,57',NULL,1,NULL),
	 (NULL,'Drafting-4组',57,6,'0,1,51,52,57',NULL,1,NULL),
	 (NULL,'QC',57,6,'0,1,51,52,57',NULL,1,NULL),
	 (NULL,'SPF组',57,6,'0,1,51,52,57',NULL,1,NULL),
	 (NULL,'子公司-18',2,10,'0,2',NULL,9,NULL);