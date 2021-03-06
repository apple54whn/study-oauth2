package top.conanan.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 配置用户信息服务（这里就是查询的用户信息，可自定义其他非内存方法）
     * Spring Security会使用它来获取用户信息。暂时使用InMemoryUserDetailsManager实现类，并在其中分别创建了zhangsan、lisi两个用户，并设置密码和权限。
     *
     * @return
     */
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     // 这里的用户名密码可以理解为数据库或其他地方存储的
    //     InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    //     manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
    //     manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
    //     return manager;
    // }

    /**
     * 密码加密方式（此处使用不加密的）
     *
     * @return
     */
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        // Spring Security 会根据此配置来决定密码是否编码
        return NoOpPasswordEncoder.getInstance();
    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




    /**
     * 拦截机制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // ant 风格的路径
                .antMatchers("/r/r1").hasAuthority("p1")
                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/**").authenticated()// url匹配/r/**的资源，经过认证后才能访问
                .anyRequest().permitAll()// 其他url完全开放
                .and()
                .formLogin().successForwardUrl("/login-success");// 支持form表单认证，认证成功后转向/login-success（此处controller必须使用post请求）
    }

}
