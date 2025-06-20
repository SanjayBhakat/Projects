package Sanjay.Journel.config;

import Sanjay.Journel.Service.UserDetailsServiceImpl;
import Sanjay.Journel.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtFilter jwtFilter;

    public SpringSecurity(UserDetailsServiceImpl userDetailsService, JwtFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF for simplicity (enable if needed)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/signup", "/login").permitAll()  // Allow signup & login
                .anyRequest().authenticated()  // Secure all other endpoints
            )
            .formLogin(login -> login
                .loginProcessingUrl("/login")  // Default login endpoint
                .defaultSuccessUrl("/home", true)  // Redirect after successful login
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")  // Define logout URL
                .logoutSuccessUrl("/login?logout")  // Redirect after logout
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
}
