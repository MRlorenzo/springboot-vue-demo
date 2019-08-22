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

import org.spmul.dao.shiro.*;
import org.spmul.entity.shiro.SysMenuEntity;
import org.spmul.entity.shiro.SysRoleEntity;
import org.spmul.entity.shiro.SysUserEntity;
import org.spmul.service.impl.BaseServiceImpl;
import org.spmul.service.shiro.SysRoleService;
import org.spmul.service.shiro.SysUserRoleService;
import org.spmul.service.shiro.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.spmul.common.base.BaseDao;
/**
 * @author 知秋
 * @email fei6751803@163.com
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUserEntity> implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private SysMenuDao sysMenuDao;


    @Override
    public BaseDao<SysUserEntity> getBaseDao() {
        return sysUserDao;
    }

    @Override
    public List<String> queryAllPerms(Long userId) {

        return sysRoleMenuDao.queryAllPerms(userId);
    }

    @Override
    public Set<String> queryPermissionsByUserId(Long userId) {
        List<String> permsList;
        Set<String> permsSet = null;
        if(userId == 1){
            List<SysMenuEntity> menuList = sysMenuDao.queryList(null);
            permsList = menuList.stream()
                    .parallel()
                    .map(SysMenuEntity::getPerms)
                    .collect(Collectors.toList());
        }else{
            permsList = queryAllPerms(userId);
        }

        permsSet = permissionsSetFor(permsList);

        return permsSet;
    }

    private Set<String> permissionsSetFor(List<String> permsList){
        Set<String> permisionsSet = new HashSet<>();

        permsList.forEach(s -> {
            if(!StringUtils.isEmpty(s)){
                Stream.of(s.split(","))
                        .collect(Collectors.toCollection(ArrayList::new))
                        .forEach( perms ->{
                            permisionsSet.add(perms);
                        } );

            }
        });

        return permisionsSet;
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysRoleMenuDao.queryAllMenuId(userId);
    }

    @Override
    public SysUserEntity queryByUserName(String username) {
        return sysUserDao.queryByUserName(username);
    }

    @Override
    public SysUserEntity queryByFreePwd(String pwd) {
        return sysUserDao.queryObjectByFreePwd(pwd);
    }

    @Override
    public SysUserEntity queryObject(Long userId) {
        return sysUserDao.queryObject(userId);
    }

    @Override
    public List<SysUserEntity> queryAllInfoList(Map<String, Object> map) {
        return sysUserDao.queryAllInfoList(map);
    }


    @Override
    @Transactional
    public void save(SysUserEntity user) {
        if(user.getFreePwd() != null){
            if(!checkFreePwd(user.getFreePwd())){
                throw new RuntimeException("系统已存在该免密密码.");
            }
        }
        user.setStatus(1);
        user.setCreateTime(new Date());
        //sha256加密
        user.setPassword(user.getPassword() );//new Sha256Hash(user.getPassword()).toHex());
        sysUserDao.save(user);

        //检查角色是否越权
//        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    private boolean checkFreePwd(String pwd){
        SysUserEntity user = sysUserDao.queryObjectByFreePwd(pwd);
        return user == null;
    }

    @Override
    @Transactional
    public int update(SysUserEntity user) {
        if(StringUtils.isEmpty(user.getPassword())){
            user.setPassword(null);
        }else{
            user.setPassword(user.getPassword()/*new Sha256Hash(user.getPassword()).toHex()*/);
        }
        int update = sysUserDao.update(user);

        //检查角色是否越权
//        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());

        return update;
    }

    @Override
    @Transactional
    public int updatePassword(Long userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return sysUserDao.updatePassword(map);
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUserEntity user){
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if(user.getCreateUserId() == 1){
            return ;
        }

        //查询用户创建的角色列表
        List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

        //判断是否越权
        if(!roleIdList.containsAll(user.getRoleIdList())){
            throw new RuntimeException("新增用户所选角色，不是本人创建");
        }
    }

    /**
     * 修改员工状态
     * @param map
     */
    @Override
    public void changeStatus(Map<String, Object> map) {
        sysUserDao.changeStatus(map);
    }

    @Override
    public boolean selectIsShiftByUserId() {
        boolean flag = false;

        List<SysRoleEntity> roleEntities  =sysRoleService.queryBatchByUserId();
        for(SysRoleEntity role:roleEntities){
            if(role.getIsShift() != null && role.getIsShift()==1){
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public int queryUserIsDealer(Long userId) {
        return sysUserDao.queryUserIsDealer(userId);
    }

    /*@Override
    public boolean currentUserIsCanForceUpdate() {
        *//*String value = redisUtil.getClazz(String.class, "config", "FORCE_UPDATE_ROLE_ID");
        if(value == null){
            value = sysConfigDao.queryByKey("FORCE_UPDATE_ROLE_ID");
            redisUtil.setClazz(String.class, "config", "FORCE_UPDATE_ROLE_ID", value, 5*60L);
        }*//*
        if(org.springframework.util.StringUtils.isEmpty(value)){
            return true;
        }
        String[] idsStr = value.split(",");
        Long[] idsLong = new Long[idsStr.length];
        for(int i=0;i<idsStr.length;i++){
            idsLong[i] = Long.parseLong(idsStr[i]);
        }
        List<Long> roleIds = sysUserRoleService.queryRoleIdList(ShiroUtils.getUserId());
        if(roleIds.isEmpty()){
            return false;
        }

        boolean flag = false;
        for(Long i:idsLong){
            if(roleIds.contains(i)){
                flag=true;
                break;
            }
        }
        return flag;
    }*/

    /*@Override
    public List<Map<String,Object>> queryUserLikeUsername(String username) {
        SysUserEntity userEntity = ShiroUtils.getUserEntity();
        Long userId = userEntity.getUserId();
        Long departmentId = userEntity.getDepartmentId();

        List<Long> longs = queryRoleIdListInCache(userId);
        if(checkHasAdminAuthority(longs)){
            return sysUserDao.queryUserLikeUsername(username, null, null);
        }else{
            return sysUserDao.queryUserLikeUsername(username, departmentId, longs.get(0));
        }
    }*/

    @Override
    public boolean checkHasAdminAuthority(Long userId) {
        List<Long> longs = queryRoleIdListInCache(userId);
        return checkHasAdminAuthority(longs);
    }

    private boolean checkHasAdminAuthority(List<Long> longs) {

        return longs.contains(1L) || longs.contains(2L) || longs.contains(28L) || longs.contains(37L);
    }

    private List<Long> queryRoleIdListInCache(Long userId){
        /*List<Long> rs = redisUtil.getClazz(List.class, "queryRoleIdListInCache", userId);
        if(rs == null){
            rs = sysRoleDao.queryRoleIdListByUserId(userId);
            redisUtil.setClazz(List.class, "queryRoleIdListInCache", userId, rs, 5L * 60);
        }*/
        List<Long> rs = new ArrayList<>();
        return rs;
    }

    @Override
    public Long queryUserMinRoleIdByUserId(Long userId) {
        List<Long> longs = queryRoleIdListInCache(userId);
        if(longs.isEmpty()){
            return 0L;
        }
        return longs.get(0);
    }

    @Override
    public void batchImport(List<SysUserEntity> list) {
        Date curr = new Date();


        for(SysUserEntity user:list){
            if(isAllFieldNull(user)){
                continue;
            }
            if(user.getCreateTime()==null){
                user.setCreateTime(curr);
            }
            String username = user.getUsername();
            String password = user.getPassword();
            if(username==null){
                throw new RuntimeException("Please Enter the username!");
            }
            if(password==null){
                throw new RuntimeException("Please Enter the password!");
            }

            //sha256加密
            user.setPassword(password/*new Sha256Hash(password).toHex()*/);

            //校验登录名是否重复
            if(sysUserDao.queryUserCountByUsername(username)>0){
                String messageA = "["+username+"]";
                String messageB = "username already exist!";
                throw new RuntimeException(messageA+messageB);
            }


            //校验部门
            String departmentText = user.getDepartmentText();
            //标识部门是否已填
            boolean deptFlag = false;
            if( !StringUtils.isEmpty(departmentText) && !"".equals(departmentText.trim())){
                Long departmentId = sysUserDao.queryDepartmentIdByString(departmentText);
                if(departmentId==null){
                    String messageA = "["+departmentText+"]";
                    String messageB = "Department doesn't exist!";
                    throw new RuntimeException(messageA+messageB);
                }
                user.setDepartmentId(departmentId);
                deptFlag = true;
            }

            //校验角色
            String roleText = user.getRoleText();


            if( !StringUtils.isEmpty(roleText) && !"".equals(roleText.trim())){
                if(!deptFlag){
                    throw new RuntimeException("Please Enter the department!");
                }

                SysRoleEntity role = sysRoleDao.queryByString(roleText) ;
                if(role == null){
                    String messageA = "["+roleText+"]";
                    String messageB = "Role doesn't exist!";
                    throw new RuntimeException(messageA+messageB);
                }


                //判断所填写的角色是否属于所填写的部门
                if(role.getDepartmentId()==null || !role.getDepartmentId().equals(user.getDepartmentId())){
                    String messageA = "The Role";
                    String messageB = "["+roleText+"]";
                    String messageC = "do not belong to the Department";
                    String messageD = "["+departmentText+"]";
                    throw new RuntimeException(messageA+messageB+messageC+messageD);
                }

                user.setRoleId(role.getRoleId());

            }

            user.setStatus(1);


        }


        if( !list.isEmpty()){
            sysUserDao.batchSave(list);
            sysUserRoleDao.batchSave(list);
        }


    }


    //判断该对象是否: 返回ture表示所有属性为null  返回false表示不是所有属性都是null
    private  boolean isAllFieldNull(Object obj){
        Class stuCla = (Class) obj.getClass();// 得到类对象
        Field[] fs = stuCla.getDeclaredFields();//得到属性集合
        boolean flag = true;
        for (Field f : fs) {//遍历属性
            f.setAccessible(true); // 设置属性是可以访问的(私有的也可以)
            Object val = null;// 得到此属性的值
            try {
                val = f.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(val!=null) {//只要有1个属性不为空,那么就不是所有的属性值都为空
                flag = false;
                break;
            }
        }
        return flag;
    }

}
