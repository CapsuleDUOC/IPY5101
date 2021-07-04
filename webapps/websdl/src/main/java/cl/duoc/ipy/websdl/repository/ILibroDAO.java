package cl.duoc.ipy.websdl.repository;

import java.util.List;

import cl.duoc.ipy.websdl.domain.Libro;
import cl.duoc.ipy.websdl.util.SearchCriteria;

public interface ILibroDAO {

	List<Libro> search(List<SearchCriteria> params);
}
