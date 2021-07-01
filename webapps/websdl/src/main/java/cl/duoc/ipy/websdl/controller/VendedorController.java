package cl.duoc.ipy.websdl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import cl.duoc.ipy.websdl.domain.Vendedor;
import cl.duoc.ipy.websdl.service.SucursalService;
import cl.duoc.ipy.websdl.service.VendedorService;

@RestController
@RequestMapping("/{codigoSucursal}/vendedor")
public class VendedorController {

	private SucursalService sucursalService;
	private VendedorService vendedorService;

	@Autowired
	public VendedorController(final SucursalService sucursalService, final VendedorService vendedorService) {
		this.sucursalService = sucursalService;
		this.vendedorService = vendedorService;
	}

	@PostMapping
	ResponseEntity<Vendedor> crear(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@RequestBody Vendedor inputDTO) {
		Sucursal sucursal = sucursalService.obtener(codigoSucursal);
		Vendedor vendedor = vendedorService.crear(sucursal, inputDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(vendedor);

	}

	@GetMapping
	ResponseEntity<List<Vendedor>> consultar(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@RequestParam(name = "nombre", required = false) final String nombre,
			@RequestParam(name = "mail", required = false) final String mail) {

		Sucursal sucursal = sucursalService.obtener(codigoSucursal);
		List<Vendedor> vendedores = vendedorService.consultar(sucursal, nombre, mail);

		return ResponseEntity.ok(vendedores);
	}

	@GetMapping("/{id}")
	ResponseEntity<Vendedor> obtener(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@PathVariable(name = "id") Long id) {

		Sucursal sucursal = sucursalService.obtener(codigoSucursal);
		Vendedor vendedor = vendedorService.obtener(sucursal, id);

		return ResponseEntity.ok(vendedor);
	}

	@PutMapping("/{id}")
	ResponseEntity<Boolean> actualizar(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@PathVariable(name = "id") Long id, @RequestBody Vendedor inputDTO) {

		Sucursal sucursal = sucursalService.obtener(codigoSucursal);

		return ResponseEntity.ok(vendedorService.actualizar(sucursal, id, inputDTO));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<Boolean> eliminar(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@PathVariable(name = "id") Long id) {

		Sucursal sucursal = sucursalService.obtener(codigoSucursal);

		return ResponseEntity.ok(vendedorService.eliminar(sucursal, id));
	}

}
