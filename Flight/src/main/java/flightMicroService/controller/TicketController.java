package flightMicroService.controller;

import flightMicroService.dto.PassengerDTO;
import flightMicroService.dto.TicketDTO;
import flightMicroService.service.FlightService;
import flightMicroService.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {

    private TicketService ticketService;
    private FlightService flightService;

    /* RequestBody
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

        Requestparam - int flightNumber
*/
    @PostMapping("/buy/ticket/")
    public ResponseEntity<TicketDTO> buyTicket(@RequestBody PassengerDTO passengerDTO, @RequestParam int flightNumber) {
        return new ResponseEntity<>(ticketService.buyTicket(passengerDTO, flightNumber), HttpStatus.CREATED);
    }

    @GetMapping("/get/availableTickets/")
    public List<TicketDTO> getAllTickets() {
        return ticketService.getAllAvailableTickets();
    }

    //    http://localhost:8081/ticket/getAllBuyedTicketsByPeriod/?startDate=2019-05-01&endDate=2022-05-05
    @GetMapping("/get/tickets/bought/period")
    public List<TicketDTO> getAllBoughtTicketsByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ticketService.getAllBuyedTicketsByPeriod(startDate, endDate);

    }
}
