package com.V5Hub.volunteerservice.controller;

import com.V5Hub.volunteerservice.model.Register;
import com.V5Hub.volunteerservice.model.User;
import com.V5Hub.volunteerservice.service.ActivityService;
import com.V5Hub.volunteerservice.service.RegisterService;
import com.V5Hub.volunteerservice.service.UserService;
import com.V5Hub.volunteerservice.util.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 普通用户Controller
 *
 * @version 1.0
 */
@RestController
public class UserController {
    private final UserService userService;
    private final RegisterService registerService;
    private final ActivityService activityService;

    @Autowired
    public UserController(UserService userService, RegisterService registerService, ActivityService activityService){
        this.userService = userService;
        this.registerService = registerService;
        this.activityService = activityService;
    }

    /**
     * 获取用户信息
     * @param openid
     * @return {@link Result}
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public Result getInfo(@RequestParam(value = "openid", defaultValue = "") String openid) {
        User user = userService.selectByOpenid(openid);
        if (user == null) {
            return new Result(null, 404, "Invalid openid.");
        }
        if (user.isDeleted()) {
            return new Result(null, 410, "Requested user has been deleted.");
        }
        user.setPassword("");
        user.setPicture(null);
        user.setRegisters(null);
        return new Result(user, 200, "Success.");
    }

    /**
     * 修改用户信息
     * @param openid
     * @param name
     * @param studentId
     * @param email
     * @param phoneNumber
     * @param college
     * @return {@link Result}
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public Result setInfo(@RequestParam(value = "openid", defaultValue = "") String openid,
                          @RequestParam(value = "name", defaultValue = "") String name,
                          @RequestParam(value = "studentId", defaultValue = "") String studentId,
                          @RequestParam(value = "email", defaultValue = "") String email,
                          @RequestParam(value = "phoneNumber", defaultValue = "") String phoneNumber,
                          @RequestParam(value = "college", defaultValue = "") String college) {
        User user = userService.selectByOpenid(openid);
        if (user == null) {
            return new Result(null, 404, "Invalid openid.");
        }
        if (user.isDeleted()) {
            return new Result(null, 410, "Requested user has been deleted.");
        }
        user.setName(name);
        user.setStudentId(studentId);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setCollege(college);
        if (userService.update(user) == 0) {
            return new Result(null, 500, "Update failed.");
        } else {
            return new Result(null, 200, "Success.");
        }
    }

    /**
     * 用户报名
     * @param openid
     * @return {@link Result}
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    @ResponseBody
    public Result userRgister(@RequestParam(value = "openid", defaultValue = "") String openid,
                              @RequestParam(value = "id", defaultValue = "") int id,
                              @RequestParam(value = "name", defaultValue = "") String name,
                              @RequestParam(value = "studentId", defaultValue = "") String studentId,
                              @RequestParam(value = "email", defaultValue = "") String email,
                              @RequestParam(value = "phoneNumber", defaultValue = "") String phoneNumber,
                              @RequestParam(value = "userClass", defaultValue = "") String userClass,
                              @RequestParam(value = "college", defaultValue = "") String college) {
        User user = userService.selectByOpenid(openid);
        Register register = userService.selectByUserIdActivityId(user.getId(), id);
        if(register == null){
            Register register1 = new Register(null,activityService.selectById(id), user.getId(), name,studentId,email,phoneNumber,userClass,college,1);
            registerService.insert(register1);
            return Result.success(register, 200, "successfully register");
        } else {
            register.setName(name);
            register.setStudentId(studentId);
            register.setEmail(email);
            register.setPhoneNumber(phoneNumber);
            register.setUserClass(userClass);
            register.setCollege(college);
            if(registerService.update(register)>0){
                return Result.success(register, 200, "updated registration information");
            } else {
                return Result.fail(register, 5000, "failed to update registration information");
            }

        }
    }

}
