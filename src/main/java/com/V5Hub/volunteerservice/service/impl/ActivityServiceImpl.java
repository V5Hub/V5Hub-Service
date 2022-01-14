package com.V5Hub.volunteerservice.service.impl;

import com.V5Hub.volunteerservice.mapper.ActivityMapper;
import com.V5Hub.volunteerservice.mapper.ActivityTagMapper;
import com.V5Hub.volunteerservice.model.Activity;
import com.V5Hub.volunteerservice.model.Tag;
import com.V5Hub.volunteerservice.model.User;
import com.V5Hub.volunteerservice.service.ActivityService;
import com.V5Hub.volunteerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author WarmCongee
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> selectAll() {
        return activityMapper.selectAll();
    }

    @Override
    public Activity selectById(int id) {
        return activityMapper.selectById(id);
    }

    @Override
    public List<Activity> selectByDate(Date startTime, Date endTime) {
        return activityMapper.selectByDate(startTime, endTime);
    }

    @Override
    public int deleteById(int id) {
        return activityMapper.deleteById(id);
    }

    @Override
    public int insert(Activity activity) {
        return activityMapper.insert(activity);
    }

    @Override
    public int insert(List<Activity> activities) {
        int count = 0;
        for(var activity : activities){
            count += insert(activity);
        }
        return count;
    }

    @Override
    public int update(Activity activity) {
        // TODO: 记得对tag的修改进行处理
        return update(activity);
    }

    @Override
    public HashMap<String, Object> parseSimpleData(Activity activity, @Nullable User user) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("id", activity.getId());
        res.put("name", activity.getName());
        res.put("startTime", activity.getStartTime());
        res.put("endTime", activity.getEndTime());
        res.put("registerDeadline", activity.getRegisterDeadline());
        res.put("position", activity.getPosition());
        res.put("description", activity.getDescription());
        res.put("stateType", activity.getStateType());

        HashMap<String, Object> sponsor = new HashMap<>();
        sponsor.put("id", activity.getSponsor().getId());
        sponsor.put("name", activity.getSponsor().getName());
        res.put("sponsor", sponsor);

        ArrayList<HashMap<String, Object>> tagArray = new ArrayList<>();
        for (Tag tag : activity.getTags()) {    // TODO: 只保留（用户更关注的）前三个tag
            HashMap<String, Object> tagMap = new HashMap<>();
            tagMap.put("text", tag.getText());
            tagMap.put("subscribe", user != null && user.getSubscribedTags().contains(tag));
            tagArray.add(tagMap);
        }
        res.put("tags", tagArray);

        return res;
    }

    @Override
    public HashMap<String, Object> parseDetailsData(Activity activity, @Nullable User user) {
        HashMap<String, Object> res = this.parseSimpleData(activity, user);
        res.put("content", activity.getContent());
        return res;
    }
}
