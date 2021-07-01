package cl.duoc.ipy.websdl.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.duoc.ipy.websdl.domain.Cliente;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long>{

	Optional<Cliente> findByRut(final String clienteRut);

}
