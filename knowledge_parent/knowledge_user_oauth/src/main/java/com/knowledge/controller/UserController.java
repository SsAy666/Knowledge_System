package com.knowledge.controller;

import com.knowledge.dto.UserLoginDTO;
import com.knowledge.service.UserService;
import com.knowledge.utils.Result;
import com.knowledge.vo.AddUserVO;
import com.knowledge.vo.RegisterUserVO;
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
}
