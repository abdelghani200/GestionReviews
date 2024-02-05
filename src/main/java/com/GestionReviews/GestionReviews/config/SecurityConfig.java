package com.GestionReviews.GestionReviews.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> {
            authorizeRequests
                    .requestMatchers("/css/**").permitAll()
                    .requestMatchers("/admins/**", "/users/**").hasRole("ADMIN")
                    .requestMatchers("/reviews/report").hasAuthority("MODERATOR")
                    .requestMatchers("/", "/reviews/**").authenticated()
                    .requestMatchers("/redirectByRole").authenticated()
                    .requestMatchers("createUser").permitAll()
                    .requestMatchers("/index").hasRole("ADMIN")
                    .requestMatchers("/edit/**").hasRole("ADMIN")
                    .requestMatchers("/delete/**").hasRole("ADMIN")
                    .requestMatchers("/moderateur/signaler/**").hasRole("MODERATOR")
                    .requestMatchers("/moderateur/edit/**").hasRole("MODERATOR")
                    .requestMatchers("/moderateur/delete/**").hasRole("MODERATOR")
                    .requestMatchers("/moderateur/index").hasRole("MODERATOR");

        });

        http.formLogin(formLogin -> formLogin
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/redirectByRole", true)
                .failureUrl("/login?error=true"));

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll());

        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}