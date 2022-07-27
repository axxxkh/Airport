package com.flightService.entity;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @NonNull
    private String password;
    @Column(name = "secret_question")
    private String secretQuestion;
    @Column(name = "secret_answer")
    private String secretAnswer;
    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean isActive = Boolean.TRUE;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    /*
     * it designed that one user can store different passengers (family, children etc)
     * */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @ToString.Exclude
    List<Passenger> passengers;

}
