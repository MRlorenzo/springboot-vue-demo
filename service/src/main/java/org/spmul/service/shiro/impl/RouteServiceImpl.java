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
    public List<RouteEntity> queryList(Map<String, Object> map) {
        return routeDao.queryList(map);
    }
}
