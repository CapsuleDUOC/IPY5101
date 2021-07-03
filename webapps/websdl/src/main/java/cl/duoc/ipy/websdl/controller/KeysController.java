package cl.duoc.ipy.websdl.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ipy.websdl.enums.EstadoDespacho;
import cl.duoc.ipy.websdl.enums.EstadoReserva;
import cl.duoc.ipy.websdl.enums.EstadoVenta;
import cl.duoc.ipy.websdl.enums.TipoDespacho;
import cl.duoc.ipy.websdl.enums.TipoPago;

@RestController
@RequestMapping("/keys")
public class KeysController {
 
	@Autowired
	public KeysController() {
		
	}
	
	@GetMapping("/tipoDespacho")
	ResponseEntity<List<String>> getTipoDespacho() {
		
		final List<String> response = new ArrayList<>();
		for (TipoDespacho value : TipoDespacho.values()) {
			response.add(value.name());
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/estadoDespacho")
	ResponseEntity<List<String>> getEstadoDespacho() {
		
		final List<String> response = new ArrayList<>();
		for (EstadoDespacho value : EstadoDespacho.values()) {
			response.add(value.name());
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/estadoReserva")
	ResponseEntity<List<String>> getEstadoReserva() {
		
		final List<String> response = new ArrayList<>();
		for (EstadoReserva value : EstadoReserva.values()) {
			response.add(value.name());
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/estadoVenta")
	ResponseEntity<List<String>> getEstadoVenta() {
		
		final List<String> response = new ArrayList<>();
		for (EstadoVenta value : EstadoVenta.values()) {
			response.add(value.name());
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/tipoPago")
	ResponseEntity<List<String>> getTipoPago() {
		
		final List<String> response = new ArrayList<>();
		for (TipoPago value : TipoPago.values()) {
			response.add(value.name());
		}
		
		return ResponseEntity.ok(response);
	}
}
	
