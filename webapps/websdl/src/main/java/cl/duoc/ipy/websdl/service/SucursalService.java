package cl.duoc.ipy.websdl.service;

import java.util.List;

import cl.duoc.ipy.websdl.domain.Sucursal;

public interface SucursalService {

	Sucursal crear(final Sucursal inputDTO);

	List<Sucursal> consultar(final String codigo, final String partDireccion, final String partEncargado, final String comuna);

	Sucursal obtener(final Long id);

	Boolean actualizar(final Long id, final Sucursal inputDTO);

	Boolean eliminar(final Long id);
	
	Sucursal obtener(final String codigo);


}
