package cl.duoc.ipy.websdl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cl.duoc.ipy.websdl.domain.Cliente;
import cl.duoc.ipy.websdl.domain.Comuna;
import cl.duoc.ipy.websdl.domain.Despacho;
import cl.duoc.ipy.websdl.domain.Venta;
import cl.duoc.ipy.websdl.dto.input.InputDespachoCrear;
import cl.duoc.ipy.websdl.enums.EstadoDespacho;
import cl.duoc.ipy.websdl.repository.DespachoRepository;
import cl.duoc.ipy.websdl.repository.IDespachoDAO;
import cl.duoc.ipy.websdl.service.ComunaService;
import cl.duoc.ipy.websdl.service.DespachoService;
import cl.duoc.ipy.websdl.service.VentaService;
import cl.duoc.ipy.websdl.util.SearchCriteria;

@Service
public class DespachoServiceImpl implements DespachoService {

	private DespachoRepository despachoRepository;
	private ComunaService comunaService;
	private VentaService ventaService;
	private IDespachoDAO despachoDAO;

	@Autowired
	public DespachoServiceImpl(final DespachoRepository despachoRepository, final ComunaService comunaService,
			final VentaService ventaService, final IDespachoDAO despachoDAO) {
		this.despachoRepository = despachoRepository;
		this.comunaService = comunaService;
		this.ventaService = ventaService;
		this.despachoDAO = despachoDAO;
	}

	@Override
	public Despacho crear(Cliente cliente, InputDespachoCrear inputDTO) {

		Venta venta = ventaService.obtener(inputDTO.getVentaId());

		Despacho despacho = new Despacho();
		despacho.setCliente(cliente);
		despacho.setComuna(comunaService.obtener(inputDTO.getComuna()));
		despacho.setDireccion(inputDTO.getDireccion());
		despacho.setEstado(inputDTO.getEstado());
		despacho.setVenta(venta);

		return despachoRepository.save(despacho);
	}

	@Override
	public List<Despacho> consultar(Cliente cliente, EstadoDespacho estado, String nombreComuna, String partDireccion,
			Long ventaId) {

		List<SearchCriteria> params = new ArrayList<>();

		params.add(new SearchCriteria("cliente", null, SearchCriteria.OPERATION.equal, cliente, null));
		if (estado != null)
			params.add(new SearchCriteria("estado", null, SearchCriteria.OPERATION.equal, estado, null));
		if (nombreComuna != null) {
			Comuna comuna = comunaService.obtener(nombreComuna);
			params.add(new SearchCriteria("comuna", null, SearchCriteria.OPERATION.equal, comuna, null));
		}
		if (partDireccion != null)
			params.add(new SearchCriteria("direccion", null, SearchCriteria.OPERATION.like, partDireccion, null));
		if (ventaId != null) {
			Venta venta = ventaService.obtener(ventaId);
			params.add(new SearchCriteria("venta", null, SearchCriteria.OPERATION.equal, venta, null));
		}

		return despachoDAO.search(params);

	}

	@Override
	public Despacho obtener(Cliente cliente, Long id) {

		Optional<Despacho> _despacho = despachoRepository.findByClienteAndId(cliente, id);
		Assert.isTrue(_despacho.isPresent(), "No existe despacho con dicho ID");

		return _despacho.get();
	}

	@Override
	public Boolean actualizarEstado(Despacho despacho, EstadoDespacho estado) {

		despacho.setEstado(estado);
		;

		despachoRepository.save(despacho);

		return Boolean.TRUE;
	}
}
