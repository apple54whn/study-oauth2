package top.conanan.security.model;

import lombok.Data;

/**
 * 用户认证请求参数
 */
@Data
public class AuthenticationRequest {

    private String username;

    private String password;
}
