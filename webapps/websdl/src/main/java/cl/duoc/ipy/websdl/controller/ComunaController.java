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

import cl.duoc.ipy.websdl.domain.Comuna;
import cl.duoc.ipy.websdl.service.ComunaService;

@RestController
@RequestMapping("/comuna")
public class ComunaController {

	private ComunaService comunaService;
	
	@Autowired
	public ComunaController(final ComunaService comunaService) {
		this.comunaService = comunaService;
	}
	
	@PostMapping
	ResponseEntity<Comuna> crear(@RequestBody Comuna inputDTO) {
		Comuna comuna = comunaService.crear(inputDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(comuna);
	}
	
	@GetMapping
	ResponseEntity<List<Comuna>> consultar(@RequestParam(name = "partNombre", required = false) final String partNombre){
		
		List<Comuna> comunas = comunaService.consultar(partNombre);

		return ResponseEntity.ok(comunas);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<Comuna> obtener(@PathVariable(name = "id") Long id) {
		Comuna comuna = comunaService.obtener(id);
		return ResponseEntity.ok(comuna);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Boolean> actualizar(@PathVariable(name = "id") Long id, @RequestBody Comuna inputDTO){
		return ResponseEntity.ok(comunaService.actualizar(id, inputDTO));
	}
}
