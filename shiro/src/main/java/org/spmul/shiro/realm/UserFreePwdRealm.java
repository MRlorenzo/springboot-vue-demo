package org.spmul.shiro.realm;

import org.apache.shiro.authc.*;
import org.spmul.entity.shiro.SysUserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserFreePwdRealm extends UserRealm{


    /*
     * 检查账号
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {


        String freePwd = new String((char[]) authenticationToken.getCredentials());

        //查询用户信息
        SysUserEntity user = sysUserService.queryByFreePwd(freePwd);

        //账号不存在
        if(user == null) {
            return null;
        }

        //账号锁定
        if(user.getStatus() == 0){
            throw new UnknownAccountException("AccountLocked");
        }

        return new SimpleAuthenticationInfo(user,  user.getFreePwd() , getName());
    }
}
