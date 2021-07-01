package cl.duoc.ipy.websdl.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import cl.duoc.ipy.websdl.domain.Venta;
import cl.duoc.ipy.websdl.util.SearchCriteria;

public interface IVentaDAO {

	List<Venta> search(List<SearchCriteria> params);
}
