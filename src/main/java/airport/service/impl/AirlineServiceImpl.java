package airport.service.impl;

import airport.dto.AirlineDTO;
import airport.repository.AirlineRepository;
import airport.entity.Airline;
import airport.service.AirlineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private AirlineRepository airlineRepository;

    @Override
    public void delete(AirlineDTO airlineDTO) {
        Airline airline = airlineRepository.findByName(airlineDTO.getName());
        airline.setActive(false);
//airlineRepository.f
    }
}
