package org.spmul.entity.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysUserEntity implements Serializable{
    private static final long serialVersionUID = -167607904051963460L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private transient String password;

	/**
	 * 免密登录的密码
	 */
	private transient String freePwd;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

	//姓
	private String surname;

	//名
	private String givenNames;

	//身份证
	private String passport;

	//出生日期
	private Date birthday;

	//地址
	private String address;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

	/**
	 * 头像
	 */
	private String headPortrait;

    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

	//出生日期
	private Date hiredate;

    private String webSocketParams;

	/**
	 * 部门id
	 */
	private Long departmentId;


	/**
	 * 部门信息
	 *
	 */
	private DepartmentEntity departmentInfo;


	/**
	 * 部门名称  -- 用于导入
	 */
	private String departmentText;
	/**
	 * 角色名称  -- 用于导入
	 */
	private String roleText;


	private Long roleId;


	private String remark;

}
