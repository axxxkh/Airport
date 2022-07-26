package com.flightService.repository;

import com.flightService.entity.PersonalInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends GenericJPARepository<PersonalInfo> {
}
