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
    public Result info(@RequestParam(value = "openid", defaultValue = "") String openid) {
        User user = userService.selectById(openid);
        if (user == null) {
            return new Result(null, 404, "Invalid openid.");
        }
        if (user.isDeleted()) {
            return new Result(null, 403, "Requested user has been deleted.");
        }
        return new Result(user, 200, "Success.");
    }
}
