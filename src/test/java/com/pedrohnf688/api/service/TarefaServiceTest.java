package com.pedrohnf688.api.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.repository.TarefaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TarefaServiceTest {

	@MockBean
	private TarefaRepository tarefaRepository;

	@Autowired
	private TarefaService tarefaService;

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.tarefaRepository.save(Mockito.any(Tarefa.class))).willReturn(new Tarefa());
		BDDMockito.given(this.tarefaRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new Tarefa()));
	}

	@Test
	public void testPersistirTarefa() {
		Tarefa t = this.tarefaService.save(new Tarefa());
		assertNotNull(t);
	}

	@Test
	public void testBuscarTodasTarefas() {
		List<Tarefa> t = this.tarefaService.listAll();

		for (Tarefa tarefa : t) {
			assertNotNull(tarefa);
		}

	}

	@Test
	public void testeBuscarTarefaPorId() {
		Optional<Tarefa> t = this.tarefaService.findById(1L);
		assertTrue(t.isPresent());
	}
	
	
	
}
