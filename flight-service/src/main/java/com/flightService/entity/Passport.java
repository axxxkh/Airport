package com.flightService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"serial_number", "passport_type"})})
public class Passport extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;
    @NonNull
    private LocalDate birthdate;
    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;
    private boolean active = Boolean.TRUE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return Objects.equals(getSerialNumber(), passport.getSerialNumber())
                && Objects.equals(getIssueDate(), passport.getIssueDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSerialNumber(), getIssueDate());
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", birthdate=" + birthdate +
                ", issueDate=" + issueDate +
//                ", passenger=" + passenger +
//                ", passportType='" + passportType + '\'' +
                ", active=" + active +
                '}';
    }
}
