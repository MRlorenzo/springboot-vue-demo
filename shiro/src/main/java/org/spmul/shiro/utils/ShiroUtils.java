
package org.spmul.shiro.utils;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.spmul.entity.dto.UserInfo;
import org.spmul.entity.shiro.SysRoleEntity;
import org.spmul.entity.shiro.SysUserEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShiroUtils {
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static SysUserEntity getUserEntity() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return getUserEntity().getUserId();
    }

    public static UserInfo getUserInfo(){
        UserInfo userInfo = new UserInfo();
        SysUserEntity userEntity = ShiroUtils.getUserEntity();

        List<SysRoleEntity> roles = userEntity.getRoles();

        userInfo.setName(userEntity.getSurname()+ " " + userEntity.getGivenNames());
        userInfo.setIntroduction(userEntity.getRemark());
        userInfo.setRoles(roles.stream().map(SysRoleEntity::getName).collect(Collectors.toList()));
        userInfo.setRoleIds(roles.stream().map(SysRoleEntity::getId).collect(Collectors.toList()));
        return userInfo;
    }

    /**
     * 刷新session中的用户id
     */
    public static void setRefreshUserId(){
        try {
            setSessionAttribute("userId", getUserId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setSessionAttribute(Object key, Object value) {
        Session session = getSession();
        if(session != null){
            session.setAttribute(key, value);
        }
    }


    public static Object getSessionAttribute(Object key) {
        Session session = getSession();
        if(session == null){
            return null;
        }
        return session.getAttribute(key);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static String getKaptcha(String key) {
        String kaptcha = getSessionAttribute(key).toString();
        getSession().removeAttribute(key);
        return kaptcha;
    }


}
