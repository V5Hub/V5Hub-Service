package com.V5Hub.volunteerservice.controller;

import com.V5Hub.volunteerservice.model.User;
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

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/wx/user", method = RequestMethod.GET)
    @ResponseBody
    public Result getInfo(@RequestParam(value = "openid", defaultValue = "") String openid) {
        User user = userService.selectById(openid);
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

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public Result setInfo(@RequestParam(value = "openid", defaultValue = "") String openid,
                          @RequestParam(value = "name", defaultValue = "") String name,
                          @RequestParam(value = "studentId", defaultValue = "") String studentId,
                          @RequestParam(value = "email", defaultValue = "") String email,
                          @RequestParam(value = "phoneNumber", defaultValue = "") String phoneNumber,
                          @RequestParam(value = "college", defaultValue = "") String college) {
        User user = userService.selectById(openid);
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
}
