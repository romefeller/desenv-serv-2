package com.example.aulabd.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class AlunoDAO {

    @Autowired
	DataSource dataSource;

	@Autowired
	PasswordEncoder passwordEncoder;

	JdbcTemplate jdbc;

	@PostConstruct
	private void initialize() {
		jdbc = new JdbcTemplate(dataSource);
	}

	public void inserirAluno(Aluno aluno) {
		String sql = "INSERT INTO aluno(nome,cpf,password)" +
	                 " VALUES (?,?,?)";
		Object[] obj = new Object[3];
		obj[0] = aluno.getNome();
		obj[1] = aluno.getCpf();
		obj[2] = passwordEncoder.encode(aluno.getPassword());
		jdbc.update(sql, obj);
	}

	public void inserirPerfil(String uuid){
		Aluno a = mostrarAluno(uuid);
		String sql = "INSERT INTO perfil(alunoid,cargo)" +
	                 " VALUES (?,?)";
		Object[] obj = new Object[2];
		obj[0] = a.getId();
		obj[1] = "aluno";
		jdbc.update(sql, obj);
	}

	public String obterUUID(String cpf){
		String sql = "SELECT id FROM aluno where cpf=?";
		Map<String,Object> mp = jdbc.queryForMap(sql,cpf);
		UUID uuid = (UUID) mp.get("id");
		return uuid.toString();
	}

	public void atualizarAluno(Aluno novo, String uuid){
		String sql = "UPDATE aluno " + 
			"SET nome = ?, cpf = ? WHERE id = ?::uuid";
		Object[] obj = new Object[3];
		obj[0] = novo.getNome();
		obj[1] = novo.getCpf();
		obj[2] = uuid;
		jdbc.update(sql,obj);
	}

	public void deletarAluno(String uuid){
		String sql = "DELETE FROM aluno where id = ?::uuid";
		jdbc.update(sql,uuid);
	}

	public Aluno mostrarAluno(String uuid){
		String sql = "SELECT * FROM aluno where id=?::uuid";
		return Aluno.converter(jdbc.queryForMap(sql,uuid));
	}


	public ArrayList<Aluno> listarAlunos(){
		String sql = "SELECT * FROM aluno";
		return Aluno.converterTodos(jdbc.queryForList(sql));
	}

	public ArrayList<Disciplina> listarMatriculadas(String uuidAluno){
		String sql = "SELECT * FROM disciplina" + 
		             "INNER JOIN matricula ON disciplina.id = matricula.disciplina_id" + 
					 "INNER JOIN aluno ON disciplina.aluno_id = aluno.id" +
					 "WHERE aluno.id = ?";
		return Disciplina.converterTodos(jdbc.queryForList(sql));
	}
}
