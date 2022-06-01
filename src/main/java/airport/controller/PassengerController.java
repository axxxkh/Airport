package airport.controller;

import airport.DTO.PassengerDTO;
import airport.entity.Passenger;
import airport.service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")

@AllArgsConstructor
public class PassengerController {

    private PassengerService passengerService;

//    @GetMapping("/get/")
//    public Passenger getPassenger() {
//        return Passenger.builder().build();
//    }

    @GetMapping("/get/passport/")
    public PassengerDTO getPassengerByPassport(@RequestParam String passportSerialNumber) {
        return passengerService.getPassengerByPassportNumber(passportSerialNumber);
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
        return passengerService.add(passengerDTO);
    }

    @PostMapping("/delete/")
    public ResponseEntity<String> deletePassenger (@RequestBody PassengerDTO passengerDTO) {
        passengerService.delete(passengerDTO);
        return new ResponseEntity<String>("ds", HttpStatus.ACCEPTED);
    }

}
