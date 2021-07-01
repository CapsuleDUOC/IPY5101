package cl.duoc.ipy.websdl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cl.duoc.ipy.websdl.domain.Cliente;
import cl.duoc.ipy.websdl.domain.Reserva;
import cl.duoc.ipy.websdl.domain.Venta;
import cl.duoc.ipy.websdl.enums.EstadoReserva;
import cl.duoc.ipy.websdl.repository.IReservaDAO;
import cl.duoc.ipy.websdl.repository.ReservaRepository;
import cl.duoc.ipy.websdl.service.ReservaService;
import cl.duoc.ipy.websdl.service.VentaService;
import cl.duoc.ipy.websdl.util.SearchCriteria;

@Service
public class ReservaServiceImpl implements ReservaService {

	private ReservaRepository reservaRepository;
	private VentaService ventaService;
	private IReservaDAO reservaDAO;

	@Autowired
	public ReservaServiceImpl(final ReservaRepository reservaRepository, final VentaService ventaService,
			final IReservaDAO reservaDAO) {
		this.reservaRepository = reservaRepository;
		this.ventaService = ventaService;
		this.reservaDAO = reservaDAO;
	}

	@Override
	public Reserva crear(Cliente cliente, Reserva inputDTO) {

		Reserva reserva = new Reserva();
		reserva.setCliente(cliente);
		reserva.setEstado(inputDTO.getEstado());

		if (inputDTO.getVenta() != null)
			reserva.setVenta(ventaService.obtener(inputDTO.getVenta().getId()));

		return reservaRepository.save(reserva);
	}

	@Override
	public Reserva obtener(Cliente cliente, Long id) {

		Optional<Reserva> _reserva = reservaRepository.findByClienteAndId(cliente, id);
		Assert.isTrue(_reserva.isPresent(), "No existe reserva para dicho ID");

		return _reserva.get();
	}

	@Override
	public Boolean actualizarEstado(Reserva reserva, EstadoReserva estado) {

		reserva.setEstado(estado);

		reservaRepository.save(reserva);

		return Boolean.TRUE;
	}

	@Override
	public List<Reserva> consultar(Cliente cliente, EstadoReserva estado, Long ventaId, Integer offset, Integer limit) {

		List<SearchCriteria> params = new ArrayList<>();

		params.add(new SearchCriteria("cliente", null, SearchCriteria.OPERATION.equal, cliente, null));
		if (estado != null)
			params.add(new SearchCriteria("estado", null, SearchCriteria.OPERATION.equal, estado, null));
		if (ventaId != null) {
			Venta venta = ventaService.obtener(ventaId);
			params.add(new SearchCriteria("venta", null, SearchCriteria.OPERATION.equal, venta, null));
		}

		return reservaDAO.search(params, PageRequest.of(offset, limit));
	}
}
