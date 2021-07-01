package cl.duoc.ipy.websdl.service;

import java.util.List;

import cl.duoc.ipy.websdl.domain.Sucursal;
import cl.duoc.ipy.websdl.domain.SucursalLibro;

public interface SucursalLibroService {

	SucursalLibro crear(final Sucursal sucursal, final SucursalLibro inputDTO);

	List<SucursalLibro> consultar(final Sucursal sucursal);

	SucursalLibro obtener(final Sucursal sucursal, final Long id);

	Boolean actualizarStock(final Sucursal sucursal, final Long id, final Integer stock);

}
