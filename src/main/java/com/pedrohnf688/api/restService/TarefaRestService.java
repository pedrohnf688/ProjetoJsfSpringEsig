package com.pedrohnf688.api.restService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrohnf688.api.config.Response;
import com.pedrohnf688.api.modelo.Tarefa;
import com.pedrohnf688.api.service.TarefaService;

@RestController
@RequestMapping(value = "/tarefa")
@CrossOrigin(origins = "*")
public class TarefaRestService {

	@Autowired
	private TarefaService tarefaService;

	@GetMapping
	public List<Tarefa> buscarTarefas() {
		List<Tarefa> tarefas = this.tarefaService.listAll();
		return tarefas;
	}

	@GetMapping(value = "/ativas")
	public List<Tarefa> listaStatusAtivos() {
		return this.tarefaService.findByTarefaStatus();
	}

	@GetMapping(value = "/feitas")
	public List<Tarefa> listaStatusFeitos() {
		return this.tarefaService.findByTarefaStatusFeitas();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<Tarefa>> deletarTarefaPorId(@PathVariable("id") Long id) {
		Response<Tarefa> response = new Response<Tarefa>();
		Optional<Tarefa> tarefa = this.tarefaService.findById(id);

		if (!tarefa.isPresent()) {
			response.getErros().add("Tarefa não encontrada");
			ResponseEntity.badRequest().body(response);
		}

		response.setData(tarefa.get());
		this.tarefaService.deleteById(id);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<Tarefa>> cadastrarTarefa(@RequestBody Tarefa tarefa) {
		Response<Tarefa> response = new Response<Tarefa>();

		this.tarefaService.save(tarefa);

		response.setData(tarefa);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping
	public ResponseEntity<Response<String>> deletarTarefasStatusFeitas() {
		Response<String> response = new Response<String>();
		List<Tarefa> listaTarefas = this.tarefaService.findByTarefaStatusFeitas();

		if (listaTarefas.isEmpty()) {
			response.getErros().add("A lista de tarefas está vazia.");
			return ResponseEntity.badRequest().body(response);
		}

		this.tarefaService.deleteAll(listaTarefas);
		return ResponseEntity.ok(new Response<String>());
	}

	@GetMapping(value = "/statusLista")
	public void MudarListaStatusFeito() {
		List<Tarefa> t = this.tarefaService.listAll();

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

}
