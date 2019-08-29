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

package org.spmul.service.shiro;

import org.spmul.entity.shiro.SysRoleEntity;
import org.spmul.common.base.BaseDao;
import java.util.List;

/**
 * 角色
 *
 * @author 知秋
 * @email fei6751803@163.com
 */
public interface SysRoleService extends BaseDao<SysRoleEntity> {

    SysRoleEntity queryObject(Long roleId);

    void deleteBatch(Long[] roleIds);

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);



    List<SysRoleEntity> queryBatchByUserId();


    /**
     * 根据部门id查询对应角色
     * @param departmentId
     * @return
     */
    List<SysRoleEntity> queryListByDepartmentId(Long departmentId);

}
