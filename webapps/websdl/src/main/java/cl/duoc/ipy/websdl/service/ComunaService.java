package cl.duoc.ipy.websdl.service;

import java.util.List;

import cl.duoc.ipy.websdl.domain.Comuna;

public interface ComunaService {

	Comuna crear(final Comuna inputDTO);

	List<Comuna> consultar(final String partNombre);

	Comuna obtener(final Long id);

	Boolean actualizar(final Long id, final Comuna inputDTO);

	Comuna obtener(final String nombre);

}
