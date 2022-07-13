package personalMicroService.repository;

import personalMicroService.entity.Salary;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends GenericJPARepository<Salary> {
}
