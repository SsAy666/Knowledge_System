package com.knowledge.controller;

import com.knowledge.dto.UserRoleDTO;
import com.knowledge.entity.UserEntity;
import com.knowledge.service.AdminService;
import com.knowledge.utils.Result;
import com.knowledge.vo.AddUserVO;
import com.knowledge.vo.UpdateUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员controller
 */
@RestController
@RequestMapping("/admin")
@Slf4j
@Api(tags = "管理员接口")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 管理员新增用户
     * @param addUserVO 添加用户参数信息VO
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "管理员新增用户接口", notes = "管理员新增用户接口")
    @PostMapping("/addUser")
    public Result addUser(@RequestBody AddUserVO addUserVO){
        adminService.addUser(addUserVO);
        return new Result().success("添加用户成功！");
    }

    /**
     * 管理员修改用户
     * @param updateUserVO 修改用户参数信息VO
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "管理员修改用户接口", notes = "管理员修改用户接口")
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody UpdateUserVO updateUserVO){
        adminService.updateUser(updateUserVO);
        return new Result().success("修改用户成功！");
    }

    /**
     * 管理员查询用户
     * @param userEntity 用户参数信息
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "管理员查询用户接口", notes = "管理员查询用户接口")
    @PostMapping("/queryUser")
    public Result queryUser(@RequestBody UserEntity userEntity){
        List<UserRoleDTO> users = adminService.queryUser(userEntity);
        return new Result().ok(users);
    }

    /**
     * 管理员删除用户
     * @param userId 用户ID
     * @return
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "管理员删除用户接口", notes = "管理员删除用户接口")
    @GetMapping("/delUser")
    public Result delUser(@ApiParam(value = "用户ID", required = true) @RequestParam("userId") Integer userId){
        adminService.delUser(userId);
        return new Result().ok("删除用户成功！");
    }
}
