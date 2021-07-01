package cl.duoc.ipy.websdl.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import cl.duoc.ipy.websdl.domain.Sucursal;
import cl.duoc.ipy.websdl.domain.Venta;

public interface VentaRepository extends PagingAndSortingRepository<Venta, Long>{

	Optional<Venta> findBySucursalAndTipoDteAndFolio(final Sucursal sucursal, final Integer tipoDte, final Long folio);

	Optional<Venta> findBySucursalAndId(final Sucursal sucursal, final Long id);

}
