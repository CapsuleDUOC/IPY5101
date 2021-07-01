package cl.duoc.ipy.websdl.service;

import java.util.List;

import cl.duoc.ipy.websdl.domain.Venta;
import cl.duoc.ipy.websdl.domain.VentaBitacora;

public interface VentaBitacoraService {

	List<VentaBitacora> consultar(final Venta venta);

}
