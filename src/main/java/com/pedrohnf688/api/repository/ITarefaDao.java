package com.pedrohnf688.api.repository;

import java.util.List;

import com.pedrohnf688.api.modelo.Tarefa;

public interface ITarefaDao extends IGenericDao<Tarefa, Integer>{

	List<Tarefa> findByTarefaStatus();
	List<Tarefa> findByTarefaStatusFeitas();
}
