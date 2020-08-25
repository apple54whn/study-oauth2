package top.conanan.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * 用户身份信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    public static final String SESSION_USER_KEY = "_user";

    private String id;

    private String username;

    private String password;

    private String fullName;

    private String mobile;

    // 用户权限
    private Set<String> authorities;
}
