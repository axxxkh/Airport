package com.flightService.service.impl;

import com.flightService.dto.FlightDTO;
import com.flightService.entity.Flight;
import com.flightService.exceptions.FlightException;
import com.flightService.repository.FlightRepository;
import com.flightService.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Data
public class FlightServiceImpl implements FlightService {
    private FlightRepository flightRepository;
    private ModelMapper mapper;

    @Override
    public List<FlightDTO> getAll() {
        return flightRepository.findAll()
                .stream()
                .filter(f -> f.getTime().isAfter(ChronoLocalDateTime.from(LocalDate.now())))
                .map(flight -> mapper.map(flight, FlightDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightDTO> getFlightsByPeriod(LocalDate startDate, LocalDate endDate) {
        return flightRepository
                .findByActiveTrue()
                .stream()
                .filter(f -> f.getTime().toLocalDate().isAfter(startDate)
                        && f.getTime().toLocalDate().isBefore(endDate))
                .map(f -> mapper.map(f, FlightDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FlightDTO addFlight(FlightDTO flightDTO) throws FlightException {

        Flight flight = mapper.map(flightDTO, Flight.class);
        Optional<Flight> flightFromDB = flightRepository.findByFlightNumber(flight.getFlightNumber());
        if (flightFromDB.isEmpty()) {
            return mapper.map(Optional.of(flightRepository.save(flight)), FlightDTO.class);
        } else {
            throw new FlightException("Flight with number " + flightDTO.getFlightNumber() + " already exist");
        }
    }
}
