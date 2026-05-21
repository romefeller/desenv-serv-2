package com.example.aulabd.config;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminBootstrap {

    @Bean
    public CommandLineRunner seedAdmin(DataSource dataSource, PasswordEncoder encoder) {
        return args -> {
            JdbcTemplate jdbc = new JdbcTemplate(dataSource);
            Integer existing = jdbc.queryForObject(
                "SELECT COUNT(*) FROM aluno WHERE nome = ?", Integer.class, "admin");
            if (existing != null && existing == 0) {
                jdbc.update(
                    "INSERT INTO aluno(nome, cpf, password) VALUES (?, ?, ?)",
                    "admin", "00000000000", encoder.encode("admin123"));
            }
            jdbc.update(
                "INSERT INTO perfil(alunoid, cargo) " +
                "SELECT id, 'admin' FROM aluno WHERE nome = 'admin' " +
                "ON CONFLICT (alunoid) DO UPDATE SET cargo = 'admin'");
        };
    }
}
