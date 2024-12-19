package com.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final DataSource dataSource;

    @Autowired
    public CustomUserDetailsService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT correo, contrasena FROM usuarios WHERE correo = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String correo = resultSet.getString("correo");
                String contrasena = resultSet.getString("contrasena");
                return User.withUsername(correo)
                           .password(contrasena)
                           .roles("USER")
                           .build();
            } else {
                throw new UsernameNotFoundException("Usuario no encontrado: " + username);
            }
        } catch (SQLException e) {
            throw new UsernameNotFoundException("Error al acceder a la base de datos", e);
        }
    }
}
