package com.lee.tms.modules.system.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 部门信息表
 * </p>
 */
@Data
@EqualsAndHashCode (callSuper = false)
@TableName ("SYS_DEPT")
@KeySequence ("SEQ_SYS_DEPT_ID")
public class Dept extends Model<Dept>
{
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId ("ID")
    private Long id;

    /**
     * 部门编号
     */
    @TableField ("DEPT_CODE")
    private String deptCode;

    /**
     * 部门名称
     */
    @TableField ("DEPT_NAME")
    private String deptName;

    /**
     * 父级部门 ID
     */
    @TableField ("PARENT_ID")
    private Long parentId;

    /**
     * 部门级别
     */
    @TableField ("DEPT_LEVEL")
    private Long deptLevel;

    /**
     * 部门祖级列表
     */
    @TableField ("ANCESTORS")
    private String ancestors;

    /**
     * 备注
     */
    @TableField ("REMARK")
    private String remark;

    /**
     * 公司 ID
     */
    @TableField ("COMPANY_ID")
    private Long companyId;

    /**
     * 部门领导
     */
    @TableField ("DEPT_LEADER")
    private String deptLeader;
    @TableField (exist = false)
    private Company company;

    @Override
    public Serializable pkVal()
    {
        return this.id;
    }
}
