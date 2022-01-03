package com.V5Hub.volunteerservice.service.impl;

import com.V5Hub.volunteerservice.mapper.ManagerMapper;
import com.V5Hub.volunteerservice.module.Manager;
import com.V5Hub.volunteerservice.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author WarmCongee
 */
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public List<Manager> selectAll() {
        return managerMapper.selectAll();
    }

    @Override
    public Manager selectById(int id) {
        return managerMapper.selectById(id);
    }

    @Override
    public int deleteById(int id) {
        return managerMapper.deleteById(id);
    }

    @Override
    public int insert(Manager manager) {
        return managerMapper.insert(manager);
    }

    @Override
    public int insert(List<Manager> managers) {
        int count = 0;
        for(var manager : managers){
            count += insert(manager);
        }
        return count;
    }

    @Override
    public int update(Manager manager) {
        return -1;
    }
}
