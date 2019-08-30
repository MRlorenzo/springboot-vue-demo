package org.spmul.dao.shiro;

import org.spmul.common.base.BaseDao;
import org.spmul.entity.shiro.SysRoleEntity;

import java.util.List;

/**
 * 角色管理
 *
 * @author   知秋
 * @email fei6751803@163.com
 */
public interface SysRoleDao extends BaseDao<SysRoleEntity> {
    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);

    /**
     * 根据用户id查找用户包含的角色id
     * @param userId
     * @return
     */
    List<Long> queryRoleIdListByUserId(Long userId);

    List<SysRoleEntity> queryBatchByUserId(Long userId);

    /**
     * 根据部门id查询对应角色
     * @param departmentId
     * @return
     */
    List<SysRoleEntity> queryListByDepartmentId(Long departmentId);


    /**
     * 通过某些字段查询一个数据
     * @param value
     * @return
     */
    SysRoleEntity queryByString(String value);

}
