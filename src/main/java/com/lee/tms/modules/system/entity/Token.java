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
import java.util.List;

/**
 * <p>
 *
 * </p>
 */
@Data
@EqualsAndHashCode (callSuper = false)
@TableName ("SYS_TOKEN")
@KeySequence ("SEQ_SYS_TOKEN_ID")
public class Token extends Model<Token>
{
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId ("ID")
    private Long id;

    /**
     * 用户令牌
     */
    @TableField ("TOKEN")
    private String token;

    /**
     * 用户 ID
     */
    @TableField ("USER_ID")
    private Long userId;

    /**
     * 令牌过期时间
     */
    @TableField ("EXPIRE_DATE")
    private LocalDateTime expireDate;

    /**
     * 令牌创建时间
     */
    @TableField ("CREATE_DATE")
    private LocalDateTime createDate;
    @TableField (exist = false)
    private User user;
    @TableField (exist = false)
    private List<Role> roles;

    @Override
    public Serializable pkVal()
    {
        return this.id;
    }
}
