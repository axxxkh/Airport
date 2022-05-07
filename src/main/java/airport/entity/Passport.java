package airport.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "serial_number")
    private String serialNumber;
    private LocalDate birthdate;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    @Column(name = "passport_type")
    private String passportType;

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", birthdate=" + birthdate +
                ", issueDate=" + issueDate +
                ", passenger=" + passenger +
                ", passportType='" + passportType + '\'' +
                '}';
    }
}
