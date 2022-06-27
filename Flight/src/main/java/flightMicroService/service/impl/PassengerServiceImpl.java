package flightMicroService.service.impl;

import flightMicroService.dto.PassengerDTO;
import flightMicroService.dto.PassportDTO;
import flightMicroService.entity.Passenger;
import flightMicroService.entity.Passport;
import flightMicroService.entity.Role;
import flightMicroService.entity.Ticket;
import flightMicroService.exceptions.EntityNotFoundException;
import flightMicroService.repository.PassengerRepository;
import flightMicroService.repository.PassportRepository;
import flightMicroService.repository.RoleRepository;
import flightMicroService.repository.TicketRepository;
import flightMicroService.service.PassengerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private PassengerRepository passengerRepository;
    private PassportRepository passportRepository;
    private TicketRepository ticketRepository;
    private RoleRepository roleRepository;
    public PasswordEncoder passwordEncoder;

    private ModelMapper mapper;

    @Override
    public Passenger getByLogin(String login) {
        return passengerRepository.findByUsername(login)
                .orElseThrow(() -> new EntityNotFoundException("Passenger with login " + login + " doesn't exist"));
    }

    @Override
    public List<PassengerDTO> getAll() {
        return passengerRepository.findAll()
                .stream()
                .map(p -> mapper.map(p, PassengerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PassengerDTO getPassengerByPassportNumber(String passportNumber) {
        Passport passport = passportRepository.findBySerialNumber(passportNumber)
                .orElseThrow(() -> new EntityNotFoundException("Passport with serial number " + passportNumber + "doesn't exist"));
        return mapper.map(passport.getPassenger(), PassengerDTO.class);
    }

    @Override
    public PassengerDTO update(PassengerDTO passengerDTO) {
        List<Passport> passports = passengerDTO.getPassports().stream()
                .map(passport -> mapper.map(passport, Passport.class))
                .collect(Collectors.toList());

        Passenger passenger = mapper.map(passengerDTO, Passenger.class);
        List<Role> roles = passengerDTO.getRoles().stream().map(roleDTO -> roleRepository.findByRole(roleDTO.getRole())).collect(Collectors.toList());
        Passenger passengerFromDB = identify(passengerDTO);
        passenger.setId(passengerFromDB.getId());
        passenger.setTickets(passengerFromDB.getTickets());

        passports.stream().forEach(p -> p.setId(passportRepository
                .findBySerialNumber(p.getSerialNumber())
                .orElseThrow(() -> new EntityNotFoundException("Passport with serial number " + p.getSerialNumber() + "doesn't exist")).getId()));

        passenger.setPassports(passports);
        passports.forEach(passport -> passport.setPassenger(passenger));
        passenger.setRoles(new HashSet<>(roles));
        passenger.setPassword(passwordEncoder.encode(passengerDTO.getPassword()));
        passengerRepository.saveAndFlush(passenger);
        passportRepository.saveAllAndFlush(passports);
        return mapper.map(passenger, PassengerDTO.class);
    }

    @Override
    public PassengerDTO add(PassengerDTO passengerDTO) {
        List<Passport> passports = passengerDTO.getPassports().stream()
                .map(passport -> mapper.map(passport, Passport.class))
                .collect(Collectors.toList());
        Passenger passenger = mapper.map(passengerDTO, Passenger.class);
        List<Role> roles = passengerDTO.getRoles().stream().map(roleDTO -> roleRepository.findByRole(roleDTO.getRole())).collect(Collectors.toList());
        passenger.setPassports(passports);
        passports.forEach(passport -> passport.setPassenger(passenger));
        passenger.setRoles(new HashSet<>(roles));
        passenger.setPassword(passwordEncoder.encode(passengerDTO.getPassword()));
        passengerRepository.save(passenger);
        passportRepository.saveAll(passports);
        return mapper.map(passenger, PassengerDTO.class);
    }


    @Override
    public void delete(PassengerDTO passengerDTO) {
        Passenger passenger = identify(passengerDTO);
        List<Ticket> ticketList = ticketRepository.findTicketsByPassengerId(passenger.getId());
        ticketRepository.deleteAll(ticketList);
        passportRepository.deleteAll(passenger.getPassports());
        passengerRepository.delete(passenger);
    }

    @Override
    public Passenger identify(PassengerDTO passengerDTO) {
        Queue<PassportDTO> passportList = new LinkedList<>(passengerDTO.getPassports());
        PassportDTO passportDTO = passportList.peek();
        assert passportDTO != null;
        Passport passport = passportRepository
                .findBySerialNumber(passportDTO.getSerialNumber())
                .orElseThrow(() -> new EntityNotFoundException("Passport with serial number " + passportDTO.getSerialNumber() + " doesn't exist"));
        return passport.getPassenger();
    }
}
