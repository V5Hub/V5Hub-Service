package com.V5Hub.volunteerservice.service.impl;

import com.V5Hub.volunteerservice.mapper.RegisterMapper;
import com.V5Hub.volunteerservice.model.Register;
import com.V5Hub.volunteerservice.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WarmCongee
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public List<Register> selectAll() {
        return registerMapper.selectAll();
    }

    @Override
    public Register selectById(int id) {
        return registerMapper.selectById(id);
    }

    @Override
    public int deleteById(int id) {
        return registerMapper.deleteById(id);
    }

    @Override
    public int insert(Register register) {
        Register tempRegister = registerMapper.selectByUserIdActivityId(register.getUserId(),register.getActivity().getId());
        if(tempRegister == null){
            return registerMapper.insert(register);
        } else {
            register.setId(tempRegister.getId());
            return registerMapper.update(register);
        }
    }

    @Override
    public int insert(List<Register> registers) {
        int count = 0;
        for(var register : registers){
            count += insert(register);
        }
        return count;
    }

    @Override
    public int update(Register register) {
        return registerMapper.update(register);
    }
}
