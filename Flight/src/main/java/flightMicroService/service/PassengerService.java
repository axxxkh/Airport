package flightMicroService.service;

import flightMicroService.dto.PassengerDTO;
import flightMicroService.entity.Passenger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PassengerService {
    PassengerDTO add(PassengerDTO passengerDTO);

    void delete(PassengerDTO passengerDTO);

    Passenger identify(PassengerDTO passengerDTO);

    Passenger getByLogin(String login);

    PassengerDTO getPassengerByPassportNumber(String passportNumber);

    List<PassengerDTO> getAll();

}
