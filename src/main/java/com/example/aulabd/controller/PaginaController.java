
package com.example.aulabd.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.aulabd.model.Aluno;
import com.example.aulabd.model.AlunoService;
import com.example.aulabd.model.Disciplina;
import com.example.aulabd.model.DisciplinaService;
import com.example.aulabd.model.Matricula;

@Controller
public class PaginaController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/aluno")
	public String formAluno(Model model) {
		model.addAttribute("aluno",new Aluno());
		return "formaluno";
	}
	
	@PostMapping("/aluno")
	public String postAluno(@ModelAttribute Aluno aluno,
			                  Model model) {
        //AlunoService eh feito via autowired
		AlunoService cs = context.getBean(AlunoService.class);
		cs.inserirAluno(aluno);
		String uuid = cs.obterUUID(aluno.getCpf());
		cs.inserirPerfil(uuid);
		return "sucesso";
	}

	@GetMapping("/perfil/{uuid}")
	public String verPerfil(@PathVariable String uuid, Model model){
		AlunoService cs = context.getBean(AlunoService.class);
		Aluno aluno = cs.mostrarAluno(uuid);
		model.addAttribute("idAluno",aluno.getId());
		model.addAttribute("cpfAluno",aluno.getCpf());
		model.addAttribute("nomeAluno",aluno.getNome());
		return "paginaaluno";
	}

	@GetMapping("/listar")
	public String listarAlunos(Model model){
		AlunoService cs = context.getBean(AlunoService.class);
		ArrayList<Aluno> alunos = (ArrayList<Aluno>) cs.listarAlunos();
		model.addAttribute("alunos",alunos);
		return "listaraluno";
	}

	@GetMapping("/aluno/{id}/editar")
	public String formAtualizar(@PathVariable("id") String uuid, Model model) {
		AlunoService cdao = context.getBean(AlunoService.class);
		Aluno alunoId = cdao.mostrarAluno(uuid);
		model.addAttribute("aluno",alunoId);
		model.addAttribute("id",uuid);
		return "formupdaluno";
	}

	@PostMapping("/aluno/{id}/editar")
	public String atualizararAluno(@PathVariable("id") String id, 
			                       Model model,
			                       @ModelAttribute Aluno cli) {
		AlunoService cdao = context.getBean(AlunoService.class);
		cdao.atualizarAluno(cli,id);
		return "redirect:/listar";
	}

	@PostMapping("/aluno/{id}/deletar")
	public String deletarAluno(@PathVariable("id") String id, 
			                       Model model) {
		AlunoService cdao = context.getBean(AlunoService.class);
		cdao.deletarAluno(id);
		return "redirect:/listar";
	}

	@GetMapping("/matricula/{id}")
	public String matricularAluno(@PathVariable("id") String id, 
			                       Model model){
		model.addAttribute("alunoid",id);
		DisciplinaService cs = context.getBean(DisciplinaService.class);
		ArrayList<Disciplina> disciplinas = (ArrayList<Disciplina>) cs.listarDisciplinas();
		model.addAttribute("disciplinas",disciplinas);
		return "matricula";						
	} 

	@PostMapping("/matricula/{id}/disciplina/{did}")
	public String matricular(@PathVariable("id") String alunoid,
							 @PathVariable("did") String disciplinaid
			                 ){
		AlunoService ms = context.getBean(AlunoService.class);
		ms.matricular(new Matricula(alunoid,disciplinaid));						
		return "sucesso";
	}

}
