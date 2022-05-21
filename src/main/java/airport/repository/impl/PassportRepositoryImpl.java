package airport.repository.impl;

import airport.DAO.PassportDAO;
import airport.DAO.impl.PassportDAOImpl;
import airport.entity.Passport;
import airport.repository.PassportRepository;

import java.util.List;
import java.util.Optional;

public class PassportRepositoryImpl implements PassportRepository {
    private PassportDAO passportDAO = new PassportDAOImpl();

    @Override
    public Passport add(Passport passport) {
        return passportDAO.add(passport);
    }

    @Override
    public Optional<Passport> getById(int id) {
        return passportDAO.getById(id);
    }

    @Override
    public List<Passport> getAll() {
        return passportDAO.getAll();
    }

    @Override
    public List<Passport> getAllActive() {
        return passportDAO.getAllActive();
    }

    @Override
    public Passport update(Passport passport) {
        return passportDAO.update(passport);
    }

    @Override
    public boolean delete(Passport passport) {
        return passportDAO.delete(passport);
    }
}
