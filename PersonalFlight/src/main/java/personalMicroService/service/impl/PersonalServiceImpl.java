package personalMicroService.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import personalMicroService.dto.FlightDTO;
import personalMicroService.dto.PersonalDTO;
import personalMicroService.repository.PersonalRepository;
import personalMicroService.service.PersonalService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PersonalServiceImpl implements PersonalService {

    private RestTemplate template;
    private PersonalRepository personalRepository;
    private ModelMapper mapper;

    @Override
    public List<PersonalDTO> getAll() {
        return personalRepository.findAll().stream()
                .map(personal -> mapper.map(personal, PersonalDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public String fromAnotherModule() {
        ResponseEntity<FlightDTO> response = null;
        response = template.getForEntity("http://localhost:8082/flight/get/number/?flightNumber=11", FlightDTO.class);
        return response.toString();
    }
}
