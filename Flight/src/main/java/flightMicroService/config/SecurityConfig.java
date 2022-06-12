package flightMicroService.config;

import flightMicroService.service.FlightUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private FlightUserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;


    @Bean
    public AuthenticationProvider authenticationProvider1() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/flight/**").permitAll()
                .antMatchers(HttpMethod.POST, "/flight/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/flight/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/flight/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/passenger/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/passenger/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().httpBasic(withDefaults());
//                .authorizeHttpRequests((authz) -> authz
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(withDefaults());
        return http.build();
    }
}
