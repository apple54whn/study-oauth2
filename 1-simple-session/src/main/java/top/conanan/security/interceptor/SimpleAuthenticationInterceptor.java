package top.conanan.security.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.conanan.security.model.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 校验用户请求的 url 是否在用户的权限内
        HttpSession session = request.getSession();
        Object object = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (object == null){
            // 未登录
            writeContent(response, "请先登录");
            return false;
        }

        UserDto userDto = (UserDto) object;
        // 请求的 url（requestURI 包括查询参数）
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/r/r1") && userDto.getAuthorities().contains("p1")) {
            return true;
        }

        if (requestURI.contains("/r/r2") && userDto.getAuthorities().contains("p2")){
            return true;
        }

        writeContent(response, "没有权限，拒绝访问");
        return false;
    }

    private void writeContent(HttpServletResponse response, String content) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(content);
        writer.close();
    }
}
