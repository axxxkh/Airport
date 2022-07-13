package auth.jwt;

import auth.Repository.PassengerRepository;
import auth.entity.Passenger;
import auth.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Builder
@AllArgsConstructor
@Data
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Passenger passenger = passengerRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("vgvg"));
        if (passenger== null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> authorities = getUserAuthority(passenger.getRoles());
        return buildUserForAuthentication(passenger,authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        List<GrantedAuthority> roles = new ArrayList<>();
        for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRole().name()));
        }
        return new ArrayList<>(roles);
    }

    public UserDetails buildUserForAuthentication(Passenger passenger, List<GrantedAuthority> authorities) {
        return new User(passenger.getUsername(), passenger.getPassword(),
                true, true, true, true, authorities);
    }
}
