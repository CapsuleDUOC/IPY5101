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
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ipy.websdl.domain.Sucursal;
import cl.duoc.ipy.websdl.domain.SucursalLibro;
import cl.duoc.ipy.websdl.service.SucursalLibroService;
import cl.duoc.ipy.websdl.service.SucursalService;

@RestController
@RequestMapping("/{codigoSucursal}/libro")
public class SucursalLibroController {

	private SucursalService sucursalService;
	private SucursalLibroService sucursalLibroService;
	
	@Autowired
	public SucursalLibroController(final SucursalService sucursalService, final SucursalLibroService sucursalLibroService) {
		this.sucursalLibroService = sucursalLibroService;
		this.sucursalService = sucursalService;
	}
	
	@PostMapping
	ResponseEntity<SucursalLibro> crear(@PathVariable(name = "codigoSucursal") String codigoSucursal, @RequestBody SucursalLibro inputDTO){
		
		Sucursal sucursal = sucursalService.obtener(codigoSucursal);
		SucursalLibro sucursalLibro = sucursalLibroService.crear(sucursal, inputDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(sucursalLibro);

	}
	
	@GetMapping
	ResponseEntity<List<SucursalLibro>> consultar(@PathVariable(name = "codigoSucursal") String codigoSucursal){
		
		Sucursal sucursal = sucursalService.obtener(codigoSucursal);
		List<SucursalLibro> sucursalLibros = sucursalLibroService.consultar(sucursal);
		
		return ResponseEntity.ok(sucursalLibros);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<SucursalLibro> obtener(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@PathVariable(name = "id") Long id) {
		
		Sucursal sucursal = sucursalService.obtener(codigoSucursal);
		SucursalLibro sucursalLibro = sucursalLibroService.obtener(sucursal, id);
		
		return ResponseEntity.ok(sucursalLibro);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Boolean> actualizarStock(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@PathVariable(name = "id") Long id,  Integer stock) {

		Sucursal sucursal = sucursalService.obtener(codigoSucursal);

		return ResponseEntity.ok(sucursalLibroService.actualizarStock(sucursal, id, stock));
	}
}
