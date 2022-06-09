package airport.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Gate extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int capacity;
    @ManyToOne
    @JoinColumn(name = "terminal_id")
    private Terminal terminal;
    private boolean active = Boolean.TRUE;
}
