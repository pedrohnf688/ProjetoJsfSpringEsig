package com.pedrohnf688.api.repository;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pedrohnf688.api.modelo.Tarefa;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TarefaRepositoryTest {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Before
	public void setUp() throws Exception {

		Tarefa t1 = new Tarefa();
		t1.setNome("Limpar");
		t1.setStatus(false);
		this.tarefaRepository.save(t1);

		Tarefa t2 = new Tarefa();
		t2.setNome("Correr");
		t2.setStatus(true);
		this.tarefaRepository.save(t2);

	}

	public final void tearDown() {
		this.tarefaRepository.deleteAll();
	}

	@Test
	public void testeStatusAtivo() {
		List<Tarefa> f = this.tarefaRepository.findByTarefaStatus();

		for (Tarefa tarefa : f) {
			assertNotNull(tarefa);
		}
	}

	@Test
	public void testeStatusFeitos() {
		List<Tarefa> f = this.tarefaRepository.findByTarefaStatusFeitas();
		
		for (Tarefa tarefa : f) {
			assertNotNull(tarefa);
		}
	}
	
	
	
	
	
	
}
