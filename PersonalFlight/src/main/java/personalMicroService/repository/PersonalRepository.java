package personalMicroService.repository;

import org.springframework.stereotype.Repository;
import personalMicroService.dto.PersonalDTO;
import personalMicroService.entity.Personal;

//@Repository
public interface PersonalRepository extends GenericJPARepository<Personal> {
    Personal findByName(String name);
}
