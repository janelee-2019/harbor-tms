INSERT INTO tms.sys_dict_data (dict_label,dict_value,dict_sort,remark,dict_type_id) VALUES
	 ('男','male',1,NULL,1),
	 ('女','female',2,NULL,1),
	 ('未知','unknown',3,NULL,1),
	 ('AS-BUILT','as-built',1,NULL,2),
	 ('MOC','moc',2,NULL,2),
	 ('IFC','ifc',3,NULL,2),
	 ('Planning','planning',1,NULL,3),
	 ('Execution','execution',2,NULL,3),
	 ('Issue Fixing Online','issue_fixing_online',3,NULL,3),
	 ('Completed','completed',4,NULL,3);
INSERT INTO tms.sys_dict_data (dict_label,dict_value,dict_sort,remark,dict_type_id) VALUES
	 ('T.P Submitted','tp_submitted',2,NULL,4),
	 ('C.P Submitted','cp_submitted',3,NULL,4),
	 ('E&PM Support','epm_support',4,NULL,4),
	 ('25% AMCC Submitted','25_ammcc_submitted',5,NULL,4),
	 ('50% AMCC Submitted','50_ammcc_submitted',6,NULL,4),
	 ('75% AMCC Submitted','75_ammcc_submitted',7,NULL,4),
	 ('AMCC Submitted','amcc_submitted',8,NULL,4),
	 ('Invoice Submitted','invoice_submitted',9,NULL,4),
	 ('Close','closed',10,NULL,4),
	 ('Not Closed','not_closed',1,NULL,5);
INSERT INTO tms.sys_dict_data (dict_label,dict_value,dict_sort,remark,dict_type_id) VALUES
	 ('Closed','closed',2,NULL,5),
	 ('New','new',1,NULL,6),
	 ('Issued','issued',2,NULL,6),
	 ('EDMS','edms',1,NULL,7),
	 ('DRAFT','draft',2,NULL,7),
	 ('SPI','spi',3,NULL,7),
	 ('SPPID','sppid',4,NULL,7),
	 ('Required','y',1,NULL,8),
	 ('Not Required','n',2,NULL,8),
	 ('New','new',1,NULL,9);
INSERT INTO tms.sys_dict_data (dict_label,dict_value,dict_sort,remark,dict_type_id) VALUES
	 ('Issued','issued',2,NULL,9),
	 ('Document','document',1,NULL,10),
	 ('Drawing','drawing',2,NULL,10),
	 ('ISO','iso',3,NULL,10),
	 ('ILD','ild',4,NULL,10),
	 ('New','new',1,NULL,12),
	 ('Exist','exist',2,NULL,12),
	 ('Menu','menu',1,NULL,13),
	 ('Function','function',2,NULL,13),
	 ('Distributed','distributed',3,NULL,6);
INSERT INTO tms.sys_dict_data (dict_label,dict_value,dict_sort,remark,dict_type_id) VALUES
	 ('SR Received','sr_recieved',1,NULL,4),
	 ('SP3D','sp3d',5,NULL,7),
	 ('新建图纸','new',5,NULL,11),
	 ('图纸已取消','drawing_canceled',10,NULL,11),
	 ('图纸已完成','drawing_completed',15,NULL,11),
	 ('绘图已下发','draft_issued',20,NULL,11),
	 ('校对已下发','proofread_issued',25,NULL,11),
	 ('QC1已下发','qc_issued',30,NULL,11),
	 ('QC2已下发','sqc_issued',35,NULL,11),
	 ('绘图已接收','draft_received',40,NULL,11);
INSERT INTO tms.sys_dict_data (dict_label,dict_value,dict_sort,remark,dict_type_id) VALUES
	 ('校对已接收','proofread_received',45,NULL,11),
	 ('QC1已接收','qc_received',50,NULL,11),
	 ('QC2已接收','sqc_received',55,NULL,11),
	 ('绘图已转移','draft_transferred',60,NULL,11),
	 ('校对已转移','proofread_transferred',65,NULL,11),
	 ('QC1已转移','qc_transferred',70,NULL,11),
	 ('QC2已转移','sqc_transferred',75,NULL,11),
	 ('绘图已提交','draft_submitted',80,NULL,11),
	 ('校对已提交','proofread_submitted',85,NULL,11),
	 ('QC1已提交','qc_submitted',90,NULL,11);
INSERT INTO tms.sys_dict_data (dict_label,dict_value,dict_sort,remark,dict_type_id) VALUES
	 ('QC2已提交','sqc_submitted',95,NULL,11),
	 ('绘图取消提交','draft_cancelled',100,NULL,11),
	 ('校对取消提交','proofread_cancelled',105,NULL,11),
	 ('QC1取消提交','qc_cancelled',110,NULL,11),
	 ('QC2取消提交','sqc_cancelled',115,NULL,11),
	 ('校对已退回','proofread_returned',120,NULL,11),
	 ('QC1已退回','qc_returned',125,NULL,11),
	 ('QC2已退回','sqc_returned',130,NULL,11),
	 ('Planning','planning',3,NULL,5),
	 ('Execution','execution',4,NULL,5);
INSERT INTO tms.sys_dict_data (dict_label,dict_value,dict_sort,remark,dict_type_id) VALUES
	 ('Issue Fixing Online','issue_fixing_online',5,NULL,5),
	 ('Completed','completed',6,NULL,5);