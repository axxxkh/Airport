package Airport.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Aircraft {
    @Id
    private int id;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private AircraftTypes typeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avialine_id")
    private Avialine avialine;

    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + id +
                ", avialine=" + avialine +
                '}';
    }
}
