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
import org.spmul.entity.dto.UserInfo;
import org.spmul.entity.shiro.SysUserEntity;
import org.spmul.common.base.BaseDao;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 知秋
 * @email fei6751803@163.com
 */
public interface SysUserService extends BaseDao<SysUserEntity> {


    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryAllPerms(Long userId);

    Set<String> queryPermissionsByUserId(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByUserName(String username);

    /**
     * 根据免密密码，查询系统用户.
     * @param pwd
     * @return
     */
    SysUserEntity queryByFreePwd(String pwd);

    /**
     * 根据用户ID，查询用户
     * @param userId
     * @return
     */
    SysUserEntity queryObject(Long userId);


    /**
     * 查询用户列表
     */
    List<SysUserEntity> queryAllInfoList(Map<String, Object> map);

    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    int updatePassword(Long userId, String password, String newPassword);

    void changeStatus(Map<String, Object> map);


    /**
     * 根据用户Id,查询角色是否需要选择班次
     */
    boolean selectIsShiftByUserId();


    /**
     * 查询用户是否属于发牌员角色
     * @param userId
     * @return
     */
    int queryUserIsDealer(Long userId);

    /**
     * 查询当前用户是否可以强制围数
     * @return
     */
    /*boolean currentUserIsCanForceUpdate();*/


    /**
     * 根据登陆帐号查询员工的某些信息, 不是管理员，总经理。不能看自己同级的数据
     * @param username
     * @return
     */
    /*List<Map<String,Object>> queryUserLikeUsername(String username);*/

    /**
     * 检查是否有admin权限
     * @param userId
     * @return
     */
    boolean checkHasAdminAuthority(Long userId);

    /**
     * 查找用户最小排序的角色权限
     * @param userId
     * @return
     */
    Long queryUserMinRoleIdByUserId(Long userId);

    /**
     * 批量导入
     * @param list
     */
    void batchImport(List<SysUserEntity> list);
}
