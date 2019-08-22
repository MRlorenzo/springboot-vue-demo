package org.spmul.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 赌台区
 * 
 * @date 2018-06-06 15:17:56
 */
@Data
public class AreaEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//赌台区主键

	private Long areaId;
	//区名称

	private String name;
	//英文名称
	private String enName;
	//区编号
	private String areaCode;
	//备注
	private String remarks;
	//创建时间
	private Date createTime;


}
