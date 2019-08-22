package org.spmul.dao.shiro;

import org.spmul.common.base.BaseDao;
import org.spmul.entity.shiro.SysUserEntity;

import java.util.List;

/**
 * @author 知秋
 * @email fei6751803@163.com
 */
public interface SysUserRoleDao extends BaseDao<SysUserEntity> {
    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);


    void batchSave(List<SysUserEntity> list);
}
