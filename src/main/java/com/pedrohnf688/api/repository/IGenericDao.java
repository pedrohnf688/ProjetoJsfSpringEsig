package com.pedrohnf688.api.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.pedrohnf688.api.modelo.Tarefa;

public interface IGenericDao<T, ID extends Serializable> {

	void save(T tarefa);

	void delete(T tarefa);

	Optional<Tarefa> findById(ID id);

	List<T> listAll();
	
	void deleteById(ID id);

}
