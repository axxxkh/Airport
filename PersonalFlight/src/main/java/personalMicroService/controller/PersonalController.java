package personalMicroService.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import personalMicroService.dto.PersonalDTO;
import personalMicroService.service.PersonalService;

import java.util.List;

@RestController
@AllArgsConstructor
public class PersonalController {
    private PersonalService personalService;

    @GetMapping("/get/")
    public List<PersonalDTO> getAll(){
        return personalService.getAll();
    }

    @GetMapping("/flight/")
    public String getFlight(){
        return personalService.fromAnotherModule();
    }
}
