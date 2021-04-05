package com.knowledge.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.knowledge.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义的登录验证失败后的去向
 */
@Component
@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    // 登录失败时，用来判断是返回json数据还是跳转html页面
    public static final String RETURN_TYPE = "json";

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败:" + exception.getMessage());
        log.info("username=>" + request.getParameter("username"));

        if (RETURN_TYPE.equals("html")) {
            redirectStrategy.sendRedirect(request, response, "/login/index?error=true");
        } else {
            Result<String> result = new Result<>();
            String msg = null;
            result.setCode(401);
            if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
                msg = "用户名或密码错误";
            } else if (exception instanceof DisabledException) {
                msg = "账户被禁用";
            } else {
                msg = "登录失败!";
            }
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(JSON.toJSONString(new Result<>(401,msg,null), SerializerFeature.WriteMapNullValue));
        }
    }
}
