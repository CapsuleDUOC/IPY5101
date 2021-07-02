package cl.duoc.ipy.websdl.service;

import java.time.LocalDate;
import java.util.List;

import cl.duoc.ipy.websdl.domain.Sucursal;
import cl.duoc.ipy.websdl.domain.Venta;
import cl.duoc.ipy.websdl.dto.input.InputVentaCrear;
import cl.duoc.ipy.websdl.enums.EstadoVenta;
import cl.duoc.ipy.websdl.enums.TipoDespacho;
import cl.duoc.ipy.websdl.enums.TipoPago;

public interface VentaService {

	Venta crear(final Sucursal sucursal, final InputVentaCrear inputDTO);

	List<Venta> consultar(final Sucursal sucursal, final String tipoDTE, final Long folio,
			final LocalDate fechaEmision, final String clienteRut, final String vendedorEmail, final EstadoVenta estado,
			final TipoPago tipoPago, final TipoDespacho tipoDespacho);

	Venta obtener(final Sucursal sucursal, final Long id);

	Boolean actualizar(final Sucursal sucursal, final Long id, final EstadoVenta estado);

	Venta obtener(final Long id);

}
