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
@Table(name = "Passenger")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @Email
    @Column(name = "email")
    private String email;
    @NonNull
    private String password;
    @Column(name = "active")
    private boolean isActive = Boolean.TRUE;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User passenger = (User) o;
        return isActive() == passenger.isActive() && getName().equals(passenger.getName()) && getSurname().equals(passenger.getSurname()) && Objects.equals(getEmail(), passenger.getEmail()) && getPassword().equals(passenger.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getEmail(), getPassword(), isActive());
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return isActive;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return isActive;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return isActive;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return isActive;
//    }
}
