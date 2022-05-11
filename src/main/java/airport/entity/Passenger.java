package airport.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "passenger")
    private List<Ticket> tickets;
    @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "passenger")
    private List<Passport> passports;
    private boolean active;

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
//                ", tickets=" + tickets +
//                ", passports=" + passports +
                '}';
    }
}
