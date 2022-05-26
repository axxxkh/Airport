package airport.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassportDTO {
    private String serialNumber;
    private LocalDate birthdate;
    private LocalDate issueDate;
    private int passengerID;
    private String passportType;
}
