package org.spmul.dao.shiro;

import org.spmul.common.base.BaseDao;
import org.spmul.entity.shiro.RouteEntity;

import java.util.List;

public interface RouteDao extends BaseDao<RouteEntity> {

    List<RouteEntity> queryByRoleId(Long roleId);

}
