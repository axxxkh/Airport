package personalMicroService.repository;

import personalMicroService.entity.PersonalInfo;
import org.springframework.stereotype.Repository;
import personalMicroService.entity.PersonalInfo;

@Repository
public interface PersonalInfoRepository extends GenericJPARepository<PersonalInfo> {
}
