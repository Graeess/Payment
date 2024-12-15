package com.example.demo.Security;
import com.example.demo.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.ignoringRequestMatchers("/pay", "/check"))

                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/pay", "/check").authenticated()  // Требуем аутентификацию для /pay и /check
                                .anyRequest().permitAll()  // Все остальные запросы разрешены
                )

                .formLogin(form ->
                        form.loginPage("/login")
                                .permitAll()
                )

                .httpBasic(withDefaults())

                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return userService;  // Используем сервис, который реализует UserDetailsService
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Используем BCrypt для шифрования паролей
    }

    @Bean
    public AuthenticationProvider authProvider(UserService userService, PasswordEncoder passwordEncoder) {
        return new CustomAuthenticationProvider(userService, passwordEncoder);
    }
}
