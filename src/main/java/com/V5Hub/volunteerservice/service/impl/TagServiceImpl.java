package com.V5Hub.volunteerservice.service.impl;

import com.V5Hub.volunteerservice.mapper.ActivityTagMapper;
import com.V5Hub.volunteerservice.mapper.SubscribeTagMapper;
import com.V5Hub.volunteerservice.mapper.TagMapper;
import com.V5Hub.volunteerservice.model.Tag;
import com.V5Hub.volunteerservice.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author KirCute
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> selectAll() {
        return tagMapper.selectAll();
    }

    @Override
    public Tag selectById(int id) {
        return tagMapper.selectById(id);
    }
}
