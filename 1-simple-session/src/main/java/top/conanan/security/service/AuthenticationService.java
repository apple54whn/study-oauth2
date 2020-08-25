package top.conanan.security.service;

import org.springframework.stereotype.Service;
import top.conanan.security.model.AuthenticationRequest;
import top.conanan.security.model.UserDto;

/**
 * 认证服务接口
 */
public interface AuthenticationService {

    /**
     * 用户认证
     * @param authenticationRequest 用户认证请求（包括账号和密码等）
     * @return 认证成功的用户信息
     */
    UserDto authentication(AuthenticationRequest authenticationRequest);
}
