package flightMicroService.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import flightMicroService.entity.Passenger;
import flightMicroService.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Builder
@AllArgsConstructor
@Data
public class FlightUserDetailsService implements UserDetailsService {

    private PassengerService passengerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Passenger passenger = passengerService.getByLogin(username);
        System.out.println(passenger);
        List<GrantedAuthority> authorities = getUserAuthority(passenger.getRoles());
        return buildUserForAuthentication(passenger,authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        List<GrantedAuthority> roles = new ArrayList<>();
        for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(Passenger passenger, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(passenger.getName(), passenger.getPassword(),
                true, true, true, true, authorities);
    }
}
