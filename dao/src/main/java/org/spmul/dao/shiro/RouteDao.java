package org.spmul.dao.shiro;

import org.spmul.common.base.BaseDao;
import org.spmul.entity.shiro.RouteEntity;

import java.util.List;
import java.util.Map;

public interface RouteDao extends BaseDao<RouteEntity> {

    /**
     * 根据角色id查出所有符合条件的路由(只查询父级但是会连带查询children)
     * */
    List<RouteEntity> queryByRoleId(Long roleId);

    /**
     * 根据用户id查出路由列表(查询所有符合条件的但是不会查询children)
     * */
    List<RouteEntity> queryRouteItemByUserId(Long userId);

    /**
     * 根据用户id查出所有的权限字符串
     * */
    List<String> queryPermissionsByUserId(Long userId);

    /**
    *  根据条件查询出routeEntity以及它所关联的实体
    * */
    List<RouteEntity> queryAllInfoList(Map<String , Object> params);
}
