package com.example.aulabd.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class MatriculaDAO {

    @Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbc;
	
	@PostConstruct
	private void initialize() {
		jdbc = new JdbcTemplate(dataSource);
	}
	
	public void matricular(Matricula mat) {
		String sql = "INSERT INTO matricula(aluno_id,disciplina_id)" +
	                 " VALUES (?::uuid,?::uuid)";
		Object[] obj = new Object[2];
		//primeiro ?
		obj[0] = mat.getAlunoId();
		//segundo ?
		obj[1] = mat.getDisciplinaId();
		jdbc.update(sql, obj);
	}
}
