package com.V5Hub.volunteerservice.controller;

import com.V5Hub.volunteerservice.model.*;
import com.V5Hub.volunteerservice.service.*;
import com.V5Hub.volunteerservice.util.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 管理员controller
 * 后续对此controller下的所有URL进行权限拦截
 *
 * @version 1.0
 */
@RestController
public class ManagerController {

    private final ManagerService managerService;
    private final ActivityService activityService;
    private final UserService userService;
    private final SponsorService sponsorService;
    private final TagService tagService;

    @Autowired
    ManagerController(ManagerService managerService, ActivityService activityService, UserService userService, SponsorService sponsorService, TagService tagService){
        this.managerService = managerService;
        this.activityService = activityService;
        this.userService = userService;
        this.sponsorService = sponsorService;
        this.tagService =tagService;
    }

    /**
     * 获取用户信息
     * @param openid
     * @return {@link Result}
     */
    @RequestMapping(value = "/manager/new_activity", method = RequestMethod.POST)
    @ResponseBody
    public Result postActivity(@RequestParam(value = "openid", defaultValue = "") String openid,
                               @RequestParam(value = "name", defaultValue = "") String name,
                               @RequestParam(value = "startTime", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
                               @RequestParam(value = "endTime", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
                               @RequestParam(value = "registerDeadline", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date registerDeadline,
                               @RequestParam(value = "position", defaultValue = "") String position,
                               @RequestParam(value = "level", defaultValue = "") String level,
                               @RequestParam(value = "sponsorName", defaultValue = "") Integer sponsorId,
                               @RequestParam(value = "tags", defaultValue = "") List<Integer> tags,
                               @RequestParam(value = "description", defaultValue = "") String description,
                               @RequestParam(value = "content", defaultValue = "") String content,
                               @RequestParam(value = "stateType", defaultValue = "") Integer stateType) {
        try {
            /// TODO 下面这段的代码太多if else，困了有空再用Optional改写
            User user = userService.selectByOpenid(openid);
            if (user != null) {
                Manager manager = managerService.selectById(user.getId());
                Sponsor sponsor = sponsorService.selectById(sponsorId);
                List<Tag> tagsList = new ArrayList<>();
                for (var item : tags) {
                    Tag tempTag = tagService.selectById(item);
                    if (tempTag != null) {
                        tagsList.add(tempTag);
                    }
                }
                if (manager != null && sponsor != null) {
                    Activity activity = new Activity(null, name, startTime, endTime, registerDeadline,
                            position, level, manager.getUser().getId(), sponsor, tagsList, description, content,
                            null, null, stateType);
                    if(activityService.insert(activity)>0){
                        return Result.success(null);
                    } else{
                        return Result.fail(null, 500, "Database update failed");
                    }
                } else if (manager == null && sponsor != null) {
                    return Result.fail(null, 5000, "Not a manager and don't have permission to post activity");
                } else {
                    return Result.fail(null, 5000, "The organization does not exist");
                }
            } else {
                return Result.fail(null, 5000, "This user does not exist");
            }
        } catch (Exception e){
            return Result.fail(null,6000,"Unknown exception");
        }
    }

    /**
     * 获取用户信息
     * @param openid
     * @return {@link Result}
     */
    @RequestMapping(value = "/manager/edit_activity", method = RequestMethod.POST)
    @ResponseBody
    public Result editActivity(@RequestParam(value = "openid", defaultValue = "") String openid,
                               @RequestParam(value = "id", defaultValue = "") Integer id,
                               @RequestParam(value = "name", defaultValue = "") String name,
                               @RequestParam(value = "startTime", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
                               @RequestParam(value = "endTime", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
                               @RequestParam(value = "registerDeadline", defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date registerDeadline,
                               @RequestParam(value = "position", defaultValue = "") String position,
                               @RequestParam(value = "level", defaultValue = "") String level,
                               @RequestParam(value = "sponsorName", defaultValue = "") Integer sponsorId,
                               @RequestParam(value = "tags", defaultValue = "") List<Integer> tags,
                               @RequestParam(value = "description", defaultValue = "") String description,
                               @RequestParam(value = "content", defaultValue = "") String content,
                               @RequestParam(value = "stateType", defaultValue = "") Integer stateType) {
        try {
            User user = userService.selectByOpenid(openid);
            Manager manager = managerService.selectById(user.getId());
            Activity activity = activityService.selectById(id);
            if (manager != null && activity != null) {
                Sponsor sponsor = sponsorService.selectById(sponsorId);
                List<Tag> tagsList = new ArrayList<>();
                for (var item : tags) {
                    Tag tempTag = tagService.selectById(item);
                    if (tempTag != null) {
                        tagsList.add(tempTag);
                    }
                }

                activity.setName(name);
                activity.setStartTime(startTime);
                activity.setEndTime(endTime);
                activity.setRegisterDeadline(registerDeadline);
                activity.setPosition(position);
                activity.setLevel(level);
                if (sponsor != null) {
                    activity.setSponsor(sponsor);
                }
                activity.setTags(tagsList);
                activity.setDescription(description);
                activity.setContent(content);
                activity.setStateType(stateType);

                if(activityService.update(activity) > 0 && sponsor != null){
                    return Result.success(null);
                } else if (activityService.update(activity) > 0 && sponsor != null){
                    return Result.success(null,200,"Organization does not exist, organization information has not been updated");
                } else {
                    return Result.fail(null, 500, "Database update failed");
                }

            }
        } catch (Exception e){
            return Result.fail(null,6000,"Unknown exception");
        }
        return Result.fail(null,6000,"Unknown exception");
    }
}
