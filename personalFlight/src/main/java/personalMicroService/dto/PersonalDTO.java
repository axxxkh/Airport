package personalMicroService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import personalMicroService.entity.Gate;
import personalMicroService.entity.PersonalInfo;
import personalMicroService.entity.Salary;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDTO {
    private String name;
    private GateDTO gateId;
    private SalaryDTO salary;
    private PersonalInfoDTO personalInfo;
}
