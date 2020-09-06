package top.conanan.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;
import top.conanan.security.interceptor.SimpleAuthenticationInterceptor;

/**
 * 相当于 SpringMVC 的配置
 * <p>
 * 我们有多种方法来配置DispatcherServlet，与之类似，启用Spring MVC组件的方法也不只一种。
 * 从前，Spring是使用XML进行配置的，可以使用<mvc:annotation-driven>启用注解驱动的Spring MVC。
 */
@Configuration
@EnableWebMvc// 启用 Spring MVC 注解驱动，替代处理器映射器、处理器适配器。
@ComponentScan(
        value = "top.conanan.security.controller",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
        useDefaultFilters = false// 必须有！这里仅仅扫描 Controller
)
// extends WebMvcConfigurerAdapter 在 Spring 5.0 已经 Deprecated
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SimpleAuthenticationInterceptor simpleAuthenticationInterceptor;

    /**
     * 配置视图解析器
     *
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    /**
     * 配置视图控制器
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(simpleAuthenticationInterceptor)
                .excludePathPatterns("/", "/login", "/logout") // 放行登录等资源
                .addPathPatterns("/r/**");// 其实这行写了后就不用再放行上面那些资源了
    }
}
