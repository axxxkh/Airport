package com.flightService.service;

import com.flightService.dto.PassengerDTO;

import java.util.List;

public interface PassengerService {
    public List<PassengerDTO> getAllByEmail(String email);
}
