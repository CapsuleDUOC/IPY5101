package cl.duoc.ipy.websdl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ipy.websdl.domain.Sucursal;
import cl.duoc.ipy.websdl.service.SucursalService;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {

	private SucursalService sucursalService;
	
	@Autowired
	public SucursalController(final SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}
	
	@PostMapping
	ResponseEntity<Sucursal> crear(@RequestBody Sucursal inputDTO) {
		
		Sucursal sucursal = sucursalService.crear(inputDTO);
		
		return ResponseEntity.ok(sucursal);
	}
	
	@GetMapping
	ResponseEntity<List<Sucursal>> consultar(@RequestParam(name = "codigo", required = false) final String codigo,
			@RequestParam(name = "partDireccion", required = false) final String partDireccion,
			@RequestParam(name = "partEncargado", required = false) final String partEncargado,
			@RequestParam(name = "comuna", required = false) final String comuna){
		
		List<Sucursal> sucursales  = sucursalService.consultar(codigo, partDireccion, partEncargado, comuna);
		
		return ResponseEntity.ok(sucursales);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<Sucursal> obtener(@PathVariable(name = "id") Long id) {
		Sucursal sucursal = sucursalService.obtener(id);
		
		return ResponseEntity.ok(sucursal);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Boolean> actualizar(@PathVariable(name = "id") Long id, @RequestBody Sucursal inputDTO){
		return ResponseEntity.ok(sucursalService.actualizar(id, inputDTO));
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<Boolean> eliminar(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(sucursalService.eliminar(id));
	}
	
}
