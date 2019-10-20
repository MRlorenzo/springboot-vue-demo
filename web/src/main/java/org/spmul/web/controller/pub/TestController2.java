package org.spmul.web.controller.pub;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spmul.dao.mybatis.mapper.JsExceptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Lorenzo
 * @Date 2019/10/19 23:13
 */
@RestController
// 表示标识这个类是swagger的资源
@Api(value = "TestController", tags = {"restful api示例"})
public class TestController2 {

    Logger logger = LoggerFactory.getLogger(TestController2.class);

    @Autowired
    private JsExceptionMapper jsExceptionMapper;

    @RequestMapping("/test/{id}")
    //表示一个http请求的操作
    @ApiOperation(value = "修改指定产品", httpMethod = "PUT", produces = "application/json")
    //@ApiImplicitParams用于方法，包含多个@ApiImplicitParam表示单独的请求参数
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "产品ID", required = true, paramType = "path")})
    public Object test(@PathVariable("id") Integer id){
        //查询该表的所有数据
        return jsExceptionMapper.selectByExample(null);
    }


    @RequestMapping("/logger")
    public String logger() {
        logger.trace("日志输出 {}", "trace");
        logger.debug("日志输出 {}", "debug");
        logger.info("日志输出 {}", "info");
        logger.warn("日志输出 {}", "warn");
        logger.error("日志输出 {}", "error");
        return "00";
    }

}
