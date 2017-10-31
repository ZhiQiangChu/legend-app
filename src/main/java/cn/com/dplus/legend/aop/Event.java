package cn.com.dplus.legend.aop;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:36 17-8-25
 * @Modified By:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Event {

    long id() default 0;

    String service() default "";

    String resource() default "";

    String action() default "";

    String eventName() default "";
}
