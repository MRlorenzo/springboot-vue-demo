package org.spmul.dao.shiro;

import org.spmul.common.base.BaseDao;
import org.spmul.entity.shiro.DepartmentEntity;

/**
 * 部门表
 *
 * @date 2018-09-11 10:44:08
 */
public interface DepartmentDao extends BaseDao<DepartmentEntity> {

    /**
     * 根据一个值获取部门id，可通过部门名称 或英文名称 或代码 查询
     * @param value
     * @return
     */
	Long queryDepartmentIdByString(String value);
}
