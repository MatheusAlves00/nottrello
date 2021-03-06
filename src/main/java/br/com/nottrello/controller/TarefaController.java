package br.com.nottrello.controller;


import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.nottrello.model.entity.Projeto;
import br.com.nottrello.model.entity.Tarefa;
import br.com.nottrello.model.service.ProjetoService;
import br.com.nottrello.model.service.TarefaService;

@Controller
public class TarefaController{

	@Autowired
	private TarefaService tarefaService;
	
	@Autowired
	private ProjetoService projetoService;
	
	
	public TarefaController(TarefaService tarefaService) {
		super();
		this.tarefaService = tarefaService;
	}

	@RequestMapping("/salvarTarefa")
	public String salvarTarefa(Tarefa tarefa) {
		tarefaService.salvar(tarefa);
		
		return "redirect:/projeto/projeto?id="+tarefa.getProjeto().getId();
	}
	
	@RequestMapping("/excluirTarefa")
	public String removeTarefa(@PathParam("id") Long id) {
		
		Tarefa  tarefa = tarefaService.buscar(id);
		Projeto projeto = projetoService.buscarPorId(tarefa.getProjeto().getId());
		tarefaService.remover(id);
		
		
		
		return "redirect:/projeto/projeto?id="+projeto.getId();
	}
	

	
}
