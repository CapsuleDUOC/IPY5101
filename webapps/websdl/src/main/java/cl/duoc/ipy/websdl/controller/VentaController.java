package cl.duoc.ipy.websdl.controller;

import java.time.LocalDate;
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

import cl.duoc.ipy.websdl.domain.Sucursal;
import cl.duoc.ipy.websdl.domain.Venta;
import cl.duoc.ipy.websdl.enums.EstadoVenta;
import cl.duoc.ipy.websdl.enums.TipoDespacho;
import cl.duoc.ipy.websdl.enums.TipoPago;
import cl.duoc.ipy.websdl.service.SucursalService;
import cl.duoc.ipy.websdl.service.VentaService;

@RestController
@RequestMapping("/{codigoSucursal}/venta")
public class VentaController {

	private SucursalService sucursalService;
	private VentaService ventaService;

	@Autowired
	public VentaController(final SucursalService sucursalService, final VentaService ventaService) {
		this.sucursalService = sucursalService;
		this.ventaService = ventaService;
	}

	@PostMapping
	ResponseEntity<Venta> crear(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@RequestBody Venta inputDTO) {

		Sucursal sucursal = sucursalService.obtener(codigoSucursal);
		Venta venta = ventaService.crear(sucursal, inputDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(venta);
	}

	@GetMapping
	ResponseEntity<List<Venta>> consultar(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@RequestParam(name = "tipoDTE", required = false) final String tipoDTE,
			@RequestParam(name = "folio", required = false) final Long folio,
			@RequestParam(name = "fechaEmision", required = false) final LocalDate fechaEmision,
			@RequestParam(name = "clienteRut", required = false) final String clienteRut,
			@RequestParam(name = "vendedorEmail", required = false) final String vendedorEmail,
			@RequestParam(name = "estado", required = false) final EstadoVenta estado,
			@RequestParam(name = "tipoPago", required = false) final TipoPago tipoPago,
			@RequestParam(name = "tipoDespacho", required = false) final TipoDespacho tipoDespacho) {

		Sucursal sucursal = sucursalService.obtener(codigoSucursal);
		List<Venta> ventas = ventaService.consultar(sucursal, tipoDTE, folio, fechaEmision, clienteRut, vendedorEmail,
				estado, tipoPago, tipoDespacho);

		return ResponseEntity.ok(ventas);
	}

	@GetMapping("/{id}")
	ResponseEntity<Venta> obtener(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@PathVariable(name = "id") Long id) {

		Sucursal sucursal = sucursalService.obtener(codigoSucursal);
		Venta venta = ventaService.obtener(sucursal, id);

		return ResponseEntity.ok(venta);
	}

	@PutMapping("/{id}")
	ResponseEntity<Boolean> actualizarEstado(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@PathVariable(name = "id") Long id, @RequestBody EstadoVenta estado) {

		Sucursal sucursal = sucursalService.obtener(codigoSucursal);

		return ResponseEntity.ok(ventaService.actualizar(sucursal, id, estado));
	}
}
