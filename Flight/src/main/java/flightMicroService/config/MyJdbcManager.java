package flightMicroService.config;

import flightMicroService.dto.PassengerDTO;
import flightMicroService.dto.PassportDTO;
import flightMicroService.dto.RoleDTO;
import flightMicroService.entity.Passenger;
import flightMicroService.service.PassengerService;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyJdbcManager extends JdbcUserDetailsManager {

    private PassengerService passengerService;

    @Override
    public void createUser(UserDetails user) {

        PassportDTO passportDTO = PassportDTO.builder()
                .passportType("UA")
                .birthdate(LocalDate.now())
                .issueDate(LocalDate.now())
                .serialNumber("123sffgg")
                .build();
        List<PassportDTO> passportDTOS = new ArrayList<>();
        passportDTOS.add(passportDTO);

        List<RoleDTO> roles = new ArrayList<>();
        roles.add(RoleDTO.builder().role("ADMIN").build());

        PassengerDTO passengerDTO = PassengerDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .name("AAA")
                .surname("BBB")
                .passports(passportDTOS)
                .roles(roles)
                .build();
    }
}
