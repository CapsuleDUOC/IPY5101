package cl.duoc.ipy.websdl.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.duoc.ipy.websdl.domain.Sucursal;

public interface SucursalRepository extends PagingAndSortingRepository<Sucursal, Long>{

	Optional<Sucursal> findByCodigo(final String codigo);

}
