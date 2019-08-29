
package org.spmul.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.spmul.entity.shiro.SysUserEntity;
import org.spmul.service.shiro.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author 知秋
 * @email fei6751803@163.com
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    protected SysUserService sysUserService;


    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUserEntity user = (SysUserEntity)principalCollection.getPrimaryPrincipal();
        Long userId = user.getUserId();

        Set<String> permsSet = sysUserService.queryPermissionsByUserId(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.setStringPermissions(permsSet);

        return info;
    }

    /*
    * 检查账号
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {


        String username = (String) authenticationToken.getPrincipal();

        //查询用户信息
        SysUserEntity user = sysUserService.queryByUserName(username);

        //账号不存在
        if(user == null) {
            return null;
        }

        //账号锁定
        if(user.getStatus() == 0){
            throw new UnknownAccountException("AccountLocked");
        }

        return new SimpleAuthenticationInfo(user,  user.getPassword() , getName());
    }

}
