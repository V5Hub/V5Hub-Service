package com.V5Hub.volunteerservice.service.impl;

import com.V5Hub.volunteerservice.mapper.ActivityMapper;
import com.V5Hub.volunteerservice.model.Activity;
import com.V5Hub.volunteerservice.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        return update(activity);
    }
}
