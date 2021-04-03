package com.knowledge.handler;

import com.alibaba.fastjson.JSON;
import com.knowledge.dao.UserDao;
import com.knowledge.dto.UserLoginDTO;
import com.knowledge.dto.UserRoleDTO;
import com.knowledge.utils.JwtTokenUtil;
import com.knowledge.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的登录验证成功后的去向
 */
@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDao userDao;

    // 登录成功时，用来判断是返回json数据还是跳转html页面
    public static final String RETURN_TYPE = "json";

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        log.info("登录成功");
        log.info("username=>" + username);
        if(RETURN_TYPE.equals("html")) {
            redirectStrategy.sendRedirect(request, response, "/user/index");
        } else {
            // 根据用户名获取用户相关信息
            List<UserRoleDTO> userRoleDTOS = userDao.queryUser(username);
            List<String> roles = new ArrayList<>();
            UserLoginDTO userLoginDTO = new UserLoginDTO();
            userLoginDTO.setUsername(username);
            userLoginDTO.setUserId(userRoleDTOS.get(0).getUserId());
            userLoginDTO.setToken(jwtTokenUtil.generateToken(username));
            for (UserRoleDTO userRoleDTO : userRoleDTOS) {
                roles.add(userRoleDTO.getRoleName());
            }
            userLoginDTO.setRoles(roles);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(new Result<UserLoginDTO>(200,"登录成功",userLoginDTO)));
        }
    }
}
