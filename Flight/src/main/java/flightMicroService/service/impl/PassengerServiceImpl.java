package flightMicroService.service.impl;

import flightMicroService.dto.PassengerDTO;
import flightMicroService.dto.PassportDTO;
import flightMicroService.entity.Passenger;
import flightMicroService.entity.Passport;
import flightMicroService.entity.Role;
import flightMicroService.entity.Ticket;
import flightMicroService.repository.PassengerRepository;
import flightMicroService.repository.PassportRepository;
import flightMicroService.repository.RoleRepository;
import flightMicroService.repository.TicketRepository;
import flightMicroService.service.PassengerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private TicketRepository ticketRepository;
    private RoleRepository roleRepository;

    public PasswordEncoder passwordEncoder;

    private ModelMapper mapper;

    @Override
    public Passenger getByLogin(String login) {
        System.out.println(passengerRepository.findByUsername(login));
        return passengerRepository.findByUsername(login);
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
        passenger.setRoles(roles);
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
        Passport passport = passportRepository.findBySerialNumber(passportDTO.getSerialNumber());
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
