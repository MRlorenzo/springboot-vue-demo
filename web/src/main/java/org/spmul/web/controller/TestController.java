package org.spmul.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.spmul.common.annotation.SysAllLog;
import org.spmul.common.base.BaseController;
import org.spmul.common.base.BaseDao;
import org.spmul.common.util.R;
import org.spmul.entity.OrderEntity;
import org.spmul.entity.TestEntiry;
import org.spmul.redis.RedisUtil;
import org.spmul.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TestController extends BaseController<OrderEntity>{

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected BaseDao<OrderEntity> getBaseService() {
        return orderService;
    }

    @RequestMapping("/transaction/list")
    public R list(){
        Map<String , Object> data = new HashMap<>();
        data.put("total", 4);
        data.put("items" , orderService.queryList(null));
        return R.ok().put("data" , data);
    }

    @RequestMapping("/test")
    @SysAllLog(operation="这是一个测试方法,测试aop有没有生效的方法。",optTypeName = "测试" ,optTypeName2 = "测试。。。。")
    public R test(){
        log.info("执行了test()方法。");
        List<OrderEntity> areaEntities = orderService.queryList(new HashMap<>());
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

    /**
     * 在触发这个接口的业务处理之后，业务逻辑处理时间长达30秒，
     * 需要在处理结束前，发起停止指令，验证是否能够正常返回。
     * 验证时所使用的kill指令：kill -2（Ctrl + C）、kill -15、kill -9。
     * @param systemNo
     * @return
     */
    @GetMapping(value = "/sleep/one", produces = "application/json")
    @SysAllLog(operation = "这是一个测试是否安全退出应用的方法", optTypeName = "springboot中断测试")
    public Long sleepOne(String systemNo){
        log.info("模拟业务处理30秒，请求参数：{}", systemNo);
        Long serverTime = System.currentTimeMillis();
        try {
            Thread.sleep(30*1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (System.currentTimeMillis() < serverTime + (30 * 1000)){
            log.info("正在处理业务，当前时间：{}，开始时间：{}", System.currentTimeMillis(), serverTime);
        }

        log.info("模拟业务处理1分钟，响应参数：{}");
        return serverTime;
    }


}
