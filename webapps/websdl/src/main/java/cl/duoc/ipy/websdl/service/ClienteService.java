package cl.duoc.ipy.websdl.service;

import java.util.List;

import cl.duoc.ipy.websdl.domain.Cliente;

public interface ClienteService {

	Cliente crear(final Cliente clienteDTO);

	Cliente obtener(final Long id);

	List<Cliente> consultar(final String partRut, final String partNombre, final String partMail, final String comuna,
			final Integer offset, final Integer limit);

	Boolean actualizar(Long id, Cliente clienteDTO);

	Cliente obtener(final String clienteRut);
}
