package org.spmul.web.controller.auth;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.spmul.common.util.R;
import org.spmul.entity.shiro.RouteEntity;
import org.spmul.entity.shiro.SysRoleEntity;
import org.spmul.service.shiro.RouteService;
import org.spmul.service.shiro.SysRoleService;
import org.spmul.shiro.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 获取所有父级路由（包含子项）
     * @return
     */
    @RequestMapping("/routes")
    public R routes(){
        Map<String , Object> params = new HashMap<>();
        params.put("pid" , 0);
        List<RouteEntity> routeEntities = routeService.queryList(params);
        return R.ok().put("list" , routeEntities);
    }

    /**
     * 获取所有角色（包含路由）
     * @return
     */
    @RequestMapping("/roles")
    public R roles(){
        return R.ok().put("list" , sysRoleService.queryList(null));
    }

    /**
     * 添加一个新的角色
     * */
    @RequestMapping("/role")
    // @RequiresPermissions("role:add")
    public R addRole(@RequestBody SysRoleEntity role){

        /*谁请求了这个方法，就是谁创建的角色*/
        role.setCreateUserId(ShiroUtils.getUserId());

        sysRoleService.save(role);
        return R.ok();
    }

    /**
     * 更新一个角色
     * @param role
     * @return
     */
    @RequestMapping("/role/update")
    // @RequiresPermissions("role:update")
    public R updateRole(@RequestBody SysRoleEntity role){
        sysRoleService.update(role);
        return R.ok();
    }

    /**
     * 删除一个角色
     * @param roleId
     * @return
     */
    @RequestMapping("/role/del/{id}")
    @RequiresPermissions("role:del")
    public R delRole(@PathVariable("id") Long roleId){
        routeService.delete(roleId);
        return R.ok();
    }
}
