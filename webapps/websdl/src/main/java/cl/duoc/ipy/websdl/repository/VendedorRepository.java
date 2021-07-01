package cl.duoc.ipy.websdl.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.duoc.ipy.websdl.domain.Sucursal;
import cl.duoc.ipy.websdl.domain.Vendedor;

public interface VendedorRepository extends PagingAndSortingRepository<Vendedor, Long>{

	Optional<Vendedor> findByMail(final String mail);

	Optional<Vendedor> findBySucursalAndId(final Sucursal sucursal, final Long id);

	Optional<Vendedor> findBySucursalAndMail(final Sucursal sucursal, final String mail);

}
