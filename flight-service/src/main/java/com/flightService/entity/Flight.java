package com.flightService.entity;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Flight extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "flight_number", nullable = false)
    private Integer flightNumber;
    private LocalDateTime time;
    @Column(name = "flight_status")
    private byte flightStatus;
    @ManyToOne
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;
    @ManyToOne
    @JoinColumn(name = "craft_id", nullable = false)
    @NonNull
    private Aircraft craftId;
    @ManyToOne
    @JoinColumn(name = "gate_id")
    private Gate gate;
    private boolean active = Boolean.TRUE;
    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;
}

