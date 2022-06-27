package flightMicroService.config;

import flightMicroService.entity.Passenger;
import flightMicroService.service.FlightUserDetailsService;
import flightMicroService.service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.criterion.Restrictions.and;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private FlightUserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    private PassengerService passengerService;
    private DataSource dataSource;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider1() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder);
//        return provider;
//    }

//    @Bean
//    public UserDetails configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ADMIN"));
//        UserDetails user = userDetailsService.buildUserForAuthentication(Passenger.builder()
//                .surname("ddd")
//                .name("bbb")
//                .password(passwordEncoder.encode("123")).build(), authorities);
//
//        UserDetailsManager userDetailsManager = new MyJdbcManager();
//        userDetailsManager.createUser(user);
//        return user;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
//                .cors().disable()
                .csrf().disable()
//                .exceptionHandling()
//                .accessDeniedHandler((request, response, accessDeniedException) -> response.sendError(HttpServletResponse.SC_FORBIDDEN))
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
                .formLogin().permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/flight/**").permitAll()
                .antMatchers(HttpMethod.POST, "/flight/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/flight/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/flight/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/passenger/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/passenger/*").hasAuthority("ADMIN")
                .anyRequest().authenticated();

//                .and().httpBasic(withDefaults());
        return http.build();
    }


}
