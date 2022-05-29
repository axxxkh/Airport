package airport.service.impl;

import airport.entity.Passenger;
import airport.entity.Passport;
import airport.repositoryDAO.PassengerRepositoryDAO;
import airport.repositoryDAO.PassportRepositoryDAO;
import airport.repositoryDAO.impl.PassportRepositoryImplDAO;
import airport.service.PassengerEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengerDAOServiceImpl implements PassengerEntityService {

    @Autowired
    private PassengerRepositoryDAO passengerRepository;
    //    @Autowired
    private final PassportRepositoryDAO passportRepository = new PassportRepositoryImplDAO();

    @Override
    public String addPassenger(Passenger passenger) {
        passengerRepository.add(passenger);
        return "Success";
    }

    @Override
    public Passenger identify(Passport passport) {
        Passport passportDB = passportRepository.getBySerialNumber(passport.getSerialNumber());
        Optional<Passenger> passenger;
        passenger = passengerRepository.getByPassport(passport);

        if (passport.equals(passportDB) && passenger.isPresent()) {
            return passenger.orElseThrow();
        }
        return null;
    }

    @Override
    public Passenger getPassengerByPassportNumber(String passportNumber) {
        Passport passport = passportRepository.getBySerialNumber(passportNumber);
        return passport.getPassenger();
    }
}
