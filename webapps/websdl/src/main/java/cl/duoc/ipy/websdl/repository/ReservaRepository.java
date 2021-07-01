package cl.duoc.ipy.websdl.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.duoc.ipy.websdl.domain.Cliente;
import cl.duoc.ipy.websdl.domain.Reserva;

public interface ReservaRepository extends PagingAndSortingRepository<Reserva, Long>{

	Optional<Reserva> findByClienteAndId(final Cliente cliente, final Long id);

}
