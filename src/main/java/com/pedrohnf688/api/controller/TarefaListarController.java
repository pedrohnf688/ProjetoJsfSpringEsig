package com.pedrohnf688.api.controller;

import java.util.ArrayList;
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
@Component(value = "tarefaListar")
@ELBeanName(value = "tarefaListar")
@Join(path = "/kkk", to = "/tarefa-listar.jsf")
public class TarefaListarController {

	@Autowired
	private TarefaService tarefaService;

}
