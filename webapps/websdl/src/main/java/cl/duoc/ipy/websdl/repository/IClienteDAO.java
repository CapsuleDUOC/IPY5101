package cl.duoc.ipy.websdl.repository;

import java.util.List;

import cl.duoc.ipy.websdl.domain.Cliente;
import cl.duoc.ipy.websdl.util.SearchCriteria;

public interface IClienteDAO {

	List<Cliente> search(List<SearchCriteria> params);
}
