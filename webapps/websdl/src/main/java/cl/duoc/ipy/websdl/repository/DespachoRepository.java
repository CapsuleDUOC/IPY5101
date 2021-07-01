package cl.duoc.ipy.websdl.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.duoc.ipy.websdl.domain.Cliente;
import cl.duoc.ipy.websdl.domain.Despacho;

public interface DespachoRepository extends PagingAndSortingRepository<Despacho, Long>{

	Optional<Despacho> findByClienteAndId(final Cliente cliente, final Long id);

}
