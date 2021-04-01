package com.knowledge.handler;

import com.alibaba.fastjson.JSON;
import com.knowledge.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义的登录验证成功后的去向
 */
@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    // 登录成功时，用来判断是返回json数据还是跳转html页面
    public static final String RETURN_TYPE = "json";

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");
        log.info("username=>" + request.getParameter("username"));
        if(RETURN_TYPE.equals("html")) {
            redirectStrategy.sendRedirect(request, response, "/user/index");
        } else {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(new Result<Authentication>(200,"登录成功",authentication)));
        }
    }
}
