package airport.controller;

import airport.DTO.PassengerDTO;
import airport.entity.Passenger;
import airport.service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")

@AllArgsConstructor
public class PassengerController {

    private PassengerService passengerSpringService;

    @GetMapping("/get/")
    public Passenger getPassenger() {
        return Passenger.builder().build();
    }

    @GetMapping("/get/passport/")
    public String getPassengerByPassport(@RequestParam String passportSerialNumber) {
        return passengerSpringService.getPassengerByPassportNumber(passportSerialNumber).toString();
    }

    /*
    localhost:8081/passenger/add
    RequestBody
    {
            "name": "name",
                "surname": "surname",
                "passports": [
            {
                "serialNumber": "11sffsd3",
                    "birthdate": "1987-12-28",
                    "issueDate": "2000-10-23",
                    "passportType": "UAinternational"
            }
                    ]
        }
        */
    @PostMapping("/add/")
    public String add(@RequestBody PassengerDTO passengerDTO) {
        return passengerSpringService.add(passengerDTO);
    }
}
