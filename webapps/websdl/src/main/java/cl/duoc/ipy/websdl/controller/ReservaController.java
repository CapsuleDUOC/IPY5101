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
import cl.duoc.ipy.websdl.domain.Reserva;
import cl.duoc.ipy.websdl.dto.ReservaType;
import cl.duoc.ipy.websdl.dto.input.InputResercaCrear;
import cl.duoc.ipy.websdl.dto.output.OutputReservaConsultar;
import cl.duoc.ipy.websdl.enums.EstadoReserva;
import cl.duoc.ipy.websdl.service.ClienteService;
import cl.duoc.ipy.websdl.service.ReservaService;

@RestController
@RequestMapping("/{clienteRut}/reserva")
public class ReservaController {

	private ClienteService clienteService;
	private ReservaService reservaService;

	@Autowired
	public ReservaController(final ClienteService clienteService, final ReservaService reservaService) {
		this.reservaService = reservaService;
		this.clienteService = clienteService;
	}

	@PostMapping
	ResponseEntity<ReservaType> crear(@PathVariable(name = "clienteRut") String clienteRut, @RequestBody InputResercaCrear inputDTO) {

		Cliente cliente = clienteService.obtener(clienteRut);
		Reserva reserva = reservaService.crear(cliente, inputDTO);
		
		ReservaType reservaType = new ReservaType();
		reservaType.setId(reserva.getId());
		reservaType.setEstado(reserva.getEstado());
		reservaType.setVentaId(reserva.getVenta().getId());

		return ResponseEntity.status(HttpStatus.CREATED).body(reservaType);
	}

	@GetMapping
	ResponseEntity<OutputReservaConsultar> consultar(@PathVariable(name = "clienteRut") String clienteRut,
			@RequestParam(name = "estado", required = false) final EstadoReserva estado,
			@RequestParam(name = "ventaId", required = false) final Long ventaId) {

		Cliente cliente = clienteService.obtener(clienteRut);
		List<Reserva> reservas = reservaService.consultar(cliente, estado, ventaId);
		
		final OutputReservaConsultar outputDTO = new OutputReservaConsultar();
		for (Reserva reserva : reservas) {
			ReservaType reservaType = new ReservaType();
			reservaType.setId(reserva.getId());
			reservaType.setEstado(reserva.getEstado());
			reservaType.setVentaId(reserva.getVenta().getId());
			
			outputDTO.getRegistros().add(reservaType);
		}
				
		
		return ResponseEntity.ok(outputDTO);
	}

	@GetMapping("/{id}")
	ResponseEntity<Reserva> obtener(@PathVariable(name = "clienteRut") String clienteRut,
			@PathVariable(name = "id") Long id) {

		Cliente cliente = clienteService.obtener(clienteRut);
		Reserva reserva = reservaService.obtener(cliente, id);

		return ResponseEntity.ok(reserva);

	}

	@PutMapping("/{id}")
	ResponseEntity<Boolean> actualizarEstado(@PathVariable(name = "clienteRut") String clienteRut,
			@PathVariable(name = "id") Long id, @RequestParam EstadoReserva estado) {

		Cliente cliente = clienteService.obtener(clienteRut);
		Reserva reserva = reservaService.obtener(cliente, id);

		return ResponseEntity.ok(reservaService.actualizarEstado(reserva, estado));

	}
}
