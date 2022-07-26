package com.flightService.repository;

import com.flightService.entity.Salary;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends GenericJPARepository<Salary> {
}
