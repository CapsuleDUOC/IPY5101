package cl.duoc.ipy.websdl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ipy.websdl.domain.Sucursal;
import cl.duoc.ipy.websdl.domain.Venta;
import cl.duoc.ipy.websdl.domain.VentaLibro;
import cl.duoc.ipy.websdl.dto.VentaLibroType;
import cl.duoc.ipy.websdl.dto.output.OutputVentaLibroConsultar;
import cl.duoc.ipy.websdl.service.SucursalService;
import cl.duoc.ipy.websdl.service.VentaLibroService;
import cl.duoc.ipy.websdl.service.VentaService;

@RestController
@RequestMapping("/{codigoSucursal}/venta/libro")
public class VentaLibroController {

	private SucursalService sucursalService;
	private VentaService ventaService;
	private VentaLibroService ventaLibroService;

	@Autowired
	public VentaLibroController(final SucursalService sucursalService, final VentaService ventaService,
			final VentaLibroService ventaLibroService) {
		this.sucursalService = sucursalService;
		this.ventaService = ventaService;
		this.ventaLibroService = ventaLibroService;
	}

	@GetMapping("/{ventaId}")
	ResponseEntity<OutputVentaLibroConsultar> consultar(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@PathVariable(name = "ventaId") Long ventaId) {

		Sucursal sucursal = sucursalService.obtener(codigoSucursal);
		Venta venta = ventaService.obtener(sucursal, ventaId);

		List<VentaLibro> ventaLibros = ventaLibroService.consultar(venta);
		
		final OutputVentaLibroConsultar outputDTO = new OutputVentaLibroConsultar();
		for (VentaLibro ventaLibro : ventaLibros) {
			
			VentaLibroType ventaLibroType = new VentaLibroType();
			
			ventaLibroType.setCantidad(ventaLibro.getCantidad());
			ventaLibroType.setId(ventaLibro.getId());
			ventaLibroType.setNombreLibro(ventaLibro.getLibro().getNombre());
			ventaLibroType.setPrecioLUnitario(ventaLibro.getPrecioUnitario());
			ventaLibroType.setVentaId(ventaLibro.getVenta().getId());
		}
		
		return ResponseEntity.ok(outputDTO);
	}
}
