package com.lee.tms.modules.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 功能信息表
 * </p>
 */
@Data
@EqualsAndHashCode (callSuper = false)
@TableName ("SYS_FUNCTION")
@KeySequence ("SEQ_SYS_FUNCTION_ID")
public class Function extends Model<Function>
{
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId ("ID")
    private Long id;

    /**
     * 功能权限字符串
     */
    @TableField ("FUNCTION_KEY")
    private String functionKey;

    /**
     * 功能名称
     */
    @TableField ("FUNCTION_NAME")
    private String functionName;

    /**
     * 父级功能 ID
     */
    @TableField ("PARENT_ID")
    private Long parentId;

    /**
     * 功能排序
     */
    @TableField ("FUNCTION_SORT")
    private Long functionSort;

    /**
     * 功能类型
     */
    @TableField ("FUNCTION_TYPE")
    private String functionType;

    /**
     * 功能状态
     */
    @TableField ("FUNCTION_STATUS")
    private String functionStatus;

    /**
     * 备注
     */
    @TableField (value = "REMARK", updateStrategy = FieldStrategy.IGNORED)
    private String remark;

    /**
     * 功能祖级列表
     */
    @TableField ("ANCESTORS")
    private String ancestors;

    /**
     * 功能级别
     */
    @TableField ("FUNCTION_LEVEL")
    private Long functionLevel;

    @TableField (value = "VUE_PATH", updateStrategy = FieldStrategy.IGNORED)
    private String vuePath;

    @TableField (value = "VUE_COMPONENT", updateStrategy = FieldStrategy.IGNORED)
    private String vueComponent;

    @TableField (value = "VUE_REDIRECT", updateStrategy = FieldStrategy.IGNORED)
    private String vueRedirect;

    @TableField (value = "VUE_NAME", updateStrategy = FieldStrategy.IGNORED)
    private String vueName;

    @TableField (value = "VUE_TITLE", updateStrategy = FieldStrategy.IGNORED)
    private String vueTitle;

    @TableField (value = "VUE_ICON", updateStrategy = FieldStrategy.IGNORED)
    private String vueIcon;

    @TableField (value = "VUE_AFFIX", updateStrategy = FieldStrategy.IGNORED)
    private String vueAffix;

    @TableField (value = "VUE_BADGE", updateStrategy = FieldStrategy.IGNORED)
    private String vueBadge;
    @TableField (exist = false)
    private List<Role> roles;

    @Override
    public Serializable pkVal()
    {
        return this.id;
    }
}
