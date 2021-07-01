package cl.duoc.ipy.websdl.service;

import java.math.BigInteger;
import java.util.List;

import cl.duoc.ipy.websdl.domain.Libro;
import cl.duoc.ipy.websdl.domain.Venta;
import cl.duoc.ipy.websdl.domain.VentaLibro;

public interface VentaLibroService {

	List<VentaLibro> consultar(final Venta venta);

	VentaLibro crear(final Venta venta, final Libro libro, final Integer cantidad, final BigInteger precioUnitario);

}
