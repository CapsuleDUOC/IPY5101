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
import cl.duoc.ipy.websdl.domain.VentaBitacora;
import cl.duoc.ipy.websdl.dto.BitacoraType;
import cl.duoc.ipy.websdl.dto.output.OutputBitacoraConsultar;
import cl.duoc.ipy.websdl.service.SucursalService;
import cl.duoc.ipy.websdl.service.VentaBitacoraService;
import cl.duoc.ipy.websdl.service.VentaService;

@RestController
@RequestMapping("/{codigoSucursal}/venta/bitacora")
public class VentaBitacoraController {

	private SucursalService sucursalService;
	private VentaService ventaService;
	private VentaBitacoraService ventaBitacoraService;

	@Autowired
	public VentaBitacoraController(final SucursalService sucursalService, final VentaService ventaService,
			final VentaBitacoraService ventaBitacoraService) {
		this.sucursalService = sucursalService;
		this.ventaService = ventaService;
		this.ventaBitacoraService = ventaBitacoraService;
	}

	@GetMapping("/{ventaId}")
	ResponseEntity<OutputBitacoraConsultar> consultar(@PathVariable(name = "codigoSucursal") String codigoSucursal,
			@PathVariable(name = "ventaId") Long ventaId) {

		Sucursal sucursal = sucursalService.obtener(codigoSucursal);
		Venta venta = ventaService.obtener(sucursal, ventaId);

		List<VentaBitacora> bitacoras = ventaBitacoraService.consultar(venta);
		
		final OutputBitacoraConsultar outputDTO = new OutputBitacoraConsultar();
		for (VentaBitacora bitacora : bitacoras) {
			final BitacoraType bitacoraType = new BitacoraType();
			bitacoraType.setId(bitacora.getId());
			bitacoraType.setInstante(bitacora.getRegistroInstante());
			bitacoraType.setNota(bitacora.getNota());
			
			outputDTO.getRegistros().add(bitacoraType);
		}
		
		return ResponseEntity.ok(outputDTO);
	}
}
