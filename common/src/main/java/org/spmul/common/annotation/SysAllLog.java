package org.spmul.common.annotation;

import java.lang.annotation.*;

/**
 * 记录系统所有更新保存日志
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysAllLog {
    /**
     * 操作描述
     * @return
     */
    String operation();

    /**
     * 操作类型
     * @return
     */
    String optTypeName();

    /**
     * 操作类型2
     * @return
     */
    String optTypeName2() default "";

}
