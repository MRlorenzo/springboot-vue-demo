package org.spmul.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SysAllLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 操作类型
     */
    private String optType;
    /**
     * 操作类型2
     */
    private String optType2;
    /**
     * 用户操作
     */
    private String operation;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求参数
     */
    private String params;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 异常名称
     */
    private String exceptionVal;

    private String computerName;

    private String tableName;

    private Long duration;
}
