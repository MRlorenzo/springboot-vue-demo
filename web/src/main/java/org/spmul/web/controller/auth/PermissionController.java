package org.spmul.web.controller.auth;

import org.spmul.common.util.R;
import org.spmul.entity.shiro.RouteEntity;
import org.spmul.service.shiro.RouteService;
import org.spmul.service.shiro.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/routes")
    public R routes(){
        Map<String , Object> params = new HashMap<>();
        params.put("pid" , 0);
        List<RouteEntity> routeEntities = routeService.queryList(params);
        return R.ok().put("list" , routeEntities);
    }

    @RequestMapping("/roles")
    public R roles(){
        return R.ok().put("list" , sysRoleService.queryList(null));
    }
}
