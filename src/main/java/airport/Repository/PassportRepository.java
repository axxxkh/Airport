package airport.Repository;

import airport.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface PassportRepository extends JpaRepository<Passport, Integer> {
    Passport findBySerialNumber(@Param("serialNumber") String serialNumber);
}
