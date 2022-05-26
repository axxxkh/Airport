package airport.controller;

import airport.DTO.PassengerDTO;
import airport.entity.Passenger;
import airport.service.PassengerService;
import airport.service.PassportService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")

@AllArgsConstructor
public class PassengerController {

    private ModelMapper modelMapper;
    private PassengerService passengerService;
    private PassportService passportService;

    @GetMapping("/get/")
    public Passenger getPassenger() {
        return Passenger.builder().build();
    }

    @GetMapping("/getByPassport/")
    public PassengerDTO getPassengerByPassport(@RequestParam String passportSerialNumber) {
        Passenger passenger = passengerService.getPassengerByPassportNumber(passportSerialNumber);
        return modelMapper.map(passenger, PassengerDTO.class);
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
        return passengerService.addPassenger(modelMapper.map(passengerDTO, Passenger.class));
    }
}
