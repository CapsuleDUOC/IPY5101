package cl.duoc.ipy.websdl.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.duoc.ipy.websdl.domain.Libro;
import cl.duoc.ipy.websdl.domain.Sucursal;
import cl.duoc.ipy.websdl.domain.SucursalLibro;

public interface SucursalLibroRepository extends PagingAndSortingRepository<SucursalLibro, Long>{

	Optional<SucursalLibro> findBySucursalAndLibro(final Sucursal sucursal, final Libro libro);

	Optional<SucursalLibro> findBySucursalAndId(final Sucursal sucursal, final Long id);

}
