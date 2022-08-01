package personalMicroService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDTO {
    private String name;
    private GateDTO gateId;
    private SalaryDTO salary;
    private PersonalInfoDTO personalInfo;
}
