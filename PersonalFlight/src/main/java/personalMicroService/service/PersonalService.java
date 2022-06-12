package personalMicroService.service;

import org.springframework.stereotype.Service;
import personalMicroService.dto.PersonalDTO;

import java.util.List;

@Service
public interface PersonalService {
    List<PersonalDTO> getAll();

    PersonalDTO getByName(String name);

    String fromAnotherModule();

    void delete(String name);

    PersonalDTO add(PersonalDTO personalDTO);

    void update(PersonalDTO personalDTO);
}
