package com.sb.stu.commonswagger.rest;

import com.sb.stu.commonswagger.model.User;
import com.sb.stu.commonswagger.rest.reqvo.UserReq;
import com.sb.stu.commonswagger.rest.respvo.UserResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 编  号：
 * 名  称：UserController
 * 描  述：
 * 完成日期：2018/11/24 15:25
 * @author：felix.shao
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("user")
public class UserController {

    /**
     * http://localhost:8080/comswagger/rest/user/getById/1
     * @param userId
     * @return
     */
    @GetMapping("/getById/{userId}")
    public User getUser(@PathVariable Integer userId){
        return new User(1, "张三", 18, 1, LocalDateTime.now());
    }

    @PostMapping("/saveUser")
    public boolean saveUser(@RequestBody User user){
        return true;
    }

    @ApiOperation(value = "更新用户信息")
    @ApiImplicitParam(name = "user", value = "源用户信息", paramType = "body", required = true, dataType = "userReq")
    @ApiResponse(code = 200, message = "success", response = UserResp.class)
    @PostMapping("/updateUser")
    public UserResp updateUser(@RequestBody UserReq userReq){
        return new UserResp();
    }

}
