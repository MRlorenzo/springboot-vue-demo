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
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;


	/**
	 * 部门id
	 */
	private Long departmentId;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 部门信息
	 *
	 */
	private DepartmentEntity department;

	/**
	* 角色ids
	* */
	private List<Long> roleIds;

	/**
	 * 角色信息
	 * */
	private List<SysRoleEntity> roles;
}
