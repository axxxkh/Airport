package com.flightService.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Aircraft extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_type_id", referencedColumnName = "id", nullable = false)
    private AircraftType typeId;
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;
    @Column(name = "serial_number", nullable = false)
    private int serialNumber;
    private boolean active = Boolean.TRUE;
}
