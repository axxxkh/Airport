package auth.entity;

import lombok.*;
import org.springframework.lang.NonNull;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

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
    @Column(name = "email", nullable = false ,unique = true)
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
//
//    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"))
//    private Set<Role> roles;


}
