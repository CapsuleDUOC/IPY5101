package cl.duoc.ipy.websdl.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.duoc.ipy.websdl.domain.Libro;

public interface LibroRepository extends PagingAndSortingRepository<Libro, Long>{

	Optional<Libro> findByIsbn(final String isbn);

}
