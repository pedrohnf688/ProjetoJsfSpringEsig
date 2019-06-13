package com.pedrohnf688.api.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.service.TarefaService;

@Scope(value = "session")
@Component(value = "tarefaController")
@ELBeanName(value = "tarefaController")
@Join(path = "/", to = "/tarefa.jsf")
public class TarefaController {

	@Autowired
	private TarefaService tarefaService;

	private List<Tarefa> listaTarefas;

	private Tarefa tarefa = new Tarefa();

	@PostConstruct
	public void init() {
		carregarDados();
	}

	@Deferred
	@RequestAction
	@IgnorePostback
	public void carregarDados() {
		listaTarefas = this.tarefaService.listAll();
	}

	public void salvarTarefa() {
		tarefa.setStatus(false);
		this.tarefaService.save(tarefa);
	}

	public void editarTarefa(Tarefa t) {
		this.tarefa = t;
	}

	public void removerTarefa(Tarefa t) {
		this.tarefaService.delete(t);
	}
	
	public void mudarStatus(Tarefa t) {
		if(t.getStatus()) {
			t.setStatus(false);
		}else {
			t.setStatus(true);
		}

		this.tarefaService.save(t);
	}
	
	

	public List<Tarefa> getListaTarefas() {
		return listaTarefas;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

}
