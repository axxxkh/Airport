package airport.repository;

import airport.entity.Passport;

public interface PassportRepository extends GenericJPARepository<Passport> {
    Passport findBySerialNumber(String serialNumber);
}
