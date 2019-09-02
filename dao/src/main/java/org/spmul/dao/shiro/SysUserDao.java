package org.spmul.dao.shiro;

import org.apache.ibatis.annotations.Param;
import org.spmul.common.base.BaseDao;
import org.spmul.entity.shiro.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author 知秋
 * @email fei6751803@163.com
 */
public interface SysUserDao extends BaseDao<SysUserEntity> {

    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByUserName(String username);

    /**
     * 根据免密密码，查询系统用户
     * @param freePwd
     * @return
     */
    SysUserEntity queryObjectByFreePwd(String freePwd);

    /**
     * 修改状态
     * @param map
     */
    void changeStatus(Map<String, Object> map);

    /**
     * 修改密码
     */
    int updatePassword(Map<String, Object> map);

}
