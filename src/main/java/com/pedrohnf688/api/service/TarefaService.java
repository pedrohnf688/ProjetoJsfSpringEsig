package com.pedrohnf688.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.repository.ITarefaDao;
import com.pedrohnf688.api.repository.TarefaRepository;

@Service
public class TarefaService implements ITarefaDao {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Override
	public void save(Tarefa tarefa) {
		this.tarefaRepository.save(tarefa);
	}

	@Override
	public void delete(Tarefa tarefa) {
		this.tarefaRepository.delete(tarefa);
	}

	@Override
	public Optional<Tarefa> findById(Integer id) {
		return this.tarefaRepository.findById(id);
	}

	@Override
	public List<Tarefa> listAll() {
		return this.tarefaRepository.findAll();
	}

	@Override
	public List<Tarefa> findByTarefaStatus() {
		return this.tarefaRepository.findByTarefaStatus();
	}

	@Override
	public List<Tarefa> findByTarefaStatusFeitas() {
		return this.tarefaRepository.findByTarefaStatusFeitas();
	}

}
