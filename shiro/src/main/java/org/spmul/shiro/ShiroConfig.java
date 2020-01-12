package org.spmul.shiro;

import org.apache.shiro.authc.AbstractAuthenticator;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.spmul.shiro.filter.MyFormAuthenticationFilter;
import org.spmul.shiro.realm.UserFreePwdRealm;
import org.spmul.shiro.realm.UserRealm;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.servlet.Filter;
import java.util.*;

@Configuration
@PropertySource("classpath:/application.yml")
public class ShiroConfig  {


    /**
     * 环境变量，通过读取application.properties配置文件获取参数
     */
    @Autowired
    Environment env;

    /**
     * @Author lorenzo
     * @Description 配置shiro过滤规则、自定义过滤器等。
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     **/
    @Bean
    public ShiroFilterFactoryBean shiroFilter(){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //必须设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        //设置自定义filter
        Map<String,Filter> filterMap = new LinkedHashMap<>();
        //重写authc拦截器，让它不要重定向到 xxx.html;
        //可以使shiroFilterFactoryBean.setLoginUrl功能失效。内部返回Json格式的字符串到客户端。
        filterMap.put("authc", new MyFormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //需要登录的接口，如果访问某个接口，需要登录却没登录，则调用此接口(如果不是前后端分离，则跳转页面)
        shiroFilterFactoryBean.setLoginUrl("/");

        //登录成功，跳转url，如果前后端分离，则没这个调用
        //shiroFilterFactoryBean.setSuccessUrl("/home.html");

        //没有权限，未授权就会调用此方法， 先验证登录-》再验证是否有权限
        //shiroFilterFactoryBean.setUnauthorizedUrl("/pub/not_permit");



        //拦截器路径，坑一，部分路径无法进行拦截，时有时无；因为hashmap是无序的，应该改为LinkedHashMap
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //退出过滤器(内置)
        filterChainDefinitionMap.put("/user/logout","logout");

        //匿名可以访问，也是就游客模式
        filterChainDefinitionMap.put("/index.html/**","anon");
        filterChainDefinitionMap.put("/user/login","anon");
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/test/**","anon");
        filterChainDefinitionMap.put("/v2/**","anon");
        filterChainDefinitionMap.put("/swagger-ui.html","anon");
        //坑二: 过滤链是顺序执行，从上而下，一般讲/** 放到最下面

        //authc : url定义必须通过认证才可以访问
        //anon  : url可以匿名访问
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * @Author lorenzo
     * @Description 配置安全管理器，设置缓存、session管理等。
     * @Date 16:49 2019/8/7
     * @Param []
     * @return org.apache.shiro.web.mgt.WebSecurityManager
     **/
    @Bean
    public WebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //如果不是前后端分离，则不必设置下面的sessionManager
        securityManager.setSessionManager(sessionManager());

        //使用自定义的cacheManager
        securityManager.setCacheManager(cacheManager());

        //设置校验策略
        securityManager.setAuthenticator(modularRealmAuthenticator());

        //设置realm（推荐放到最后，不然某些情况会不生效）
        //securityManager.setRealm(userRealm());
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm());
        realms.add(userFreePwdRealm());
        securityManager.setRealms(realms);

        return securityManager;
    }


    /**
     * 检验策略， 默认为 AtLeastOneSuccessfulStrategy
     * FirstSuccessfulStrategy：只要有一个Realm 验证成功即可，只返回第一个Realm 身份验证成功的认证信息，其他的忽略；
     * AtLeastOneSuccessfulStrategy：只要有一个Realm验证成功即可，和FirstSuccessfulStrategy不同，将返回所有Realm身份验证成功的认证信息；
     * AllSuccessfulStrategy：所有Realm验证成功才算成功，且返回所有Realm身份验证成功的认证信息，如果有一个失败就失败了。
     *
     * @return
     */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return authenticator;
    }


    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     * 定义aop切面，用于代理如@RequiresPermissions注解的控制器，进行权限控制。
     * （这段话起前面是AuthorizationAttributeSourceAdvisor的xml配置）
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * @Author lorenzo
     * @Description 自定义realm
     * @Date 16:50 2019/8/7
     * @Param []
     * @return org.apache.shiro.realm.Realm
     **/
    @Bean
    public Realm userRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    @Bean
    public Realm userFreePwdRealm(){
        UserRealm userFreePwdRealm = new UserFreePwdRealm();

        return userFreePwdRealm;
    }


    /**
     * @Author lorenzo
     * @Description 密码加解密规则
     * @Date 16:50 2019/8/7
     * @Param []
     * @return org.apache.shiro.authc.credential.HashedCredentialsMatcher
     **/
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();

        //设置散列算法：这里使用的MD5算法
        credentialsMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);

        //散列次数，好比散列2次，相当于md5(md5(xxxx))
        credentialsMatcher.setHashIterations(1);

        return credentialsMatcher;
    }


    /**
     * @Author lorenzo
     * @Description 自定义sessionManager
     * @Date 16:50 2019/8/7
     * @Param []
     * @return org.apache.shiro.session.mgt.SessionManager
     **/
    @Bean
    public SessionManager sessionManager(){

        DefaultWebSessionManager mySessionManger = new MySessionManager();

        //超时时间，默认 30分钟，会话超时；方法里面的单位是毫秒
        //customSessionManager.setGlobalSessionTimeout(20000);

        //配置session持久化
        mySessionManger.setSessionDAO(redisSessionDAO());

        return mySessionManger;
    }



   /**
    * @Author lorenzo
    * @Description 配置redisManager
    * @Date 16:51 2019/8/7
    * @Param []
    * @return org.crazycake.shiro.RedisManager
    **/
    public RedisManager getRedisManager(){
        RedisManager redisManager = new RedisManager();

        /*
         * @Author lorenzo
         * 从 【application.properties】 配置文件中获取参数，为了统一修改方式
         **/
        redisManager.setHost(env.getProperty("spring.redis.host"));
        redisManager.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));
        redisManager.setPassword(env.getProperty("spring.redis.password"));
        return redisManager;
    }


    /**
     * @Author lorenzo
     * @Description 配置具体cache实现类,这里使用的是第三方包
     * @Date 16:51 2019/8/7
     * @Param []
     * @return org.crazycake.shiro.RedisCacheManager
     **/
    @Bean
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());
        redisCacheManager.setPrincipalIdFieldName("username");
        //设置过期时间，单位是秒，30分钟
        Integer expire = Integer.parseInt(env.getProperty("shiro.redis.auther.expire"));
        redisCacheManager.setExpire( expire );

        return redisCacheManager;
    }


    /**
     * @Author lorenzo
     * @Description 自定义session持久化
     * @Date 16:51 2019/8/7
     * @Param []
     * @return org.crazycake.shiro.RedisSessionDAO
     **/
    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(getRedisManager());
        return redisSessionDAO;
    }



}
