package com.flightService.entity;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Passenger extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "passenger")
    @ToString.Exclude
    private List<Ticket> tickets;
    @OneToOne
    @ToString.Exclude
    @JoinColumn(name = "passport_id")
    private Passport passport;
    private boolean active = Boolean.TRUE;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return getName().equals(passenger.getName()) && getSurname().equals(passenger.getSurname()) && Objects.equals(getPassport(), passenger.getPassport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getPassport());
    }
}
