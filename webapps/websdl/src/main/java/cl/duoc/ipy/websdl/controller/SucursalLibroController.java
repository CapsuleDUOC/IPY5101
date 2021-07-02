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
import cl.duoc.ipy.websdl.dto.SucursalLibroType;
import cl.duoc.ipy.websdl.dto.input.InputSucursalLibroCrear;
import cl.duoc.ipy.websdl.dto.output.OutputSucursalLibroConsultar;
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
	ResponseEntity<SucursalLibroType> crear(@PathVariable(name = "codigoSucursal") String codigoSucursal, @RequestBody InputSucursalLibroCrear inputDTO){
		
		Sucursal sucursal = sucursalService.obtener(codigoSucursal);
		SucursalLibro sucursalLibro = sucursalLibroService.crear(sucursal, inputDTO);
		
		final SucursalLibroType sucursalLibroType = new SucursalLibroType();
		sucursalLibroType.setCodigoSucursal(sucursalLibro.getSucursal().getCodigo());
		sucursalLibroType.setId(sucursalLibro.getId());
		sucursalLibroType.setNombreLibro(sucursalLibro.getLibro().getNombre());
		sucursalLibro.setStock(sucursalLibro.getStock());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(sucursalLibroType);

	}
	
	@GetMapping
	ResponseEntity<OutputSucursalLibroConsultar> consultar(@PathVariable(name = "codigoSucursal") String codigoSucursal){
		
		Sucursal sucursal = sucursalService.obtener(codigoSucursal);
		List<SucursalLibro> sucursalLibros = sucursalLibroService.consultar(sucursal);
		
		final OutputSucursalLibroConsultar outputDTO = new OutputSucursalLibroConsultar();
		for (SucursalLibro sucursalLibro : sucursalLibros) {
			final SucursalLibroType sucursalLibroType = new SucursalLibroType();
			sucursalLibroType.setCodigoSucursal(sucursalLibro.getSucursal().getCodigo());
			sucursalLibroType.setId(sucursalLibro.getId());
			sucursalLibroType.setNombreLibro(sucursalLibro.getLibro().getNombre());
			sucursalLibro.setStock(sucursalLibro.getStock());
			
			outputDTO.getRegistros().add(sucursalLibroType);
		}
		
		return ResponseEntity.ok(outputDTO);
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
