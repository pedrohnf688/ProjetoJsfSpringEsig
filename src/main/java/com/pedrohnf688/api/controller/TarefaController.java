package com.pedrohnf688.api.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.service.TarefaService;

@Scope(value = "session")
@Component(value = "tarefaController")
@ELBeanName(value = "tarefaController")
@Join(path = "/", to = "/tarefa.jsf")
public class TarefaController implements Serializable{

	private static final long serialVersionUID = 897434443868250709L;

	@Autowired
	private TarefaService tarefaService;
	private List<Tarefa> listaTarefas;
	private Tarefa tarefa = new Tarefa();
	private int qtdTarefaStatusAtivo;
	private int qtdTarefasStatusFeitas;

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

	public void removerTarefaPorId(Long id) {
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

	public void MudarListaStatusFeito(List<Tarefa> t) {
		if (listaStatusFeitos().size() == t.size()) {
			for (Tarefa tarefa : t) {
				tarefa.setStatus(false);
				this.tarefaService.save(tarefa);
			}
		} else if (listaStatusFeitos().size() < listaStatusAtivos().size()
				|| listaStatusFeitos().size() > listaStatusAtivos().size()
				|| listaStatusAtivos().size() == listaStatusFeitos().size()) {
			for (Tarefa tarefa : t) {
				tarefa.setStatus(true);
				this.tarefaService.save(tarefa);
			}
		}

	}

	public List<Tarefa> listaStatusAtivos() {
		return this.tarefaService.findByTarefaStatus();
	}

	public List<Tarefa> listaStatusFeitos() {
		return this.tarefaService.findByTarefaStatusFeitas();
	}

	public void carregarListaStatusAtivos() {
		listaTarefas = listaStatusAtivos();
	}

	public void carregarListaStatusFeitos() {
		listaTarefas = listaStatusFeitos();
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Edited");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		FacesContext context = FacesContext.getCurrentInstance();
		Tarefa tarefa = context.getApplication().evaluateExpressionGet(context, "#{tarefa}", Tarefa.class);

		if (tarefa != null) {

			this.tarefaService.save(tarefa);

		}

	}

	public int getQtdTarefaStatusAtivo() {
		qtdTarefaStatusAtivo = this.tarefaService.findByTarefaStatus().size();
		return qtdTarefaStatusAtivo;
	}

	public String deletarTarefasStatusFeitas() {
		this.tarefaService.deleteAll(listaStatusFeitos());
		return "/tarefa.xhtml?faces-redirect=true";
	}

	public int getQtdTarefasStatusFeitas() {
		qtdTarefasStatusFeitas = this.tarefaService.findByTarefaStatusFeitas().size();
		return qtdTarefasStatusFeitas;
	}

	public void addMensagem(Severity severity, String summary, String detail) {
		FacesMessage msg = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Tarefa> getListaTarefas() {
		return listaTarefas;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

}
