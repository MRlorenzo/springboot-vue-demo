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
    /*@Select("select * from sys_user where username = #{username}")*/
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


    List<SysUserEntity> queryAllInfoList(Map<String, Object> map);

    /**
     * 查询用户是否属于发牌员角色
     * @param userId
     * @return
     */
    int queryUserIsDealer(Long userId);

    /**
     * 根据登陆帐号查询员工的某些信息
     * @param username 模糊登录用户
     * @param departmentId 部门id
     * @param roleId 角色id
     * @return
     */
    List<Map<String,Object>> queryUserLikeUsername(@Param("username") String username, @Param("departmentId") Long departmentId,
                                                   @Param("roleId") Long roleId);


    /**
     * 根据userId查询员工的某些信息
     * @param userId
     * @return
     */
    Map<String,Object> queryUserRoleInfoByUserId(Long userId);


    /**
     * 判断是否已存在该账号
     * @param username
     * @return
     */
    Integer queryUserCountByUsername(String username);


    /**
     * 通过某些字段查询部门id
     * @param value
     * @return
     */
    Long queryDepartmentIdByString(String value);



    void batchSave(List<SysUserEntity> list);

    /**
     * 根据登录账号查询用户
     * @param username 登录账号
     * @return Map
     */
    Map<String,Object> queryUserByUsername(String username);
}
