package br.com.nottrello.controller;

import java.io.FileNotFoundException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import br.com.nottrello.model.entity.Usuario;
import br.com.nottrello.model.service.ProjetoService;
import br.com.nottrello.model.service.TarefaService;
import br.com.nottrello.model.service.UsuarioService;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ProjetoService projetoService;

	@Autowired
	private TarefaService tarefaService;

	// Sempre lembrar de colocar o construtor para que os métodos do service
	// funcionem
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;

	}

	@GetMapping("/novo")
	public String novo() {

		return "pags/formCadastro";
	}

	@PostMapping("/salvar")
	public String salvar(Usuario usuario) {

		usuario.getNomeUsuario().toLowerCase();
		usuario.getEmail().toLowerCase();
		usuarioService.salvar(usuario);

		return "redirect:/usuario/entrar";
	}

	@GetMapping("/entrar")
	public String entrar() {
		return "pags/formLogin";
	}

	@GetMapping("/logado")
	public String usuarioLogado(Model model, HttpSession session) {	
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("projetos", projetoService.listarPorUsuario(usuario.getId()));		
		return "/pags/usuariologado";
	}

	@GetMapping("/logar")
	public String logar(Usuario usuario, HttpSession session) {
		

		if (usuarioService.verificarUsuario(usuario)) {
			session.setAttribute("usuarioLogado", usuarioService.buscarUsuarioNome(usuario.getNomeUsuario()));
			return "redirect:/usuario/logado";
		} else {
			return "pags/loginfail";
		}

	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return entrar();
	}
	
	@RequestMapping("/editarPerfil")
	public String editarPerfil(@PathParam("id") Long id, Model model) {
		model.addAttribute("usuario", usuarioService.buscarPorId(id));
		
		return "pags/formPerfil";
	}
	
	@RequestMapping("/salvarPerfil")
	public String salvarPerfil(Usuario usuario) {
		usuario.getNomeUsuario().toLowerCase();		
		usuarioService.salvar(usuario);

		return "redirect:/usuario/logado";
		
	}
	
	
	
}
