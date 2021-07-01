package cl.duoc.ipy.websdl.service;

import java.util.List;

import cl.duoc.ipy.websdl.domain.Cliente;
import cl.duoc.ipy.websdl.domain.Reserva;
import cl.duoc.ipy.websdl.enums.EstadoReserva;

public interface ReservaService {

	Reserva crear(final Cliente cliente, final Reserva inputDTO);

	Reserva obtener(final Cliente cliente, final Long id);

	Boolean actualizarEstado(final Reserva reserva, final EstadoReserva estado);

	List<Reserva> consultar(final Cliente cliente, final EstadoReserva estado, final Long ventaId, Integer offset, Integer limit);

}