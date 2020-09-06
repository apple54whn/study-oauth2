package top.conanan.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义 UserDetailsService
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 连接数据库，根据 username 查询用户
        System.out.println("username:"+ username);
        return User.withUsername("zhangsan").password("$2a$10$VD2tV49..qSgU6g3UA4rIeqVsXdEQuTigZ5aA2GH9ldkYj6kAL6Au").authorities("p1").build();
    }
}
