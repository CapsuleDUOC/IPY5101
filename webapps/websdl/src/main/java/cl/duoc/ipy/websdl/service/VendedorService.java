package cl.duoc.ipy.websdl.service;

import java.util.List;

import cl.duoc.ipy.websdl.domain.Sucursal;
import cl.duoc.ipy.websdl.domain.Vendedor;

public interface VendedorService {

	Vendedor crear(final Sucursal sucursal, Vendedor inputDTO);

	List<Vendedor> consultar(final Sucursal sucursal, final String nombre, final String mail);

	Vendedor obtener(final Sucursal sucursal, final Long id);

	Boolean actualizar(final Sucursal sucursal, final Long id, final Vendedor inputDTO);

	Boolean eliminar(final Sucursal sucursal, final Long id);

	Vendedor obtener(final Sucursal sucursal, final String mail);

}