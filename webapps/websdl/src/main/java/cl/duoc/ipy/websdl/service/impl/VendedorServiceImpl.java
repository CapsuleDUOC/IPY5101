package cl.duoc.ipy.websdl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cl.duoc.ipy.websdl.domain.Sucursal;
import cl.duoc.ipy.websdl.domain.Vendedor;
import cl.duoc.ipy.websdl.repository.IVendedorDAO;
import cl.duoc.ipy.websdl.repository.VendedorRepository;
import cl.duoc.ipy.websdl.service.VendedorService;
import cl.duoc.ipy.websdl.util.SearchCriteria;

@Service
public class VendedorServiceImpl implements VendedorService{

	private VendedorRepository vendedorRepository;
	private IVendedorDAO vendedorDAO;
	
	@Autowired
	public VendedorServiceImpl(final VendedorRepository vendedorRepository, final IVendedorDAO vendedorDAO){
		this.vendedorRepository = vendedorRepository;
		this.vendedorDAO = vendedorDAO;
	}

	@Override
	public Vendedor crear(Sucursal sucursal, Vendedor inputDTO) {
		
		Optional<Vendedor> _vendedor = vendedorRepository.findByMail(inputDTO.getMail());
		if (_vendedor.isPresent())
			return _vendedor.get();
		
		Vendedor vendedor = new Vendedor();
		vendedor.setMail(inputDTO.getMail());
		vendedor.setNombre(inputDTO.getNombre());
		vendedor.setSucursal(sucursal);
		
		return vendedorRepository.save(vendedor);
	}

	@Override
	public List<Vendedor> consultar(Sucursal sucursal, String nombre, String mail) {
		
		List<SearchCriteria> params = new ArrayList<>();
		params.add(new SearchCriteria("sucursal", null, SearchCriteria.OPERATION.equal, sucursal, null));
		if (nombre != null)
			params.add(new SearchCriteria("nombre", null, SearchCriteria.OPERATION.like, nombre, null));
		if (mail != null)
			params.add(new SearchCriteria("mail", null, SearchCriteria.OPERATION.like, mail, null));

		return vendedorDAO.search(params);
	}

	@Override
	public Vendedor obtener(Sucursal sucursal, Long id) {
		
		Optional<Vendedor> _vendedor = vendedorRepository.findBySucursalAndId(sucursal, id);
		Assert.isTrue(_vendedor.isPresent(), "No existe vendedor con dicho ID");
		
		return _vendedor.get();
	}

	@Override
	public Boolean actualizar(Sucursal sucursal, Long id, Vendedor inputDTO) {
		
		Vendedor vendedor = obtener(sucursal, id);
		vendedor.setMail(inputDTO.getMail());
		vendedor.setNombre(inputDTO.getNombre());
		
		vendedorRepository.save(vendedor);
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean eliminar(Sucursal sucursal, Long id) {
		
		Vendedor vendedor = obtener(sucursal, id);

		vendedorRepository.delete(vendedor);
		
		return Boolean.TRUE;
	}

	@Override
	public Vendedor obtener(Sucursal sucursal, String mail) {
		
		Optional<Vendedor> _vendedor = vendedorRepository.findBySucursalAndMail(sucursal, mail);
		Assert.isTrue(_vendedor.isPresent(), "No existe vendedor con dicho Email");
		
		return _vendedor.get();
	}
	
	
}
		

