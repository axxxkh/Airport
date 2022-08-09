package com.flightService.service.impl;

import com.flightService.dto.FlightDTO;
import com.flightService.repository.FlightRepository;
import com.flightService.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
                .map(flight -> mapper.map(flight, FlightDTO.class))
                .collect(Collectors.toList());
    }
}
