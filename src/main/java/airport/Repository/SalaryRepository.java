package airport.Repository;

import airport.entity.Salary;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends GenericJPARepository<Salary> {
}
