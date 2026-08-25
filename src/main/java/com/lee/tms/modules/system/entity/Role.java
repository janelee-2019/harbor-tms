package com.lee.tms.modules.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色信息表
 * </p>
 */
@Data
@EqualsAndHashCode (callSuper = false)
@TableName ("SYS_ROLE")
@KeySequence ("SEQ_SYS_ROLE_ID")
public class Role extends Model<Role>
{
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId (value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 角色权限字符串
     */
    @TableField ("ROLE_KEY")
    private String roleKey;

    /**
     * 角色名称
     */
    @TableField ("ROLE_NAME")
    private String roleName;

    /**
     * 角色等级
     */
    @TableField ("ROLE_LEVEL")
    private Long roleLevel;

    /**
     * 角色信息创建时间
     */
    @TableField ("CREATE_DATE")
    private LocalDateTime createDate;

    /**
     * 角色信息最后更新时间
     */
    @TableField ("UPDATE_DATE")
    private LocalDateTime updateDate;

    /**
     * 角色信息创建者
     */
    @TableField ("CREATOR_ID")
    private Long creatorId;

    /**
     * 角色信息最后更新者
     */
    @TableField ("UPDATER_ID")
    private Long updaterId;

    /**
     * 备注
     */
    @TableField ("REMARK")
    private String remark;

    @Override
    public Serializable pkVal()
    {
        return this.id;
    }
}
