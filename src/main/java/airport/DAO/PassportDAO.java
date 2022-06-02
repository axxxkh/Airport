package airport.DAO;

import airport.entity.Passport;

public interface PassportDAO extends GenericDAO<Passport> {
    Passport getBySerialNumber(String serialNumber);
}
