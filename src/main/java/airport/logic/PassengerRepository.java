package airport.logic;

import airport.DAO.Impl.PassengerDAOImpl;
import airport.DAO.Impl.PassportDAOImpl;
import airport.DAO.PassengerDAO;
import airport.DAO.PassportDAO;
import airport.entity.Passenger;
import airport.entity.Passport;

import java.util.Optional;

public class PassengerRepository {
    public Passenger identify(Passport passport) {
        PassportDAO passportDAO = new PassportDAOImpl();
        Passport passportDB = passportDAO.getBySerialNumber(passport.getSerialNumber());
        Optional<Passenger> passenger;
        PassengerDAO passengerDAO = new PassengerDAOImpl();
        passenger = passengerDAO.getByPassport(passport);

        if (passport.equals(passportDB) && passenger.isPresent()) {
            return passenger.orElseThrow();
        }
        return null;
    }

    //тут знову виникає питання. де зберігати імя і прізвище. якщо в паспорті то все простіше.
    // але тоді клас і таблиця пасажирів окрім ід та паспорту нічого наче і немає а лише служить для
//    поєднання паспортів. Короче питання дискусійне
//    public Passenger registerNewPassenger (Passport passport) {
//
//    }

//    тут питання в який репозиторій це класти. пасажира. рейса. квитка і тд
//    public Passenger registerPassengerOnFlight (Ticket ticket) {
//
//    }
}
