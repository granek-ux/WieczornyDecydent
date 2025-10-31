package io.github.granekux.wieczornydecydent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Wyłącz CSRF (Cross-Site Request Forgery)
                // Ponieważ używamy API bezstanowego (stateless) z tokenami, nie potrzebujemy tej ochrony
                .csrf(csrf -> csrf.disable())

                // 2. Ustaw zarządzanie sesją na STATELESS (BEZSTANOWE)
                // Mówimy Springowi, aby NIE tworzył sesji (JSESSIONID)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 3. Konfiguracja autoryzacji zapytań (TO JEST KLUCZ!)
                .authorizeHttpRequests(auth -> auth
                        // Mówimy, że endpointy /api/auth/** (czyli /register i /login)
                        // są PUBLICZNE (permitAll)
                        .requestMatchers("/api/auth/**").permitAll()

                        // Mówimy, że WSZYSTKIE INNE zapytania (anyRequest)
                        // muszą być UWIERZYTELNIONE (authenticated)
                        .anyRequest().authenticated()
                )

                // 4. Wyłącz domyślny formularz logowania Springa
                // (Odpowiedź na Twoje pytanie)
                .formLogin(form -> form.disable())

                // 5. Wyłącz domyślne logowanie HTTP Basic
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}