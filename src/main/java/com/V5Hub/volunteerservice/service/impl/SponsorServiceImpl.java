package com.V5Hub.volunteerservice.service.impl;

import com.V5Hub.volunteerservice.mapper.ManagerSponsorMapper;
import com.V5Hub.volunteerservice.mapper.SponsorMapper;
import com.V5Hub.volunteerservice.model.Sponsor;
import com.V5Hub.volunteerservice.service.SponsorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WarmCongee
 * @date 2022/1/20 13:20
 */
@Service
public class SponsorServiceImpl implements SponsorService {
    private final SponsorMapper sponsorMapper;
    private final ManagerSponsorMapper managerSponsorMapper;

    @Autowired
    public SponsorServiceImpl(SponsorMapper sponsorMapper, ManagerSponsorMapper managerSponsorMapper){
        this.sponsorMapper = sponsorMapper;
        this.managerSponsorMapper =managerSponsorMapper;
    }

    @Override
    public Sponsor selectById(Integer sponsorId){
        return sponsorMapper.selectById(sponsorId);
    }

}
