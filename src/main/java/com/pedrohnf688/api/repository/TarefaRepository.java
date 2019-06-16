package com.pedrohnf688.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pedrohnf688.api.modelo.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	@Query(value = "SELECT * FROM Tarefa t WHERE t.status = false", nativeQuery = true)
	List<Tarefa> findByTarefaStatus();

	@Query(value = "SELECT * FROM Tarefa t WHERE t.status = true", nativeQuery = true)
	List<Tarefa> findByTarefaStatusFeitas();
	
}
