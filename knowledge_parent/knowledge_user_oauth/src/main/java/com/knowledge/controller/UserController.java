package com.knowledge.controller;

import com.knowledge.service.UserService;
import com.knowledge.utils.Result;
import com.knowledge.vo.RegisterUserVO;
import com.knowledge.vo.UpdatePwdVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理入口
 */
@RestController
@RequestMapping("/user")
//@CrossOrigin
@Slf4j
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册用户
     * @param registerUserVO 注册信息VO
     * @return
     */
    @ApiOperation(value = "注册用户接口", notes = "注册用户接口")
    @PostMapping("/registerUser")
    public Result registerUser(@RequestBody RegisterUserVO registerUserVO){
        userService.registerUser(registerUserVO);
        return new Result().ok("注册成功,请前往登录！");
    }

    /**
     * 用户修改密码
     * @param updatePwdVO 用户修改密码信息VO
     * @return
     */
    @ApiOperation(value = "用户修改密码接口", notes = "用户修改密码接口")
    @PostMapping("/updatePwd")
    public Result updatePwd(@RequestBody UpdatePwdVO updatePwdVO){
        userService.updatePwd(updatePwdVO);
        return new Result().ok("修改密码成功,请重新登录！");
    }
}
