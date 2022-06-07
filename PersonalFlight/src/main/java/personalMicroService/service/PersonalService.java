package personalMicroService.service;

import org.springframework.stereotype.Service;
import personalMicroService.dto.PersonalDTO;

import java.util.List;

@Service
public interface PersonalService {
    List<PersonalDTO> getAll();

    String fromAnotherModule();
}
