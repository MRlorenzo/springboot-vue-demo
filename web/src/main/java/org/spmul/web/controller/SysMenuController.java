package org.spmul.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.spmul.common.annotation.SysAllLog;
import org.spmul.common.annotation.SysLog;
import org.spmul.common.base.BaseController;
import org.spmul.common.base.BaseDao;
import org.spmul.common.util.R;
import org.spmul.entity.shiro.SysMenuEntity;
import org.spmul.service.shiro.SysMenuService;
import org.spmul.shiro.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController<SysMenuEntity> {

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    protected BaseDao<SysMenuEntity> getBaseService() {
        return sysMenuService;
    }

    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        return super.list(params);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public R select(){
        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenuEntity root = new SysMenuEntity();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setEnName("First level menu");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return R.ok().put("menuList", menuList);
    }

    /**
     * 角色授权菜单
     */
    @RequestMapping("/perms")
    @RequiresPermissions("sys:menu:perms")
    public R perms(){
        //查询列表数据
        List<SysMenuEntity> menuList;

        //只有超级管理员，才能查看所有管理员列表
        if(ShiroUtils.getUserId() == 1L){
            menuList = sysMenuService.queryList(new HashMap<>());
        }else{
            menuList = sysMenuService.queryUserList(ShiroUtils.getUserId());
        }

        return R.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @RequestMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public R info(@PathVariable("menuId") Long menuId){
        SysMenuEntity menu = sysMenuService.queryObject(menuId);
        return R.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @SysLog("保存菜单")
    @RequestMapping("/save")
    @RequiresPermissions("sys:menu:save")
    @SysAllLog(operation = "保存菜单", optTypeName = "保存", optTypeName2 = "菜单")
    public R save(@RequestBody SysMenuEntity menu){

        sysMenuService.save(menu);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改菜单")
    @RequestMapping("/update")
    @RequiresPermissions("sys:menu:update")
    @SysAllLog(operation = "修改菜单", optTypeName = "修改", optTypeName2 = "菜单")
    public R update(@RequestBody SysMenuEntity menu){

        sysMenuService.update(menu);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除菜单")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    @SysAllLog(operation = "删除菜单", optTypeName = "删除", optTypeName2 = "菜单")
    public R delete(@RequestBody Long[] menuIds){

        sysMenuService.deleteBatch(menuIds);

        return R.ok();
    }

    /**
     * 用户菜单列表
     */
    @RequestMapping("/user")
    public R user(){
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(ShiroUtils.getUserId());

        return R.ok().put("list", menuList);
    }

}
