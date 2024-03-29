package com.flightService.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Airline extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    private int rate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "airline")
    @ToString.Exclude
    private List<Aircraft> airCrafts;
    private boolean active = Boolean.TRUE;

    @Override
    public String toString() {
        return "Airline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                ", airCrafts=" + airCrafts +
                ", active=" + active +
                '}';
    }
}
