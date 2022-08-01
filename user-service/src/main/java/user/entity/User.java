package user.entity;

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

//    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"))
//    private Set<Role> roles;

//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getEmail(), getPassword());
//    }
}
