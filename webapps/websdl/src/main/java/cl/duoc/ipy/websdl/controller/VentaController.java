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
import cl.duoc.ipy.websdl.dto.VentaType;
import cl.duoc.ipy.websdl.dto.input.InputVentaCrear;
import cl.duoc.ipy.websdl.dto.output.OutputVentaConsultar;
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
	ResponseEntity<VentaType> crear(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@RequestBody InputVentaCrear inputDTO) {

		Sucursal sucursal = sucursalService.obtener(codigoSucursal);
		Venta venta = ventaService.crear(sucursal, inputDTO);
		
		final VentaType ventaType = new VentaType();
		ventaType.setId(venta.getId());
		ventaType.setClienteRut(venta.getCliente().getRut());
		ventaType.setCodigoSucursal(venta.getSucursal().getCodigo());
		ventaType.setEstado(venta.getEstado());
		ventaType.setFecha(venta.getFecha());
		ventaType.setFolio(venta.getFolio());
		ventaType.setMontoBruto(venta.getMontoBruto());
		ventaType.setMontoIva(venta.getMontoIva());
		ventaType.setMontoTotal(venta.getMontoTotal());
		ventaType.setTipoDespacho(venta.getTipoDespacho());
		ventaType.setTipoDTE(venta.getTipoDte());
		ventaType.setTipoPago(venta.getTipoPago());
		ventaType.setVendedorEmail(venta.getVendedor().getMail());

		return ResponseEntity.status(HttpStatus.CREATED).body(ventaType);
	}

	@GetMapping
	ResponseEntity<OutputVentaConsultar> consultar(@PathVariable(name = "codigoSucursal") String codigoSucursal,
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
		
		OutputVentaConsultar outputDTO = new OutputVentaConsultar();
		for (Venta venta : ventas) {
			final VentaType ventaType = new VentaType();
			ventaType.setId(venta.getId());
			ventaType.setClienteRut(venta.getCliente().getRut());
			ventaType.setCodigoSucursal(venta.getSucursal().getCodigo());
			ventaType.setEstado(venta.getEstado());
			ventaType.setFecha(venta.getFecha());
			ventaType.setFolio(venta.getFolio());
			ventaType.setMontoBruto(venta.getMontoBruto());
			ventaType.setMontoIva(venta.getMontoIva());
			ventaType.setMontoTotal(venta.getMontoTotal());
			ventaType.setTipoDespacho(venta.getTipoDespacho());
			ventaType.setTipoDTE(venta.getTipoDte());
			ventaType.setTipoPago(venta.getTipoPago());
			ventaType.setVendedorEmail(venta.getVendedor().getMail());
			
			outputDTO.getRegistros().add(ventaType);
		}

		return ResponseEntity.ok(outputDTO);
	}

	@GetMapping("/{id}")
	ResponseEntity<VentaType> obtener(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@PathVariable(name = "id") Long id) {

		Sucursal sucursal = sucursalService.obtener(codigoSucursal);
		Venta venta = ventaService.obtener(sucursal, id);
		
		final VentaType ventaType = new VentaType();
		ventaType.setClienteRut(venta.getCliente().getRut());
		ventaType.setCodigoSucursal(venta.getSucursal().getCodigo());
		ventaType.setEstado(venta.getEstado());
		ventaType.setFecha(venta.getFecha());
		ventaType.setFolio(venta.getFolio());
		ventaType.setMontoBruto(venta.getMontoBruto());
		ventaType.setMontoIva(venta.getMontoIva());
		ventaType.setMontoTotal(venta.getMontoTotal());
		ventaType.setTipoDespacho(venta.getTipoDespacho());
		ventaType.setTipoDTE(venta.getTipoDte());
		ventaType.setTipoPago(venta.getTipoPago());
		ventaType.setVendedorEmail(venta.getVendedor().getMail());

		return ResponseEntity.ok(ventaType);
	}

	@PutMapping("/{id}")
	ResponseEntity<Boolean> actualizarEstado(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@PathVariable(name = "id") Long id, @RequestBody EstadoVenta estado) {

		Sucursal sucursal = sucursalService.obtener(codigoSucursal);

		return ResponseEntity.ok(ventaService.actualizar(sucursal, id, estado));
	}
}
