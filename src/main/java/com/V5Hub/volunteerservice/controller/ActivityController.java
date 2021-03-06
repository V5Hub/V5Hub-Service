package com.V5Hub.volunteerservice.controller;

import com.V5Hub.volunteerservice.model.Activity;
import com.V5Hub.volunteerservice.model.User;
import com.V5Hub.volunteerservice.service.ActivityService;
import com.V5Hub.volunteerservice.service.TagService;
import com.V5Hub.volunteerservice.service.UserService;
import com.V5Hub.volunteerservice.util.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 活动Controller
 *
 * @version 1.0
 */
@RestController
public class ActivityController {
    private final ActivityService activityService;
    private final UserService userService;

    @Autowired
    public ActivityController(ActivityService activityService,
                              UserService userService) {
        this.activityService = activityService;
        this.userService = userService;
    }

    @RequestMapping(value = "/activities/recent", method = RequestMethod.GET)
    @ResponseBody
    public Result recent(@RequestParam(value = "openid", defaultValue = "") String openid) {
        User user = userService.selectByOpenid(openid);
        Calendar date = new GregorianCalendar();
        date.setTime(new Date());
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.add(Calendar.DATE, -6);
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();
        for (int i = -6; i <= 21; i++) {
            HashMap<String, Object> item = new HashMap<>();
            item.put("date", String.format("%d-%d-%d",
                    date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH)));
            Date pre = date.getTime();
            date.add(Calendar.DATE, 1);
            List<Activity> activityList = activityService.selectByDate(pre, date.getTime());
            List<HashMap<String, Object>> activities = new ArrayList<>();
            for (Activity activity : activityList) {
                HashMap<String, Object> activityJson = activityService.parseSimpleData(activity, user);
                // TODO: 根据用户对tag的订阅情况对activity.tags做进一步处理
                activities.add(activityJson);
            }
            item.put("activities", activities);
            data.add(item);
        }
        return new Result(data, 200, "Success.");
    }
}
