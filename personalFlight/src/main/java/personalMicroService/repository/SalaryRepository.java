package personalMicroService.repository;

import org.springframework.stereotype.Repository;
import personalMicroService.entity.Salary;

@Repository
public interface SalaryRepository extends GenericJPARepository<Salary> {
}
