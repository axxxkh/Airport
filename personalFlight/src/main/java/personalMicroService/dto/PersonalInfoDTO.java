package personalMicroService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfoDTO {
    private boolean married;
    private LocalDate birthday;
    private String city;
}
