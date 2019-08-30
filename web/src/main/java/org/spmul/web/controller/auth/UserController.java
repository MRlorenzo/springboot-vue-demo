package org.spmul.web.controller.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.spmul.common.util.R;
import org.spmul.entity.dto.UserInfo;
import org.spmul.service.shiro.RouteService;
import org.spmul.shiro.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private RouteService routeService;

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
