package airport.service.impl;

import airport.entity.Passenger;
import airport.entity.Passport;
import airport.repository.PassengerRepository;
import airport.repository.PassportRepository;
import airport.repository.impl.PassportRepositoryImpl;
import airport.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;
    //    @Autowired
    private final PassportRepository passportRepository = new PassportRepositoryImpl();

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
