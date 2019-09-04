package org.spmul.service.shiro.impl;

import org.spmul.common.util.RRException;
import org.spmul.dao.shiro.*;
import org.spmul.entity.shiro.RouteEntity;
import org.spmul.entity.shiro.SysUserEntity;
import org.spmul.service.impl.BaseServiceImpl;
import org.spmul.service.shiro.RouteService;
import org.spmul.service.shiro.SysUserRoleService;
import org.spmul.service.shiro.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    private RouteService routeService;

    @Override
    public BaseDao<SysUserEntity> getBaseDao() {
        return sysUserDao;
    }

    @Override
    public List<SysUserEntity> queryAllInfoList(Map<String, Object> params) {
        return sysUserDao.queryAllInfoList(params);
    }

    @Override
    public Set<String> queryPermissionsByUserId(Long userId) {
        List<String> permsList;
        Set<String> permsSet = null;
        if(userId == 1){
            List<RouteEntity> routes = routeService.queryList(null);

            permsList = routes.stream()
                    .parallel()
                    .map(RouteEntity::getPerms)
                    .collect(Collectors.toList());
        }else{
            permsList = routeService.queryPermissionsByUserId(userId);
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
    @Transactional
    public void save(SysUserEntity user) {
        if (StringUtils.isEmpty(user.getPassword())){
            throw new RRException("密码不能为空");
        }

        userBeforeCheck(user);

        user.setStatus(1);
        user.setCreateTime(new Date());
        sysUserDao.save(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIds());
    }

    private void userBeforeCheck(SysUserEntity user){

        if( !StringUtils.isEmpty(user.getFreePwd())){
            SysUserEntity u = sysUserDao.queryObjectByFreePwd(user.getFreePwd());
            if( u != null && user.getUserId() != u.getUserId()){
                throw new RRException("系统已存在该免密密码.");
            }
        } else {
            user.setFreePwd(null);
        }

        if (user.getDepartmentId() == null){
            throw new RRException("请选择部门");
        }

        if (user.getRoleIds() == null || user.getRoleIds().size() == 0){
            throw new RRException("请选择角色");
        }
    }

    @Override
    @Transactional
    public int update(SysUserEntity user) {
        userBeforeCheck(user);
        int update = sysUserDao.update(user);
        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(),  user.getRoleIds());
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

}
