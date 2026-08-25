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
 * 用户与角色关联表
 * </p>
 */
@Data
@EqualsAndHashCode (callSuper = false)
@TableName ("SYS_USER_ROLE")
@KeySequence ("SEQ_SYS_USER_ROLE_ID")
public class UserRole extends Model<UserRole>
{
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId ("ID")
    private Long id;

    /**
     * 用户 ID
     */
    @TableField ("USER_ID")
    private Long userId;

    /**
     * 角色 ID
     */
    @TableField ("ROLE_ID")
    private Long roleId;

    @Override
    public Serializable pkVal()
    {
        return this.id;
    }
}
