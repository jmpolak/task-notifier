package jmpolak.task_notification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // .csrf().disable() // disable CSRF for APIs
                // .authorizeHttpRequests()
                // .requestMatchers("/api/auth/**").permitAll() // login/signup are public
                // .anyRequest().authenticated() // other endpoints require JWT/interceptor
                // .and()
                // .httpBasic().disable() // disable default basic auth
                // .formLogin().disable(); // disable default login page

                .csrf().disable() // Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permit all endpoints
                )
                .httpBasic().disable() // Disable basic auth
                .formLogin().disable(); // Disable default login page

        return http.build();
    }
}
