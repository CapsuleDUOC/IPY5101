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
import cl.duoc.ipy.websdl.domain.Despacho;
import cl.duoc.ipy.websdl.dto.DespachoType;
import cl.duoc.ipy.websdl.dto.input.InputDespachoCrear;
import cl.duoc.ipy.websdl.dto.output.OutputDespachoConsultar;
import cl.duoc.ipy.websdl.enums.EstadoDespacho;
import cl.duoc.ipy.websdl.service.ClienteService;
import cl.duoc.ipy.websdl.service.DespachoService;

@RestController
@RequestMapping("/{clienteRut}/despacho")
public class DespachoController {

	private DespachoService despachoService;
	private ClienteService clienteService;

	@Autowired
	public DespachoController(final DespachoService despachoService, final ClienteService clienteService) {
		this.despachoService = despachoService;
		this.clienteService = clienteService;
	}

	@PostMapping
	ResponseEntity<DespachoType> crear(@PathVariable(name = "clienteRut") String clienteRut,
			@RequestBody InputDespachoCrear inputDTO) {

		Cliente cliente = clienteService.obtener(clienteRut);
		Despacho despacho = despachoService.crear(cliente, inputDTO);
		
		final DespachoType despachoType = new DespachoType();
		despachoType.setId(despacho.getId());
		despachoType.setClienteRut(despacho.getCliente().getRut());
		despachoType.setComuna(despacho.getComuna().getNombre());
		despachoType.setDireccion(despacho.getDireccion());
		despachoType.setEstado(despacho.getEstado());
		despachoType.setVentaId(despacho.getVenta().getId());

		return ResponseEntity.status(HttpStatus.CREATED).body(despachoType);
	}

	@GetMapping
	ResponseEntity<OutputDespachoConsultar> consultar(@PathVariable(name = "clienteRut") String clienteRut,
			@RequestParam(name = "partComuna", required = false) final String partComuna,
			@RequestParam(name = "estado", required = false) final EstadoDespacho estado,
			@RequestParam(name = "partDireccion", required = false) final String partDireccion,
			@RequestParam(name = "ventaId", required = false) final Long ventaId) {

		Cliente cliente = clienteService.obtener(clienteRut);
		List<Despacho> despachos = despachoService.consultar(cliente, estado, partComuna, partDireccion, ventaId);
		
		final OutputDespachoConsultar outputDTO = new OutputDespachoConsultar();
		for (Despacho despacho : despachos) {
			final DespachoType despachoType = new DespachoType();
			despachoType.setId(despacho.getId());
			despachoType.setClienteRut(despacho.getCliente().getRut());
			despachoType.setComuna(despacho.getComuna().getNombre());
			despachoType.setDireccion(despacho.getDireccion());
			despachoType.setEstado(despacho.getEstado());
			despachoType.setVentaId(despacho.getVenta().getId());
			
			outputDTO.getRegistros().add(despachoType);
		}

		return ResponseEntity.ok(outputDTO);
	}

	@GetMapping("/{id}")
	ResponseEntity<Despacho> obtener(@PathVariable(name = "clienteRut") String clienteRut,
			@PathVariable(name = "id") Long id) {
		Cliente cliente = clienteService.obtener(clienteRut);
		Despacho despacho = despachoService.obtener(cliente, id);
		
		return ResponseEntity.ok(despacho);
	}

	@PutMapping("/{id}")
	ResponseEntity<Boolean> actualizarEstado(@PathVariable(name = "clienteRut") String clienteRut,
			@PathVariable(name = "id") Long id, @RequestParam EstadoDespacho estado) {
		Cliente cliente = clienteService.obtener(clienteRut);
		Despacho despacho = despachoService.obtener(cliente, id);
		return ResponseEntity.ok(despachoService.actualizarEstado(despacho, estado));
	}

}
