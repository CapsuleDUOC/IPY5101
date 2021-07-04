package cl.duoc.ipy.websdl.repository;

import java.util.List;

import cl.duoc.ipy.websdl.domain.Despacho;
import cl.duoc.ipy.websdl.util.SearchCriteria;

public interface IDespachoDAO {

	List<Despacho> search(List<SearchCriteria> params);
}
