package br.com.nottrello.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Projeto implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@OneToMany(mappedBy="projeto")
	private List<Tarefa> tarefas = new ArrayList<>();
	private String descricao;
	private String dataVecimento;
	private String etiquetaCor;
	
	@OneToOne(mappedBy="projeto")
	private Equipe equipe;
	
	@OneToMany
	private List<Usuario> usuarios = new ArrayList<>();
	
	@ManyToMany
	private List<Categoria> categorias = new ArrayList<>();
	
	public Projeto() {
		super();
	}

	public Projeto(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Tarefa> getTarefas() {
		Tarefa tarefa1 = new Tarefa(null,"Java Web","Estudar Java web no youtube");
		Tarefa tarefa2 = new Tarefa(null,"Python","Estudar Python web com django");
		
		tarefas.add(tarefa1);
		tarefas.add(tarefa2);
		
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		
		this.tarefas = tarefas;
	}

	public String getDescricao() {
		
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataVecimento() {
		return dataVecimento;
	}

	public void setDataVecimento(String dataVecimento) {
		this.dataVecimento = dataVecimento;
	}

	public String getEtiquetaCor() {
		return etiquetaCor;
	}

	public void setEtiquetaCor(String etiquetaCor) {
		this.etiquetaCor = etiquetaCor;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}


	public List<Categoria> getCategorias() {
		
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	
	
	

}
