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


import org.spmul.dao.shiro.SysMenuDao;
import org.spmul.entity.shiro.SysMenuEntity;
import org.spmul.service.impl.BaseServiceImpl;
import org.spmul.service.shiro.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.spmul.common.base.BaseDao;
/**
 * @author 知秋
 * @email fei6751803@163.com
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuEntity> implements SysMenuService {


    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    public BaseDao<SysMenuEntity> getBaseDao() {
        return sysMenuDao;
    }


    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return sysMenuDao.queryNotButtonList();
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if(userId == 1){
            List<SysMenuEntity> list = null;
           /* if(USER_CACHE){
                list = redisUtil.getClazz(List.class, "sysMenuList", "admin");
            }
            if(list == null){*/
                //暂时值支持2级目录
//                list = sysMenuDao.queryListAllMenu();
                list = sysMenuDao.queryListAllMenu();
                list = fatherSonCombination(list);
           /*     redisUtil.setClazz(List.class, "sysMenuList", "admin", list, 5L * 30);
            }*/
            //获取所有菜单
            return list;
        }

        //暂时值支持2级目录
        //用户菜单列表
        List<SysMenuEntity> list = null;
       /* if(USER_CACHE){
            list = redisUtil.getClazz(List.class, "queryUserListAllMenu", userId);
        }
        if(list == null){*/
            //暂时值支持2级目录
            list = sysMenuDao.queryUserListAllMenu(userId);
            list = fatherSonCombination(list);
           /* redisUtil.setClazz(List.class, "queryUserListAllMenu", userId, list, 3L * 30);
        }*/
        return list;
    }

    /**
     * 父子层级组合
     * @param list
     * @return
     */
    private List<SysMenuEntity> fatherSonCombination(List<SysMenuEntity> list){
        List<SysMenuEntity> rs = new ArrayList<>();
        Map<Long,SysMenuEntity> map = new HashMap<>();
        for (SysMenuEntity menu:list){

            //当前层级处理
            Long menuId = menu.getMenuId();
            SysMenuEntity old = map.put(menuId, menu);
            if(old != null){
                menu.setList(old.getList());
            }

            //父层级处理
            Long parentId = menu.getParentId();
            if(parentId != null && parentId > 0){
                SysMenuEntity parent = map.get(parentId);
                if(parent == null){
                    parent = new SysMenuEntity();
                    map.put(parentId, parent);
                }
                List<SysMenuEntity> son = parent.getList();
                if(son == null){
                    son = new ArrayList<>();
                    parent.setList(son);
                }
                son.add(menu);
            }else{
                menu.setList(new ArrayList<>());
                rs.add(menu);
            }
        }

        return rs;
    }

    @Override
    public SysMenuEntity queryObject(Long menuId) {
        return sysMenuDao.queryObject(menuId);
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] menuIds) {
        sysMenuDao.deleteBatch(menuIds);
    }

    @Override
    public List<SysMenuEntity> queryUserList(Long userId) {
        return sysMenuDao.queryUserList(userId);
    }

}
