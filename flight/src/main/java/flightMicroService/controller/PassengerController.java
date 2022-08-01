package flightMicroService.controller;

import flightMicroService.dto.PassengerDTO;
import flightMicroService.service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/passenger")

@AllArgsConstructor
public class PassengerController {

    private PassengerService passengerService;

    @GetMapping("/")
    public ResponseEntity<List<PassengerDTO>> getAll() {
        return new ResponseEntity<>(passengerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<PassengerDTO> findBySerialNumber(@PathVariable String serialNumber) {
        return new ResponseEntity<>(passengerService.getPassengerByPassportNumber(serialNumber), HttpStatus.OK);
    }

    /*
    localhost:8081/passenger/add
    RequestBody
    {
    "name": "Bohdan",
    "surname": "Shchehliuk",
    "passports": [
        {
            "serialNumber": "AAXXXXF222",
            "birthdate": "1987-12-28",
            "issueDate": "2000-10-23",
            "passportType": "UAinternational"
        }
    ],
    "login": "bohdan@gmail.com",
    "pwd": "123",
    "roles": [
        {
            "role": "PASSENGER"
        }
    ]
}
        */
    @PostMapping("/")
    public ResponseEntity<PassengerDTO> add(@RequestBody @Valid PassengerDTO passengerDTO) {
        return new ResponseEntity<PassengerDTO>(passengerService.add(passengerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<PassengerDTO> update(@RequestBody @Valid PassengerDTO passengerDTO) {
        return new ResponseEntity<PassengerDTO>(passengerService.update(passengerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deletePassenger(@RequestBody @Valid PassengerDTO passengerDTO) {
        passengerService.delete(passengerDTO);
        return new ResponseEntity<>("ds", HttpStatus.ACCEPTED);
    }

}
