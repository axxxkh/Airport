package airport.controller;

import airport.DTO.TicketDTO;
import airport.service.TicketService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class TicketController {

    private ModelMapper modelMapper;
    private TicketService ticketService;


    @GetMapping("/ticket/buyTicket/")
    public String buyTicket() {
        return null;
    }

    @GetMapping("/ticket/getAllAvailableTickets/")
    public List<TicketDTO> getAllTickets() {
        return ticketService.getAllAvailableTickets()
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .collect(Collectors.toList());
    }
}
