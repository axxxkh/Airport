package airport.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassportDTO {
    @NonNull
    private String serialNumber;
    private LocalDate birthdate;
    private LocalDate issueDate;
    private int passengerID;
    private String passportType;
}
