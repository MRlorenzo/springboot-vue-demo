/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.spmul.entity.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 *
 * @author   知秋
 * @email fei6751803@163.com
 */
@Data
public class SysRoleEntity implements Serializable {
    private static final long serialVersionUID = 4358013151231997214L;

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 备注
     */
    private String description;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;


	/**
	 * 是否需要选择班次  0:不需要  1:需要
	 */
    private Integer isShift;


	/**
	 * 是否已删除 0否 1是
	 * @return
	 */
	private Integer isDel;

	/**
	 * 部门id
	 */
	private Long departmentId;


	/**
	 * 部门实体
	 */
	private DepartmentEntity department;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 路由清单
	* */
	private List<RouteEntity> routes;

}
