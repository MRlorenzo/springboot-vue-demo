package org.spmul.web.controller.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.spmul.common.base.BaseController;
import org.spmul.common.base.BaseDao;
import org.spmul.common.util.PageUtils;
import org.spmul.common.util.Query;
import org.spmul.common.util.R;
import org.spmul.common.util.RRException;
import org.spmul.entity.dto.UserInfo;
import org.spmul.entity.shiro.SysUserEntity;
import org.spmul.service.shiro.RouteService;
import org.spmul.service.shiro.SysUserService;
import org.spmul.shiro.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/page")
    public R page(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<SysUserEntity> list = sysUserService.queryAllInfoList(query);
        int total = sysUserService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    @GetMapping("/all")
    public R page(){
        return R.ok().put("list" , sysUserService.queryList(null));
    }

    @PostMapping("/login")
    public R login(@RequestBody Map<String , String> params){

        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken usernamePasswordToken =
                    new UsernamePasswordToken(
                            params.getOrDefault("username",""),
                            params.getOrDefault("password","")
                    );
            subject.login(usernamePasswordToken);

            return R.ok().put("msg","登录成功").put("token", subject.getSession().getId());

        }catch (UnknownAccountException e){
            return R.error(e.getMessage());
        }catch (Exception e ){
            return R.error("账号或者密码错误");
        }

    }

    @RequestMapping("/add")
    public R add(@RequestBody SysUserEntity user){
        /*
         * 由于Shiro的包依赖于Service层，Sha256Hash来源于Shiro的依赖。
         * 为了不相互依赖，我将密码加密工作移到了Controller层
         */
        user = encryption(user);
        sysUserService.save(user);
        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody SysUserEntity user){
        user = encryption(user);
        sysUserService.update(user);
        return R.ok();
    }

    private SysUserEntity encryption(SysUserEntity user){
        if (StringUtils.isEmpty(user.getPassword())){
            user.setPassword(null);
        } else {
            String oldPwd = user.getPassword();
            user.setPassword(new Sha256Hash(oldPwd).toHex());
        }

        return user;
    }

    @RequestMapping("/del/{id}")
    @RequiresPermissions("user:delete")
    public R del(@PathVariable("id") Long userId){
        sysUserService.delete(userId);
        return R.ok();
    }

    /**
     * 不用我们去实现退出，只要去访问一个退出的url（该 url是可以不存在），
     * 由LogoutFilter拦截住，清除session
     * 当我们在shiroConfig中配置了logoutFilter时，该url不会经过此方法。
     * @return
     */
    @GetMapping("/logout")
    public R logout(){

        Subject subject = SecurityUtils.getSubject();

        if(subject.getPrincipals() != null ) {
            subject.logout();
        }

        return R.ok();
    }

    @GetMapping("/info")
    public R info (){
        UserInfo userInfo = ShiroUtils.getUserInfo();
        Long roleId = null;
        if(userInfo.getRoleIds() != null && userInfo.getRoleIds().size()>0){
            roleId = userInfo.getRoleIds().get(0);
        }

        userInfo.setRoutes(routeService.queryByRoleId(roleId));
        return R.ok().put("entity" , userInfo);
    }


}
