package airport.service;

import airport.entity.Passenger;
import airport.entity.Passport;

public interface PassengerEntityService {
    String addPassenger(Passenger passenger);

    Passenger identify(Passport passport);

    Passenger getPassengerByPassportNumber(String passportNumber);

}
