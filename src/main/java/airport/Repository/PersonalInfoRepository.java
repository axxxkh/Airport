package airport.Repository;

import airport.entity.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends GenericJPARepository<PersonalInfo> {
}
