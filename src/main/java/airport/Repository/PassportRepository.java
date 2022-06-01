package airport.Repository;

import airport.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PassportRepository extends GenericJPARepository<Passport> {
    Passport findBySerialNumber(String serialNumber);
}
