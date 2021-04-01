package com.knowledge.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knowledge.filter.MyAuthenticationFilter;
import com.knowledge.handler.*;
import com.knowledge.service.impl.auth.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring Security的配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailHandler;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //必须授权才能访问(本地postman自测使用)
        //http.authorizeRequests().anyRequest().authenticated();
        //开启跨域(前后端分离用)
        http.cors().and().csrf().disable().authorizeRequests()
                //处理跨域请求中的Preflight请求
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(HttpMethod.POST,"/user/login").permitAll()
                .anyRequest().authenticated();
        //使用自带的登录
        http.formLogin()
                .permitAll()
                // 访问需要登录才能访问的页面，如果未登录，会跳转到该地址来
//                .loginPage("/authentication/login")
                .loginProcessingUrl("/login")
                //登录失败，返回json
                //.failureHandler(myAuthenticationFailHandler)
                //登录成功，返回json
                .successHandler(myAuthenticationSuccessHandler);
        //处理异常情况：用户未登录和权限不足
        http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint).accessDeniedHandler(myAccessDeniedHandler);
        //退出登录
        http.logout().logoutSuccessHandler(myLogoutSuccessHandler).permitAll();
        //开启跨域访问
        http.cors().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();
        //http.addFilterAt(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


        //开启跨域 cors()
        /*http.cors().and().csrf().disable().authorizeRequests()
                //处理跨域请求中的Preflight请求
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(HttpMethod.POST,"/user/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
                // 设置登陆成功页
                //.defaultSuccessUrl("/")
                .and()
                .logout()
                .and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);
        http.formLogin().loginProcessingUrl("/user/login").permitAll();*/


        //        什么是（cors 预检请求） 就是你要跨域请求得时候 你要预先发一个请求看对面是拦你还是放你
        //第1步：解决跨域问题。cors 预检请求放行,让Spring security 放行所有preflight request（cors 预检请求）
        //http.formLogin().loginProcessingUrl("/user/login").permitAll();
        //http.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

        // 关闭CSRF跨域
        //http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

    @Bean
    MyAuthenticationFilter myAuthenticationFilter() throws Exception {
        MyAuthenticationFilter filter = new MyAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(myAuthenticationFailHandler);
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //用户密码加密验证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
