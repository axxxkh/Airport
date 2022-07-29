package com.flightService.entity;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Passenger extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "passenger", orphanRemoval = true)
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

//    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"))
//    private Set<Role> roles;


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