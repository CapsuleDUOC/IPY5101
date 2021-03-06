package cl.duoc.ipy.websdl.repository;

import java.util.List;

import cl.duoc.ipy.websdl.domain.Reserva;
import cl.duoc.ipy.websdl.util.SearchCriteria;

public interface IReservaDAO {
	
	List<Reserva> search(List<SearchCriteria> params);
}
