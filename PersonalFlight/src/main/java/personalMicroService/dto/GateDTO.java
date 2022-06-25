package personalMicroService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GateDTO {
    private int capacity;
    private TerminalDTO terminal;
}
