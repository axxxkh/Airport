package flightMicroService.repository;

import flightMicroService.entity.Passport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassportRepository extends GenericJPARepository<Passport> {
    Optional<Passport> findBySerialNumber(String serialNumber);
}
