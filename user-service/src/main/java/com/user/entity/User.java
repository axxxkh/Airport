package com.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;

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
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

}
