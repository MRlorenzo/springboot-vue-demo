package org.spmul.common.entity;



import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SysExceptionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 异常主键
     */
    private Long exceptionId;
    /**
     * 用户名
     */
    private String username;
    /**
     * IP地址
     */
    private String ip;
    /**
     *
     */
    private String computerName;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求参数
     */
    private String params;
    /**
     *
     */
    private String exceptionVal;
}
