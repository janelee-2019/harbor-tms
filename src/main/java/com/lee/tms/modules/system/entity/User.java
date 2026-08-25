package com.lee.tms.modules.system.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户信息表
 * </p>
 */
@Data
@EqualsAndHashCode (callSuper = false)
@TableName ("SYS_USER")
@KeySequence ("SEQ_SYS_USER_ID")
public class User extends Model<User>
{
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId ("ID")
    private Long id;

    /**
     * 用户登录名
     */
    @TableField ("LOGIN_NAME")
    private String loginName;

    /**
     * 用户登录密码
     */
    @TableField ("LOGIN_PASSWORD")
    private String loginPassword;

    /**
     * 密码加密盐
     */
    @TableField ("SALT")
    private String salt;

    /**
     * 用户最后登录 IP
     */
    @TableField ("LAST_LOGIN_IP")
    private String lastLoginIp;

    /**
     * 用户最后登录日期
     */
    @TableField ("LAST_LOGIN_DATE")
    private LocalDateTime lastLoginDate;

    /**
     * 用户信息创建时间
     */
    @TableField ("CREATE_DATE")
    private LocalDateTime createDate;

    /**
     * 用户信息最后更新时间
     */
    @TableField ("UPDATE_DATE")
    private LocalDateTime updateDate;

    /**
     * 用户密码最后更新时间
     */
    @TableField ("PASSWORD_UPDATE_DATE")
    private LocalDateTime passwordUpdateDate;

    /**
     * 用户信息创建者
     */
    @TableField ("CREATOR_ID")
    private Long creatorId;

    /**
     * 用户信息最后更新者
     */
    @TableField ("UPDATER_ID")
    private Long updaterId;

    /**
     * 备注
     */
    @TableField ("REMARK")
    private String remark;

    /**
     * 帐号状态
     */
    @TableField ("ACCOUNT_STATUS")
    private String accountStatus;

    /**
     * 员工号
     */
    @TableField ("EMPLOYEE_CODE")
    private String employeeCode;

    /**
     * 员工姓名
     */
    @TableField ("EMPLOYEE_NAME")
    private String employeeName;

    /**
     * 员工电话
     */
    @TableField ("EMPLOYEE_TEL")
    private String employeeTel;

    /**
     * 员工 Email
     */
    @TableField ("EMPLOYEE_EMAIL")
    private String employeeEmail;

    /**
     * 员工地址
     */
    @TableField ("EMPLOYEE_ADDRESS")
    private String employeeAddress;

    /**
     * 员工照片
     */
    @TableField ("EMPLOYEE_PICTURE")
    private String employeePicture;

    /**
     * 员工性别
     */
    @TableField ("EMPLOYEE_SEX")
    private String employeeSex;

    /**
     * 部门 ID
     */
    @TableField ("DEPT_ID")
    private Long deptId;

    /**
     * 删除标记
     */
    @TableField ("DEL_FLAG")
    private String delFlag;
    @TableField (exist = false)
    private Dept dept;
    @TableField (exist = false)
    private Company company;
    @TableField (exist = false)
    private List<Role> roles;

    @Override
    public Serializable pkVal()
    {
        return this.id;
    }
}
