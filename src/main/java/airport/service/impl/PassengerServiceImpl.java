package airport.service.impl;

import airport.Repository.PassengerRepository;
import airport.Repository.PassportRepository;
import airport.entity.Passenger;
import airport.entity.Passport;
import airport.service.PassengerSpringService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PassengerServiceImpl implements PassengerSpringService {

    @Autowired
    private PassengerRepository passengerRepository;
    private PassportRepository passportRepository;


    @Override
    public Passenger add(Passenger passenger) {
        return passengerRepository.saveAndFlush(passenger);
    }

    @Override
    public void delete(Passenger passenger) {
        passenger.setActive(false);
        passengerRepository.saveAndFlush(passenger);
    }

    @Override
    public Passenger identify(Passport passport) {
        return null;
    }

    @Override
    public Passenger getPassengerByPassportNumber(String passportNumber) {
        Passport passport = passportRepository.findBySerialNumber(passportNumber);
        return passport.getPassenger();
//return null;
    }

    @Override
    public Passenger getPassengerByPassport(Passport passport) {
//        passengerRepository.findBy(passport,p->p.s)

        return null;
    }

    @Override
    public List<Passenger> getAll() {
        return null;
    }
}
