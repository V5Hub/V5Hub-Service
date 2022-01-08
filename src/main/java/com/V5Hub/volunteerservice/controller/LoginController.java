package com.V5Hub.volunteerservice.controller;


import com.V5Hub.volunteerservice.config.InfoConfig;
import com.V5Hub.volunteerservice.entity.WeChatSessionModel;
import com.V5Hub.volunteerservice.model.User;
import com.V5Hub.volunteerservice.service.UserService;
import com.V5Hub.volunteerservice.util.response.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/wx")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService){
        this.userService = userService;
    }


    @RequestMapping("/example")
    @ResponseBody
    public Result example() {
        return Result.success(userService.newUserLogin("1111"),200,"sucess");
    }

    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestParam(value = "code", defaultValue = "") String code) {
        String openid = null;
        String session_key = null;
        String errcode = null;
        String errmsg = null;
        WeChatSessionModel weChatSessionModel;

        //微信服务器的接口路径
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + InfoConfig.APPID + "&secret=" + InfoConfig.APPSECRET + "&js_code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        //进行网络请求,访问微信服务器接口
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        //根据返回值进行后续操作

        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            String sessionData = responseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            //将json字符串转化为实体类;
            try {
                weChatSessionModel = objectMapper.readValue(sessionData, WeChatSessionModel.class);

                //获取用户的唯一标识
                openid = weChatSessionModel.getOpenid();
                //获取会话秘钥（具有时效性，用户登录的临时通行证）
                //登录后前端的每次接口请求都需带上session_key
                session_key = weChatSessionModel.getSession_key();
                //获取错误码
                errcode = weChatSessionModel.getErrcode();
                //获取错误信息
                errmsg = weChatSessionModel.getErrmsg();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        try {
            if (openid == null || session_key == null) {
                return Result.fail(null, 5000, "login failed");
            }
            else if (userService.selectById(openid) == null) {
                User user = userService.newUserLogin(openid);
                int count = userService.insert(user);
                if (count >= 0) {
                    return Result.success(user);
                } else {
                    return Result.fail(null, 5000, "login failed");
                }
            }
            else {
                return Result.success(userService.selectById(openid));
            }
        } catch (Exception e) {
            return Result.fail(null, 6000, e.getMessage());
        }
    }

}
