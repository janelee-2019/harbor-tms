package com.lee.tms.modules.base;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * BaseEntity
 *
 * @author lijie
 * @since 2025-12-24
 */
@Data
@EqualsAndHashCode (callSuper = false)
public class BaseEntity implements Serializable
{
    /**
     * 主键
     */
    @TableId (type = IdType.AUTO)
    @JsonSerialize (using = ToStringSerializer.class) // 将 Long 转为 String 传给前端
    private Long id;

    /**
     * 创建时间
     */
    @TableField (fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField (fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 更新时间
     */
    @TableField (fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    @TableField (fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @JsonIgnore // 阻止序列化返回给前端，也阻止前端通过 JSON 篡改
    private Boolean isDeleted = false;
}
