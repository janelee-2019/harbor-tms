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
 * 公司信息表
 * </p>
 */
@Data
@EqualsAndHashCode (callSuper = false)
@TableName ("SYS_COMPANY")
@KeySequence ("SEQ_SYS_COMPANY_ID")
public class Company extends Model<Company>
{
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId ("ID")
    private Long id;

    /**
     * 公司代码
     */
    @TableField ("COMPANY_CODE")
    private String companyCode;

    /**
     * 公司名称
     */
    @TableField ("COMPANY_NAME")
    private String companyName;

    /**
     * 公司简称
     */
    @TableField ("COMPANY_SHORT_NAME")
    private String companyShortName;

    /**
     * 公司类型
     */
    @TableField ("COMPANY_TYPE")
    private String companyType;

    /**
     * 公司工商登记号
     */
    @TableField ("COMPANY_REG_NO")
    private String companyRegNo;

    /**
     * 公司所在国家
     */
    @TableField ("COMPANY_COUNTRY")
    private String companyCountry;

    /**
     * 公司所在省份
     */
    @TableField ("COMPANY_PROVINCE")
    private String companyProvince;

    /**
     * 公司所在城市
     */
    @TableField ("COMPANY_CITY")
    private String companyCity;

    /**
     * 公司详细地址
     */
    @TableField ("COMPANY_ADDRESS")
    private String companyAddress;

    /**
     * 备注
     */
    @TableField ("REMARK")
    private String remark;

    @Override
    public Serializable pkVal()
    {
        return this.id;
    }
}
