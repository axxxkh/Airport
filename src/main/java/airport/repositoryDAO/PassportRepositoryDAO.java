package airport.repositoryDAO;

import airport.entity.Passport;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepositoryDAO extends GenericRepositoryDAO<Passport> {
    Passport getBySerialNumber(String serialNumber);
}
