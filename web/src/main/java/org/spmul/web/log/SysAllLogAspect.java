package org.spmul.web.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.spmul.common.annotation.SysAllLog;
import org.spmul.common.entity.SysAllLogEntity;
import org.spmul.web.log.util.ErrUtil;
import org.spmul.web.log.util.IPUtil;
import org.spmul.web.log.util.RequestParameterUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class SysAllLogAspect {



    @Pointcut("within(org.spmul.web.controller..*) && @annotation(org.spmul.common.annotation.SysAllLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object saveSysLog(ProceedingJoinPoint pjd) throws Throwable {
        log.info("全局log。。");
        Throwable curr = null;
        String tableName = "success";
        SysAllLogEntity sysLog = createSysAllLogEntityByJoinPoint(pjd);
        Object result = null;
        //执行目标方法
        try {
            //前置通知
            result = pjd.proceed();
            //后置通知
        } catch (Throwable e) {
            sysLog.setExceptionVal(ErrUtil.errToString(e));
            //保存系统日志
            tableName = "fail";
            curr = e;
        }
        try {
            sysLog.setDuration((System.currentTimeMillis() - sysLog.getCreateDate().getTime()));
        }catch (Exception e){
            e.printStackTrace();
        }
        sysLog.setTableName(tableName);
        String username = sysLog.getUsername();
        log.info(JSON.toJSONString(sysLog));
        if(curr != null){
            throw curr;
        }
        return result;
    }

    /**
     * 根据切入点生成记录类
     * @param pjd 连接点
     * @return SysAllLogEntity
     */
    private SysAllLogEntity createSysAllLogEntityByJoinPoint(ProceedingJoinPoint pjd){
        //只记录操作成功的数据
        MethodSignature signature = (MethodSignature) pjd.getSignature();
        Method method = signature.getMethod();

        SysAllLogEntity sysLog = new SysAllLogEntity();
        SysAllLog syslog = method.getAnnotation(SysAllLog.class);
        if(syslog != null){
            //注解上的描述
            sysLog.setOperation(syslog.operation());
            sysLog.setOptType(syslog.optTypeName());
            sysLog.setOptType2(syslog.optTypeName2());
        }

        //请求的方法名
        String className = pjd.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = pjd.getArgs();
        sysLog.setParams(RequestParameterUtil.getParams(args));

        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //设置IP地址
        String ipAddr = IPUtil.getIpAddr(request);
        sysLog.setIp(ipAddr);
        sysLog.setComputerName(IPUtil.getComputerName(ipAddr, request));


        sysLog.setCreateDate(new Date());
        return sysLog;
    }

}
