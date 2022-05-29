package airport.service;

import airport.entity.Passenger;
import airport.entity.Passport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PassengerSpringService {
    Passenger add(Passenger passenger);

    void delete(Passenger passenger);

    Passenger identify(Passport passport);

    Passenger getPassengerByPassportNumber(String passportNumber);

    Passenger getPassengerByPassport(Passport passport);

    List<Passenger> getAll ();

}
