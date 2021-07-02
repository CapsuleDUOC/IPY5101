package cl.duoc.ipy.websdl.service;

import java.util.List;

import cl.duoc.ipy.websdl.domain.Cliente;
import cl.duoc.ipy.websdl.domain.Despacho;
import cl.duoc.ipy.websdl.dto.input.InputDespachoCrear;
import cl.duoc.ipy.websdl.enums.EstadoDespacho;

public interface DespachoService {

	Despacho crear(final Cliente cliente, final InputDespachoCrear inputDTO);

	List<Despacho> consultar(final Cliente cliente, final EstadoDespacho estadp, final String nombreComuna,
			final String partDireccion, final Long ventaId, Integer offset, Integer limit);

	Despacho obtener(final Cliente cliente, final Long id);

	Boolean actualizarEstado(final Despacho despacho, final EstadoDespacho estado);

}
