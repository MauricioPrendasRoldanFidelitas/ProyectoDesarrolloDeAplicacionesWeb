package com.proyecto.config;

import com.proyecto.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
                .requestMatchers("/usuario/perfil", "/carrito/**").authenticated() // Rutas protegidas
                .requestMatchers("/**").permitAll() // Todo lo demás permitido
            .and()
            .formLogin()
                .loginPage("/login") // Página personalizada de inicio de sesión
                .loginProcessingUrl("/login") // Ruta a la que envía el formulario
                .defaultSuccessUrl("/inicio", true) // Redirigir tras inicio exitoso
                .failureUrl("/login?error=true") // Redirigir tras fallo
                .permitAll()
            .and()
            .logout(logout -> logout
            .logoutSuccessUrl("/login?logout=true")
            .permitAll())
            .csrf(csrf -> csrf.disable()); // Desactiva CSRF para pruebas

        return http.build();
    }
}
