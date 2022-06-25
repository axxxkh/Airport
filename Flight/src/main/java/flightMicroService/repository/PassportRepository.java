package flightMicroService.repository;

import flightMicroService.entity.Passport;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepository extends GenericJPARepository<Passport> {
    Passport findBySerialNumber(String serialNumber);
}
