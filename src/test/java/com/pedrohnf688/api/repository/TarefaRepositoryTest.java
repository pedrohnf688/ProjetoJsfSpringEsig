package com.pedrohnf688.api.repository;



import static org.junit.Assert.assertArrayEquals;

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

		Tarefa t = new Tarefa();
		t.setNome("Limpar");
		t.setStatus(false);
		this.tarefaRepository.save(t);

		Tarefa t1 = new Tarefa();
		t.setNome("Varrer");
		t.setStatus(false);
		this.tarefaRepository.save(t1);

	}

	public final void tearDown() {
		this.tarefaRepository.deleteAll();
	}

	@Test
	public void testBuscarStatus() {
		List<Tarefa> e = this.tarefaRepository.findByTarefaStatus();


	}

}
