package com.flightService.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BasicEntity {
    public abstract long getId();
}
