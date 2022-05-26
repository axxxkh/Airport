package airport.controller;

import airport.DTO.PassengerDTO;
import airport.DTO.TicketDTO;
import airport.entity.Passenger;
import airport.entity.Passport;
import airport.repository.PassengerRepository;
import airport.repository.impl.PassengerRepositoryImpl;
import airport.service.FlightService;
import airport.service.TicketService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {

    private ModelMapper modelMapper;
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
    @PostMapping("/buyTicket/")
    public String buyTicket(@RequestBody PassengerDTO passengerDTO, @RequestParam int flightNumber) {
        Passenger passenger = modelMapper.map(passengerDTO, Passenger.class);
        ticketService.buyTicket(passenger, flightNumber);
        return null;
    }

    @GetMapping("/getAllAvailableTickets/")
    public List<TicketDTO> getAllTickets() {
        return ticketService.getAllAvailableTickets().stream().map(ticket -> modelMapper.map(ticket, TicketDTO.class)).collect(Collectors.toList());
    }

    //    http://localhost:8081/ticket/getAllBuyedTicketsByPeriod/?startDate=2019-05-01&endDate=2022-05-05
    @GetMapping("/getAllBuyedTicketsByPeriod/")
    public List<TicketDTO> getAllBuyedTicketsByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ticketService.getAllBuyedTicketsByPeriod(startDate, endDate).stream().map(t -> modelMapper.map(t, TicketDTO.class)).collect(Collectors.toList());

    }
}
