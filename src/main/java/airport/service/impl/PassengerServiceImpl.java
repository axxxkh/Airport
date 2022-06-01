package airport.service.impl;

import airport.DTO.PassengerDTO;
import airport.DTO.PassportDTO;
import airport.Repository.PassengerRepository;
import airport.Repository.PassportRepository;
import airport.entity.Passenger;
import airport.entity.Passport;
import airport.service.PassengerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private PassengerRepository passengerRepository;
    private PassportRepository passportRepository;
    private ModelMapper mapper;

    @Override
    public String add(PassengerDTO passengerDTO) {
        passengerRepository.saveAndFlush(mapper.map(passengerDTO, Passenger.class));
        return "Success";
    }

    @Override
    public void delete(PassengerDTO passengerDTO) {
        Passenger passenger = identify(passengerDTO);
        passengerRepository.delete(passenger);
    }

    @Override
    public Passenger identify(PassengerDTO passengerDTO) {
        Queue<PassportDTO> passportList = new LinkedList<PassportDTO>(passengerDTO.getPassports());
        PassportDTO passportDTO = passportList.peek();
        assert passportDTO != null;
        Passport passport = passportRepository.findBySerialNumber(passportDTO.getSerialNumber());
        System.out.println(passport);
        return passport.getPassenger();
    }

    @Override
    public PassengerDTO getPassengerByPassportNumber(String passportNumber) {
        Passport passport = passportRepository.findBySerialNumber(passportNumber);
        return mapper.map(passport.getPassenger(), PassengerDTO.class);
    }


    @Override
    public List<PassengerDTO> getAll() {
        return passengerRepository.findAll()
                .stream()
                .map(p -> mapper.map(p, PassengerDTO.class))
                .collect(Collectors.toList());
    }
}
