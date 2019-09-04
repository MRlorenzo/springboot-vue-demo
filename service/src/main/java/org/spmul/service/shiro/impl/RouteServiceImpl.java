package org.spmul.service.shiro.impl;

import org.spmul.common.base.BaseDao;
import org.spmul.dao.shiro.RouteDao;
import org.spmul.entity.shiro.RouteEntity;
import org.spmul.service.impl.BaseServiceImpl;
import org.spmul.service.shiro.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("routeService")
public class RouteServiceImpl extends BaseServiceImpl<RouteEntity> implements RouteService {

    @Autowired
    private RouteDao routeDao;

    @Override
    public BaseDao<RouteEntity> getBaseDao() {
        return routeDao;
    }

    @Override
    public List<RouteEntity> queryByRoleId(Long roleId) {
        return routeDao.queryByRoleId(roleId);
    }

    @Override
    public List<RouteEntity> queryRouteItemByUserId(Long userId) {
        return routeDao.queryRouteItemByUserId(userId);
    }

    @Override
    public List<String> queryPermissionsByUserId(Long userId) {
        return routeDao.queryPermissionsByUserId(userId);
    }

    @Override
    public List<RouteEntity> queryAllInfoList(Map<String, Object> params) {
        return routeDao.queryAllInfoList(params);
    }
}
