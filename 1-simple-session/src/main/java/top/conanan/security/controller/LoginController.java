package top.conanan.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.conanan.security.model.AuthenticationRequest;
import top.conanan.security.model.UserDto;
import top.conanan.security.service.AuthenticationService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 登录
     * @param authenticationRequest
     * @param session
     * @return
     */
    @PostMapping(value = "/login", produces = {"text/plain;charset=utf-8"})
    public String login(AuthenticationRequest authenticationRequest, HttpSession session){
        UserDto userDto = authenticationService.authentication(authenticationRequest);

        // 登录成功存入 session
        session.setAttribute(UserDto.SESSION_USER_KEY, userDto);
        return userDto.getUsername()+"登录成功";
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @GetMapping(value = "/logout", produces = "text/plain;charset=utf-8")
    public String logout(HttpSession session){
        // 使会话无效
        session.invalidate();
        return "退出成功";
    }

    /**
     * 访问资源r1
     * @param session
     * @return
     */
    @GetMapping(value = "/r/r1", produces = "text/plain;charset=utf-8")
    public String accessR1(HttpSession session){
        String fullName;
        Object object = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (object == null){
            fullName = "匿名";
        } else {
            UserDto userDto = (UserDto) object;
            fullName = userDto.getFullName();
        }
        return fullName + "访问资源r1";
    }

    /**
     * 访问资源r2
     * @param session
     * @return
     */
    @GetMapping(value = "/r/r2", produces = "text/plain;charset=utf-8")
    public String accessR2(HttpSession session){
        String fullName;
        Object object = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (object == null){
            fullName = "匿名";
        } else {
            UserDto userDto = (UserDto) object;
            fullName = userDto.getFullName();
        }
        return fullName + "访问资源r2";
    }
}
