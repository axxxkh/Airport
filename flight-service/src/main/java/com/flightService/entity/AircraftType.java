package com.flightService.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "Aircraft_type")
public class AircraftType extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String producer;
    @NonNull
    private String type;
    @NonNull
    private int capacity;
    private boolean active = Boolean.TRUE;
}
