package com.knowledge.controller;

import com.knowledge.dto.UserRoleDTO;
import com.knowledge.entity.UserEntity;
import com.knowledge.service.AdminService;
import com.knowledge.utils.Result;
import com.knowledge.vo.AddUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员controller
 */
@RestController
@RequestMapping("/admin")
//@CrossOrigin
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 管理员新增用户
     * @param addUserVO 添加用户参数信息VO
     * @return
     */
    @PostMapping("/addUser")
    public Result addUser(@RequestBody AddUserVO addUserVO){
        adminService.addUser(addUserVO);
        return new Result().success("添加用户成功！");
    }

    /**
     * 管理员查询用户
     * @param userEntity 用户参数信息
     * @return
     */
    @PostMapping("/queryUser")
    public Result queryUser(@RequestBody UserEntity userEntity){
        List<UserRoleDTO> users = adminService.queryUser(userEntity);
        return new Result().ok(users);
    }

    /**
     * 管理员删除用户
     * @param addUserVO 添加用户参数信息VO
     * @return
     */
    @PostMapping("/delUser")
    public Result delUser(@RequestBody AddUserVO addUserVO){
        adminService.addUser(addUserVO);
        return new Result().ok("添加用户成功！");
    }
}
