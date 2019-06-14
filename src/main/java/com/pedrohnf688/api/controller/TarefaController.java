package com.pedrohnf688.api.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.primefaces.event.CellEditEvent;
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

	private Tarefa tarefa;

	@PostConstruct
	public void init() {
		tarefa = new Tarefa();
		carregarDados();
	}

	@Deferred
	@RequestAction
	@IgnorePostback
	public void carregarDados() {
		listaTarefas = this.tarefaService.listAll();
	}

	public void salvarTarefa() {
		if (tarefa.getNome() != null && tarefa.getNome().trim() != "") {
			tarefa.setStatus(false);
			this.tarefaService.save(tarefa);
			tarefa = new Tarefa();
			carregarDados();
		}
	}

	public void editarTarefa(Tarefa t) {
		this.tarefa = t;
	}

	public void removerTarefa(Tarefa t) {
		this.tarefaService.delete(t);
		carregarDados();
	}

	public void removerTarefaPorId(Integer id) {
		this.tarefaService.deleteById(id);
		carregarDados();
	}

	public void mudarStatus(Tarefa t) {
		if (t.getStatus()) {
			t.setStatus(false);
		} else {
			t.setStatus(true);
		}
		this.tarefaService.save(t);
	}

	public List<Tarefa> ListaStatusAtivos() {
		return this.tarefaService.findByTarefaStatus();
	}

	public List<Tarefa> ListaStatusFeitos() {
		return this.tarefaService.findByTarefaStatusFeitas();
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();

		Object newValue = event.getNewValue();

		FacesContext context = FacesContext.getCurrentInstance();
		if (newValue != null && !newValue.equals(oldValue)) {

			Tarefa tarefa = context.getApplication().evaluateExpressionGet(context, "#{tarefa}", Tarefa.class);
			this.tarefaService.save(tarefa);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			carregarDados();
		}

	}

	public List<Tarefa> getListaTarefas() {
		return listaTarefas;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

}
