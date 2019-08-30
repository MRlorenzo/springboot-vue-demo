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

package org.spmul.service.shiro.impl;


import org.spmul.common.util.RRException;
import org.spmul.dao.shiro.SysRoleDao;
import org.spmul.dao.shiro.SysRolePermissionDao;
import org.spmul.entity.shiro.RouteEntity;
import org.spmul.entity.shiro.SysRoleEntity;
import org.spmul.entity.shiro.SysRolePermissionEntity;
import org.spmul.service.impl.BaseServiceImpl;
import org.spmul.service.shiro.SysRoleService;
import org.spmul.service.shiro.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.spmul.common.base.BaseDao;
/**
 * 角色
 *
 * @author 知秋
 * @email fei6751803@163.com
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleEntity> implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;

    @Override
    public BaseDao<SysRoleEntity> getBaseDao() {
        return sysRoleDao;
    }

    @Override
    public SysRoleEntity queryObject(Long roleId) {
        return sysRoleDao.queryObject(roleId);
    }

    private void assertNoEmpty(SysRoleEntity role){
        if( role.getRoutes() == null){
            throw new RRException("routes不能为空");
        }
    }

    @Override
    @Transactional
    public void save(SysRoleEntity role) {

        /* 检查routes 是否为空*/
        assertNoEmpty(role);

        role.setCreateTime(new Date());
        role.setIsDel(0);

        sysRoleDao.save(role);

        /* 保存新的角色与路由关联 */
        List<SysRolePermissionEntity> list = getRolePermissionListForRoutes(role.getId(), role.getRoutes());
        sysRolePermissionDao.saveBatch(list);
    }

    @Override
    @Transactional
    public int update(SysRoleEntity role) {

        /* 检查routes 是否为空*/
        assertNoEmpty(role);
        /* 清空旧的角色与路由关联 */
        sysRolePermissionDao.delete(role.getId());

        /* 保存新的角色与路由关联 */
        List<SysRolePermissionEntity> list = getRolePermissionListForRoutes(role.getId(), role.getRoutes());
        sysRolePermissionDao.saveBatch(list);

        int update = sysRoleDao.update(role);
        return update;
    }

    private List<SysRolePermissionEntity> getRolePermissionListForRoutes(Long roleId, List<RouteEntity> routes){
        List<SysRolePermissionEntity> rps = new ArrayList<>();
        routes.forEach(route ->{
            SysRolePermissionEntity rp = new SysRolePermissionEntity();
            rp.setRoleId(roleId);
            rp.setPermissionId(route.getId());
            rps.add(rp);
            if (route.getChildren() != null && route.getChildren().size()>0){
                List<SysRolePermissionEntity> list = getRolePermissionListForRoutes(roleId, route.getChildren());
                rps.addAll(list);
            }
        });
        return rps;
    }


    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return  sysRoleDao.queryRoleIdList(createUserId);
    }

    @Override
    public List<SysRoleEntity> queryBatchByUserId() {
        return sysRoleDao.queryBatchByUserId(0l);
    }

    @Override
    public List<SysRoleEntity> queryListByDepartmentId(Long departmentId) {
        return sysRoleDao.queryListByDepartmentId(departmentId);
    }


}
