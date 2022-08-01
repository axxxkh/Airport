package flightMicroService.service.impl;

import flightMicroService.dto.AirlineDTO;
import flightMicroService.entity.Airline;
import flightMicroService.exceptions.EntityNotFoundException;
import flightMicroService.repository.AirlineRepository;
import flightMicroService.service.AirlineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private AirlineRepository airlineRepository;

    @Override
    public void delete(AirlineDTO airlineDTO) {
        Airline airline = airlineRepository.findByName(airlineDTO.getName())
                .orElseThrow(() -> new EntityNotFoundException("Airline " + airlineDTO.getName() + "doesn`t exist"));
        airlineRepository.delete(airline);
    }
}
