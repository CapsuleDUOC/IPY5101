package cl.duoc.ipy.websdl.repository;

import java.util.List;

import cl.duoc.ipy.websdl.domain.Sucursal;
import cl.duoc.ipy.websdl.util.SearchCriteria;

public interface ISucursalDAO {
	
	List<Sucursal> search(List<SearchCriteria> params);

}
