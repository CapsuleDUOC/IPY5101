package cl.duoc.ipy.websdl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cl.duoc.ipy.websdl.domain.Cliente;
import cl.duoc.ipy.websdl.domain.Comuna;
import cl.duoc.ipy.websdl.repository.ClienteRepository;
import cl.duoc.ipy.websdl.repository.IClienteDAO;
import cl.duoc.ipy.websdl.service.ClienteService;
import cl.duoc.ipy.websdl.service.ComunaService;
import cl.duoc.ipy.websdl.util.SearchCriteria;

@Service
public class ClienteServiceImpl implements ClienteService {

	private ClienteRepository clienteRepository;
	private ComunaService comunaService;
	private IClienteDAO clienteDAO;

	@Autowired
	public ClienteServiceImpl(final ClienteRepository clienteRepository, final ComunaService comunaService, final IClienteDAO clienteDAO) {
		this.clienteRepository = clienteRepository;
		this.comunaService = comunaService;
		this.clienteDAO = clienteDAO;
	}

	@Override
	public Cliente crear(Cliente inputDTO) {

		Cliente cliente = new Cliente();
		cliente.setComuna(comunaService.obtener(inputDTO.getComuna().getNombre()));
		cliente.setDireccion(inputDTO.getDireccion());
		cliente.setMail(inputDTO.getMail());
		cliente.setNombre(inputDTO.getNombre());
		cliente.setRut(inputDTO.getRut());
		cliente.setTelefono(inputDTO.getTelefono());

		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente obtener(Long id) {

		Optional<Cliente> _cliente = clienteRepository.findById(id);
		Assert.isTrue(_cliente.isPresent(), "No existe Cliente con dicho ID");

		return _cliente.get();
	}

	@Override
	public List<Cliente> consultar(String partRut, String partNombre, String partMail, String comunaStr) {
		
		List<SearchCriteria> params = new ArrayList<>();
		
		if (partRut != null)
			params.add(new SearchCriteria("rut", null, SearchCriteria.OPERATION.like, partRut, null));
		if (partNombre != null)
			params.add(new SearchCriteria("nombre", null, SearchCriteria.OPERATION.like, partNombre, null));
		if (comunaStr != null) {
			Comuna comuna = comunaService.obtener(comunaStr);
			params.add(new SearchCriteria("comuna", null, SearchCriteria.OPERATION.like, comuna, null));
		}
		
		return clienteDAO.search(params);
	}

	@Override
	public Boolean actualizar(Long id, Cliente clienteDTO) {

		Cliente cliente = obtener(id);
		cliente.setComuna(comunaService.obtener(clienteDTO.getComuna().getNombre()));
		cliente.setDireccion(clienteDTO.getDireccion());
		cliente.setMail(clienteDTO.getMail());
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setRut(clienteDTO.getRut());
		cliente.setTelefono(clienteDTO.getTelefono());

		clienteRepository.save(cliente);

		return Boolean.TRUE;
	}

	@Override
	public Cliente obtener(String clienteRut) {

		Optional<Cliente> _cliente = clienteRepository.findByRut(clienteRut);
		Assert.isTrue(_cliente.isPresent(), "No existe cliente con dicho rut");

		return _cliente.get();
	}
}
