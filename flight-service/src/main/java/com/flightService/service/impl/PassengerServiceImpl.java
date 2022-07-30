package com.flightService.service.impl;

import com.flightService.dto.PassengerDTO;
import com.flightService.repository.PassengerRepository;
import com.flightService.service.PassengerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private PassengerRepository passengerRepository;
    private ModelMapper mapper;

    @Override
    public List<PassengerDTO> getAll(String id) {
        return passengerRepository.getByUser(id)
                .stream()
                .map(passenger -> mapper.map(passenger, PassengerDTO.class))
                .collect(Collectors.toList());
    }
}
