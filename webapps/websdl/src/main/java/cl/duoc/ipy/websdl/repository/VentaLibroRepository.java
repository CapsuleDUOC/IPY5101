package cl.duoc.ipy.websdl.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.duoc.ipy.websdl.domain.Venta;
import cl.duoc.ipy.websdl.domain.VentaLibro;

public interface VentaLibroRepository extends PagingAndSortingRepository<VentaLibro, Long>{

	List<VentaLibro> findByVenta(final Venta venta);

}
