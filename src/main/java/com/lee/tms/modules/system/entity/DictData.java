package com.lee.tms.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 字典数据表
 * </p>
 */
@Data
@EqualsAndHashCode (callSuper = false)
@TableName ("SYS_DICT_DATA")
public class DictData extends Model<DictData>
{
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId ("ID")
    private Long id;

    /**
     * 字典标签
     */
    @TableField ("DICT_LABEL")
    private String dictLabel;

    /**
     * 字典值
     */
    @TableField ("DICT_VALUE")
    private String dictValue;

    /**
     * 字典排序
     */
    @TableField ("DICT_SORT")
    private Long dictSort;

    /**
     * 备注
     */
    @TableField ("REMARK")
    private String remark;

    /**
     * 字典类型 ID
     */
    @TableField ("DICT_TYPE_ID")
    private String dictTypeId;

    @Override
    public Serializable pkVal()
    {
        return this.id;
    }
}
