package auth.entity;

import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Passenger extends BasicEntity implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @Email
    @Column(name = "email")
    private String username;
    @NonNull
    private String password;

    //    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "passenger", orphanRemoval = true)
//    @ToString.Exclude
//    private List<Ticket> tickets;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "passenger", orphanRemoval = true)
//    @ToString.Exclude
//    private List<Passport> passports;
    private boolean active = Boolean.TRUE;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return isActive() == passenger.isActive() && getName().equals(passenger.getName()) && getSurname().equals(passenger.getSurname()) && Objects.equals(getUsername(), passenger.getUsername()) && getPassword().equals(passenger.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getUsername(), getPassword(), isActive());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> userRoles = new ArrayList<>();
        for (Role role : roles) {
            userRoles.add(new SimpleGrantedAuthority(role.getRole().name()));
        }
        return new ArrayList<>(userRoles);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
