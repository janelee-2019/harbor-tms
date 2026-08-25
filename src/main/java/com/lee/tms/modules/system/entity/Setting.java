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
 * 参数设置表
 * </p>
 */
@Data
@EqualsAndHashCode (callSuper = false)
@TableName ("SYS_SETTING")
@KeySequence ("SEQ_SYS_SETTING_ID")
public class Setting extends Model<Setting>
{
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId ("ID")
    private Long id;

    @TableField ("SETTING_KEY")
    private String key;

    @TableField ("SETTING_VALUE")
    private String value;

    @TableField ("SETTING_NAME")
    private String name;

    @TableField ("SETTING_TYPE")
    private String type;

    public Long getLong()
    {
        return Long.valueOf(value);
    }

    @Override
    public Serializable pkVal()
    {
        return this.id;
    }
}
