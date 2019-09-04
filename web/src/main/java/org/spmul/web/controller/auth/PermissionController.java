package org.spmul.web.controller.auth;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.spmul.common.util.PageUtils;
import org.spmul.common.util.Query;
import org.spmul.common.util.R;
import org.spmul.entity.shiro.RouteEntity;
import org.spmul.entity.shiro.SysRoleEntity;
import org.spmul.service.shiro.RouteService;
import org.spmul.service.shiro.SysRoleService;
import org.spmul.shiro.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        List<RouteEntity> routeEntities = routeService.queryAllInfoList(params);
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

    @RequestMapping("/menu/page")
    public R getMenuPageData(@RequestParam Map<String , Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<RouteEntity> list = routeService.queryList(query);
        int total = routeService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    @RequestMapping("/menu/root")
    public R getRootMenus(){
        Map<String , Object> params = new HashMap<>();
        params.put("pid" , 0);
        List<RouteEntity> menus = routeService.queryList(params);
        return R.ok().put("list" , menus);
    }

    @RequestMapping("/menu/all")
    public R getMenus(){
        List<RouteEntity> menus = routeService.queryList(null);
        return R.ok().put("list" , menus);
    }

    @RequestMapping("/menu/add")
    public R addMenu(@RequestBody RouteEntity routeEntity){
        routeService.save(routeEntity);
        return R.ok();
    }

    @RequestMapping("/menu/update")
    public R updateMenu(@RequestBody RouteEntity routeEntity){
        routeService.update(routeEntity);
        return R.ok();
    }

    @RequestMapping("/menu/del/{id}")
    @RequiresPermissions("menu:delete")
    public R deleteMenu(@PathVariable("id") Long id){
        routeService.delete(id);
        return R.ok();
    }

}
