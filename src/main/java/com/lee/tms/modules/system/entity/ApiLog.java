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
 * 接口操作日志表
 * </p>
 */
@Data
@EqualsAndHashCode (callSuper = false)
@TableName ("SYS_API_LOG")
@KeySequence ("SEQ_SYS_API_LOG_ID")
public class ApiLog extends Model<ApiLog>
{
    private static final long serialVersionUID = 1L;

    @TableId ("ID")
    private Long id;

    /**
     * 用户 ID
     */
    @TableField ("USER_ID")
    private Long userId;

    /**
     * HTTP Method
     */
    @TableField ("HTTP_METHOD")
    private String httpMethod;

    /**
     * 请求 URL
     */
    @TableField ("REQUEST_URL")
    private String requestUrl;

    /**
     * 客户端 IP 地址
     */
    @TableField ("CLIENT_IP")
    private String clientIp;

    /**
     * 客户端操作系统
     */
    @TableField ("CLIENT_OS")
    private String clientOs;

    /**
     * JSON 响应
     */
    @TableField ("JSON_RESULT")
    private String jsonResult;

    /**
     * 请求参数
     */
    @TableField ("REQUEST_PARAM")
    private String requestParam;

    /**
     * 状态
     */
    @TableField ("STATUS")
    private String status;

    /**
     * 返回信息
     */
    @TableField ("MESSAGE")
    private String message;

    /**
     * 创建时间
     */
    @TableField ("CREATE_DATE")
    private LocalDateTime createDate;

    /**
     * 浏览器 UA 信息
     */
    @TableField ("USER_AGENT")
    private String userAgent;

    /**
     * 客户端浏览器
     */
    @TableField ("CLIENT_BROWSER")
    private String clientBrowser;

    /**
     * 日志标签
     */
    @TableField ("TAG")
    private String tag;

    /**
     * 日志标签
     */
    @TableField ("MODULE")
    private String module;

    /**
     * 执行时长（毫秒）
     */
    @TableField ("DURATION")
    private Long duration;

    @Override
    public Serializable pkVal()
    {
        return this.id;
    }
}
