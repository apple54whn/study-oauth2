package top.conanan.security.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 必须有这个类！
 * 继承的那个类实现了 WebApplicationInitializer 接口，和 WebAppInitializer 也是底层实现了该接口
 * Spring 容器启动时会加载 WebApplicationInitializer 接口的所有实现类
 */
public class SpringSecurityApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
