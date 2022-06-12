package personalMicroService.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import personalMicroService.dto.FlightDTO;
import personalMicroService.dto.PersonalDTO;
import personalMicroService.entity.Personal;
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

    @Override
    public PersonalDTO getByName(String name) {
       return mapper.map( personalRepository.findByName(name), PersonalDTO.class);
    }

    @Override
    public void delete(String name) {
        Personal personal = personalRepository.findByName(name);
        personalRepository.delete(personal);
    }

    @Override
    public PersonalDTO add(PersonalDTO personalDTO) {
        personalRepository.save(mapper.map(personalDTO,Personal.class));
        return null;
    }

    @Override
    public void update(PersonalDTO personalDTO) {
//        Personal personal = personalRepository.findByName(personalDTO.getName());
//        personal = mapper.map()
    }
}

