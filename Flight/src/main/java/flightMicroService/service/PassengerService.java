package flightMicroService.service;

import flightMicroService.dto.PassengerDTO;
import flightMicroService.entity.Passenger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PassengerService {
    String add(PassengerDTO passengerDTO);

    void delete(PassengerDTO passengerDTO);

    Passenger identify(PassengerDTO passengerDTO);

    PassengerDTO getPassengerByPassportNumber(String passportNumber);

    List<PassengerDTO> getAll();

}
