package com.example.aulabd.model;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class DisciplinaDAO {

    @Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbc;
	
	@PostConstruct
	private void initialize() {
		jdbc = new JdbcTemplate(dataSource);
	}
	
	public void inserirDisciplina(Disciplina disc) {
		String sql = "INSERT INTO disciplina(nome,sigla)" +
	                 " VALUES (?,?)";
		Object[] obj = new Object[2];
		//primeiro ?
		obj[0] = disc.getNome();
		//segundo ?
		obj[1] = disc.getSigla();
		jdbc.update(sql, obj);
	}

	public ArrayList<Disciplina> listarDisciplinas(){
		String sql = "SELECT d.id, d.nome, d.sigla, d.professor_id, p.nome AS professor_nome " +
		             "FROM disciplina d LEFT JOIN professor p ON d.professor_id = p.id";
		return Disciplina.converterTodos(jdbc.queryForList(sql));
	}

	public void atribuirProfessor(String disciplinaId, String professorId){
		String sql = "UPDATE disciplina SET professor_id = ?::uuid WHERE id = ?::uuid";
		jdbc.update(sql, professorId, disciplinaId);
	}
}
