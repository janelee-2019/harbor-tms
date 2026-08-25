package com.lee.tms.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 字典类型表
 * </p>
 */
@Data
@EqualsAndHashCode (callSuper = false)
@TableName ("SYS_DICT_TYPE")
public class DictType extends Model<DictType>
{
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId ("ID")
    private Long id;

    /**
     * 字典 Key
     */
    @TableField ("DICT_KEY")
    private String dictKey;

    /**
     * 字典名称
     */
    @TableField ("DICT_NAME")
    private String dictName;

    /**
     * 备注
     */
    @TableField ("REMARK")
    private String remark;
    @TableField (exist = false)
    private List<DictData> dictDataList;

    @Override
    public Serializable pkVal()
    {
        return this.id;
    }
}
