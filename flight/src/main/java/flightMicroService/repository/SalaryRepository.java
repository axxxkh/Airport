package flightMicroService.repository;

import flightMicroService.entity.Salary;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends GenericJPARepository<Salary> {
}