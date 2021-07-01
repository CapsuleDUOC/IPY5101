package cl.duoc.ipy.websdl.repository;

import java.util.List;

import cl.duoc.ipy.websdl.domain.Vendedor;
import cl.duoc.ipy.websdl.util.SearchCriteria;

public interface IVendedorDAO {

	List<Vendedor> search(List<SearchCriteria> params);

}
