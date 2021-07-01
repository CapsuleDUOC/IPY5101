package cl.duoc.ipy.websdl.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.duoc.ipy.websdl.domain.Venta;
import cl.duoc.ipy.websdl.domain.VentaBitacora;

public interface VentaBitacoraRepository extends PagingAndSortingRepository<VentaBitacora, Long>{

	List<VentaBitacora> findByVenta(final Venta venta);

}
