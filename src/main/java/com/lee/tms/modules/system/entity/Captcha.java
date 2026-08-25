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

/**
 * <p>
 * 验证码信息表
 * </p>
 */
@Data
@EqualsAndHashCode (callSuper = false)
@TableName ("SYS_CAPTCHA")
@KeySequence ("SEQ_SYS_CAPTCHA_ID")
public class Captcha extends Model<Captcha>
{
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId ("ID")
    private Long id;

    /**
     * 验证码标识
     */
    @TableField ("UUID")
    private String uuid;

    /**
     * 验证码
     */
    @TableField ("CODE")
    private String code;

    /**
     * 验证码过期时间
     */
    @TableField ("EXPIRE_DATE")
    private LocalDateTime expireDate;

    /**
     * 验证码创建时间
     */
    @TableField ("CREATE_DATE")
    private LocalDateTime createDate;

    @Override
    public Serializable pkVal()
    {
        return this.id;
    }
}
