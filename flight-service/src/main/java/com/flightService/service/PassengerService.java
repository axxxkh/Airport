package com.flightService.service;

import com.flightService.dto.PassengerDTO;

import java.util.List;

public interface PassengerService {
    List<PassengerDTO> getAllByEmail(String email);
}
