package cl.duoc.ipy.websdl.service;

import java.util.List;

import cl.duoc.ipy.websdl.domain.Libro;

public interface LibroService {

	Libro crear(final Libro inputDTO);

	List<Libro> consultar(final String isbn, final String partNombre, final String partAutor,
			final String partEditorial);

	Libro obtener(final Long id);

	Boolean actualizar(final Long id, final Libro inputDTO);

	Libro obtener(final String isbn);

}
