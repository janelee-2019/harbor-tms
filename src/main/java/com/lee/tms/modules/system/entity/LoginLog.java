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
 * 用户登录日志表
 * </p>
 */
@Data
@EqualsAndHashCode (callSuper = false)
@TableName ("SYS_LOGIN_LOG")
@KeySequence ("SEQ_SYS_LOGIN_LOG_ID")
public class LoginLog extends Model<LoginLog>
{
    private static final long serialVersionUID = 1L;

    @TableId ("ID")
    private Long id;

    /**
     * 用户登录名
     */
    @TableField ("LOGIN_NAME")
    private String loginName;

    /**
     * 客户端 IP 地址
     */
    @TableField ("CLIENT_IP")
    private String clientIp;

    /**
     * 客户端浏览器
     */
    @TableField ("CLIENT_BROWSER")
    private String clientBrowser;

    /**
     * 客户端操作系统
     */
    @TableField ("CLIENT_OS")
    private String clientOs;

    /**
     * 登录状态
     */
    @TableField ("LOGIN_STATUS")
    private String loginStatus;

    /**
     * 服务端返回信息
     */
    @TableField ("MESSAGE")
    private String message;

    /**
     * 登录日期
     */
    @TableField ("LOGIN_DATE")
    private LocalDateTime loginDate;

    /**
     * 浏览器 UA 信息
     */
    @TableField ("USER_AGENT")
    private String userAgent;

    /**
     * 用户 ID
     */
    @TableField ("USER_ID")
    private Long userId;

    @Override
    public Serializable pkVal()
    {
        return this.id;
    }
}
