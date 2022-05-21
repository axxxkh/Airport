package airport.repository.impl;

import airport.DAO.impl.PersonalInfoDAOImpl;
import airport.entity.PersonalInfo;
import airport.repository.PersonalInfoRepository;

import java.util.List;
import java.util.Optional;

public class PersonalInfoRepositoryImpl implements PersonalInfoRepository {
    private PersonalInfoDAOImpl personalInfoDAO;

    @Override
    public PersonalInfo add(PersonalInfo personalInfo) {
        return personalInfoDAO.add(personalInfo);
    }

    @Override
    public Optional<PersonalInfo> getById(int id) {
        return personalInfoDAO.getById(id);
    }

    @Override
    public List<PersonalInfo> getAll() {
        return personalInfoDAO.getAll();
    }

    @Override
    public List<PersonalInfo> getAllActive() {
        return personalInfoDAO.getAllActive();
    }

    @Override
    public PersonalInfo update(PersonalInfo personalInfo) {
        return personalInfoDAO.update(personalInfo);
    }

    @Override
    public boolean delete(PersonalInfo personalInfo) {
        return personalInfoDAO.delete(personalInfo);
    }
}
