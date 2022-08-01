package personalMicroService.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import personalMicroService.dto.PersonalDTO;
import personalMicroService.service.PersonalService;

import java.util.List;

@RestController
@RequestMapping("/personal")
@AllArgsConstructor
public class PersonalController {
    private PersonalService personalService;

    @GetMapping("/")
    public List<PersonalDTO> getAll() {
        return personalService.getAll();
    }

    @GetMapping("/{name}")
    public PersonalDTO getByName(@PathVariable String name) {
        return personalService.getByName(name);
    }

    @DeleteMapping("/{name}")
    public void deleteByName(@PathVariable String name) {
        personalService.delete(name);
    }

    @PostMapping("/")
    public void addPersonal(@RequestBody PersonalDTO personalDTO) {
        personalService.add(personalDTO);
    }

//    @PutMapping("/personal/{name}")
//    public void updatePersonal(@PathVariable("name") String name) {
//        personalService
//    }

    @GetMapping("/flight/")
    public String getFlight() {
        return personalService.fromAnotherModule();
    }
}
