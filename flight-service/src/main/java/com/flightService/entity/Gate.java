package com.flightService.entity;

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
    private Long id;
    @NonNull
    private int capacity;
    @ManyToOne
    @JoinColumn(name = "terminal_id",nullable = false)
    private Terminal terminal;
    private boolean active = Boolean.TRUE;
}
