package com.pedrohnf688.api.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.pedrohnf688.api.modelo.Tarefa;

public interface IGenericDao<T, ID extends Serializable> {

	void save(T object);

	void delete(T object);

	Optional<T> findById(ID id);

	List<T> listAll();
	
	void deleteById(ID id);
	
	void deleteAll(List<T> lista);

}
