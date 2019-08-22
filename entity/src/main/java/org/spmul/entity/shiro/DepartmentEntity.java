package org.spmul.entity.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * 部门表
 * 
 * @date 2018-09-11 10:44:08
 */
@Data
public class DepartmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 部门id
	 */
	private Long departmentId;
	/**
	 * 部门中文名称
	 */
	private String name;
	/**
	 * 部门英文名称
	 */
	private String enName;
	/**
	 * 部门编号
	 */
	private String departmentCode;

	private Integer sort;

}
