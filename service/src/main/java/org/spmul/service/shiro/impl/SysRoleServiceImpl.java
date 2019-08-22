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


import org.spmul.dao.shiro.SysRoleDao;
import org.spmul.entity.shiro.SysRoleEntity;
import org.spmul.service.impl.BaseServiceImpl;
import org.spmul.service.shiro.SysRoleMenuService;
import org.spmul.service.shiro.SysRoleService;
import org.spmul.service.shiro.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public BaseDao<SysRoleEntity> getBaseDao() {
        return sysRoleDao;
    }

    @Override
    public SysRoleEntity queryObject(Long roleId) {
        return sysRoleDao.queryObject(roleId);
    }

    @Override
    @Transactional
    public void save(SysRoleEntity role) {

        role.setCreateTime(new Date());
        role.setIsDel(0);
        sysRoleDao.save(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional
    public int update(SysRoleEntity role) {

        int update = sysRoleDao.update(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

        return update;
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] roleIds) {
        sysRoleDao.deleteBatch(roleIds);
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


    /**
     * 检查权限是否越权
     */
    private void checkPrems(SysRoleEntity role){
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if(role.getCreateUserId() == 1){
            return ;
        }

        //查询用户所拥有的菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());

        //判断是否越权
        if(!menuIdList.containsAll(role.getMenuIdList())){
            throw new RuntimeException("新增角色的权限，已超出你的权限范围");
        }
    }
}
