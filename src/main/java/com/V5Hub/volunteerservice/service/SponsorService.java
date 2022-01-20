package com.V5Hub.volunteerservice.service;

import com.V5Hub.volunteerservice.model.Register;
import com.V5Hub.volunteerservice.model.Sponsor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 组织Sponsor相关业务操作的接口
 *
 * @version 1.0
 */
@Repository
public interface SponsorService {

    /**
     * 获得对应id的Sponsor
     *
     * @return {@link Sponsor}
     */
    Sponsor selectById(Integer sponsorId);
}
