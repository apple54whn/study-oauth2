package top.conanan.security.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.conanan.security.model.AuthenticationRequest;
import top.conanan.security.model.UserDto;
import top.conanan.security.service.AuthenticationService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    /**
     * 用户认证，校验用户信息是否合法
     * @param authenticationRequest 用户认证请求（包括账号和密码等）
     * @return 认证成功的用户信息
     */
    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {

        // 校验参数是否为空
        if (authenticationRequest == null
                || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())){
            throw new RuntimeException("账号或密码为空");
        }

        // 模拟用户查询
        UserDto user = getUserDto(authenticationRequest.getUsername());

        // 判断用户是否为空
        if (user == null){
            throw new RuntimeException("查询不到该用户");
        }

        // 校验密码
        if (!authenticationRequest.getPassword().equals(user.getPassword())){
            throw new RuntimeException("账号或密码错误");
        }

        // 认证成功返回用户信息
        return user;
    }


    /**
     * 根据用户名查询用户信息（一般不放在这里）
     * @param username 用户名
     * @return
     */
    public UserDto getUserDto(String username){
        return userMap.get(username);
    }


    // 模拟用户信息
    private Map<String,UserDto> userMap = new HashMap<>();

    {
        HashSet<String> authorities1 = new HashSet<>();
        authorities1.add("p1");// permission 1，令其和 r/r1 绑定

        HashSet<String> authorities2 = new HashSet<>();
        authorities2.add("p2");// permission 2，令其和 r/r2 绑定


        userMap.put("zhangsan", new UserDto("100001", "zhangsan", "123", "张三", "88880001", authorities1));
        userMap.put("lisi", new UserDto("100002", "lisi", "456", "李四", "88880002", authorities2));
    }

}
