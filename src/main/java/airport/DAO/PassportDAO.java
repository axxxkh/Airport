package airport.DAO;

import airport.DAO.GenericDAO;
import airport.entity.Passport;

public interface PassportDAO  extends GenericDAO<Passport> {
    Passport getBySerialNumber (String serialNumber);
}
