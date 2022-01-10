package com.V5Hub.volunteerservice.service.impl;

import com.V5Hub.volunteerservice.mapper.ManagerMapper;
import com.V5Hub.volunteerservice.model.Manager;
import com.V5Hub.volunteerservice.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WarmCongee
 */
@Service
public class ManagerServiceImpl implements ManagerService {


    private ManagerMapper managerMapper;

    @Autowired
    public ManagerServiceImpl(ManagerMapper managerMapper){
        this.managerMapper = managerMapper;
    }


    @Override
    public List<Manager> selectAll() {
        return managerMapper.selectAll();
    }

    @Override
    public Manager selectById(String id) {
        return managerMapper.selectById(id);
    }

    @Override
    public int deleteById(String id) {
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
