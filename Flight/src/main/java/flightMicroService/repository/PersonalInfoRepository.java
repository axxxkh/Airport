package flightMicroService.repository;

import flightMicroService.entity.PersonalInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends GenericJPARepository<PersonalInfo> {
}
