package personalMicroService.repository;

import personalMicroService.entity.Personal;

//@Repository
public interface PersonalRepository extends GenericJPARepository<Personal> {
    Personal findByName(String name);
}
