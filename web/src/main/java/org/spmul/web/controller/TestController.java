package org.spmul.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.spmul.common.annotation.SysAllLog;
import org.spmul.common.base.BaseController;
import org.spmul.common.base.BaseDao;
import org.spmul.common.util.R;
import org.spmul.entity.AreaEntity;
import org.spmul.entity.TestEntiry;
import org.spmul.redis.RedisUtil;
import org.spmul.service.AreaService;
import org.spmul.shiro.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("test")
@Slf4j
public class TestController extends BaseController<AreaEntity>{

    @Autowired
    private AreaService areaService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected BaseDao<AreaEntity> getBaseService() {
        return areaService;
    }

    @RequestMapping("/test")
    @SysAllLog(operation="这是一个测试方法,测试aop有没有生效的方法。",optTypeName = "测试" ,optTypeName2 = "测试。。。。")
    public R test(){
        log.info("执行了test()方法。");
        List<AreaEntity> areaEntities = areaService.queryList(new HashMap<>());
        return R.ok().put("list" , areaEntities);
    }

    @RequestMapping("/page")
    @SysAllLog(operation="这是一个测试方法,测试分页。",optTypeName = "测试" ,optTypeName2 = "测试分页。。。。")
    public R testPage(){
        Map<String , Object> params = new HashMap<>();
        params.put("page" , 1);
        params.put("limit" , 10);
        return list(params);
    }

    @RequestMapping("/testSaveRedis")
    @RequiresPermissions("cookie:deviceCasinoSelect")
    @SysAllLog(operation = "这是一个测试redis的方法" , optTypeName = "redis测试" , optTypeName2 = "测试redis.....")
    public R testSaveRedis(){

        String key = "test";

        Map<String , Object> values = new HashMap<>();
        values.put("a" ,new Date().getTime());
        /*
         * set方法只能存Object对象（非字符串），详见 org.spmul.redis.RedisConfig中的配置。
         **/
        redisUtil.set(key , values);
        //设置10秒过期。(一定要在set方法之后调用)
        Boolean bl =  redisUtil.expire(key , 10);
        return R.ok().put("success" , bl);
    }

    @RequestMapping("/testGetRedis")
    @SysAllLog(operation = "这是一个测试redis的方法" , optTypeName = "redis测试" , optTypeName2 = "测试redis.....")
    public R testGetRedis(){
        Object value = redisUtil.get("test");
        return R.ok().put("value" , value).put("timeOut" , redisUtil.getExpire("test"));
    }


    @RequestMapping("/haha")
    public String haha(){
        return TestEntiry.showName();
    }


}
