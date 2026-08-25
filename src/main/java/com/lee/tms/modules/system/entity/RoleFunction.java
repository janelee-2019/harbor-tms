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
 * 角色与功能关联表
 * </p>
 */
@Data
@EqualsAndHashCode (callSuper = false)
@TableName ("SYS_ROLE_FUNCTION")
@KeySequence ("SEQ_SYS_ROLE_FUNCTION_ID")
public class RoleFunction extends Model<RoleFunction>
{
    private static final long serialVersionUID = 1L;

    @TableId ("ID")
    private Long id;

    @TableField ("ROLE_ID")
    private Long roleId;

    @TableField ("FUNCTION_ID")
    private Long functionId;

    @Override
    public Serializable pkVal()
    {
        return null;
    }
}
