package cl.duoc.ipy.websdl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ipy.websdl.domain.Cliente;
import cl.duoc.ipy.websdl.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	private ClienteService clienteService;

	@Autowired
	public ClienteController(final ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping
	ResponseEntity<Cliente> crear(@RequestBody Cliente inputDTO) {

		Cliente cliente = clienteService.crear(inputDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}

	@GetMapping("/{id}")
	ResponseEntity<Cliente> obtener(@PathVariable(name = "id") Long id) {
		Cliente cliente = clienteService.obtener(id);
		return ResponseEntity.ok(cliente);
	}

	@GetMapping
	ResponseEntity<List<Cliente>> consultar(
			@RequestParam(name = "partRut", required = false) final String partRut,
			@RequestParam(name = "partNombre", required = false) final String partNombre,
			@RequestParam(name = "partMail", required = false) final String partMail,
			@RequestParam(name = "comuna", required = false) final String comuna,
			@RequestParam(name = "offset", defaultValue = "0") final Integer offset, 
			@RequestParam(name = "limit", defaultValue = "100") final Integer limit) {
		
		final List<Cliente> clientes = clienteService.consultar(partRut, partNombre, partMail, comuna, offset, limit);

		return ResponseEntity.ok(clientes);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Boolean> actualizar(@PathVariable(name = "id") Long id, @RequestBody Cliente clienteDTO){
		return ResponseEntity.ok(clienteService.actualizar(id, clienteDTO));
	}
}
