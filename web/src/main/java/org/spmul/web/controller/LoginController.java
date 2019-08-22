package org.spmul.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.spmul.common.util.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author Lorenzo
 * @Date 2019/8/7 16:07
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/doLogin")
    public R login(@RequestBody Map<String , String> params){

        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken usernamePasswordToken =
                    new UsernamePasswordToken(
                            params.getOrDefault("username",""),
                            params.getOrDefault("password","")
                    );
            subject.login(usernamePasswordToken);

            return R.ok().put("msg","登录成功").put("session_id", subject.getSession().getId());

        }catch (UnknownAccountException e){
            return R.error(e.getMessage());
        }catch (Exception e ){
            return R.error("账号或者密码错误");
        }

    }

    @RequestMapping("/doLogout")
    public R findMyPlayRecord(){

        Subject subject = SecurityUtils.getSubject();

        if(subject.getPrincipals() != null ) {
            subject.logout();
        }

        return R.ok();
    }

}
