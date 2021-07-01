package cl.duoc.ipy.websdl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import cl.duoc.ipy.websdl.domain.Comuna;

public interface ComunaRepository extends PagingAndSortingRepository<Comuna, Long>{

	Optional<Comuna> findByNombre(final String nombre);

	@Query("SELECT a FROM Comuna a WHERE a.nombre LIKE :partNombre")
	List<Comuna> findByNombreLike(String partNombre);

}
