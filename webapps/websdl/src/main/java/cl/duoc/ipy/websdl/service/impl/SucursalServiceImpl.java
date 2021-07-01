package cl.duoc.ipy.websdl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cl.duoc.ipy.websdl.domain.Comuna;
import cl.duoc.ipy.websdl.domain.Sucursal;
import cl.duoc.ipy.websdl.repository.ISucursalDAO;
import cl.duoc.ipy.websdl.repository.SucursalRepository;
import cl.duoc.ipy.websdl.service.ComunaService;
import cl.duoc.ipy.websdl.service.SucursalService;
import cl.duoc.ipy.websdl.util.SearchCriteria;

@Service
public class SucursalServiceImpl implements SucursalService{
	
	private SucursalRepository sucursalRepository;
	private ComunaService comunaService;
	private ISucursalDAO sucursalDAO;

	@Autowired
	public SucursalServiceImpl(final SucursalRepository sucursalRepository, final ComunaService comunaService, final ISucursalDAO sucursalDAO) {
		this.sucursalRepository = sucursalRepository;
		this.comunaService = comunaService;
		this.sucursalDAO = sucursalDAO;
	}

	@Override
	public Sucursal crear(Sucursal inputDTO) {
		
		Optional<Sucursal> _sucursal = sucursalRepository.findByCodigo(inputDTO.getCodigo());
		if (_sucursal.isPresent())
			return _sucursal.get();
		
		Sucursal sucursal = new Sucursal();
		sucursal.setComuna(comunaService.obtener(inputDTO.getComuna().getNombre()));
		sucursal.setCodigo(inputDTO.getCodigo());
		sucursal.setDireccion(inputDTO.getDireccion());
		sucursal.setEncargado(inputDTO.getEncargado());
		
		return sucursalRepository.save(sucursal);
	}

	@Override
	public List<Sucursal> consultar(String codigo, String partDireccion, String partEncargado, String nombreComuna) {
		
		List<SearchCriteria> params = new ArrayList<>();
		
		if (codigo != null)
			params.add(new SearchCriteria("codigo", null, SearchCriteria.OPERATION.equal, codigo, null));
		if (partDireccion != null)
			params.add(new SearchCriteria("direccion", null, SearchCriteria.OPERATION.like, partDireccion, null));
		if (partEncargado != null)
			params.add(new SearchCriteria("encargado", null, SearchCriteria.OPERATION.like, partEncargado, null));
		if (nombreComuna != null) {
			Comuna comuna = comunaService.obtener(nombreComuna);
			params.add(new SearchCriteria("comuna", null, SearchCriteria.OPERATION.equal, comuna, null));
		}
		
		return sucursalDAO.search(params);
	}

	@Override
	public Sucursal obtener(Long id) {
		
		Optional<Sucursal> _sucursal = sucursalRepository.findById(id);
		Assert.isTrue(_sucursal.isPresent(), "No existe sucursal para dicho ID");
		
		return _sucursal.get();
	}

	@Override
	public Boolean actualizar(Long id, Sucursal inputDTO) {
		
		Sucursal sucursal = obtener(id);
		sucursal.setCodigo(inputDTO.getCodigo());
		sucursal.setComuna(comunaService.obtener(inputDTO.getComuna().getNombre()));
		sucursal.setDireccion(inputDTO.getDireccion());
		sucursal.setEncargado(inputDTO.getEncargado());
		
		sucursalRepository.save(sucursal);
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean eliminar(Long id) {
		
		Sucursal sucursal = obtener(id);
		
		sucursalRepository.delete(sucursal);
		
		return Boolean.TRUE;
	}

	@Override
	public Sucursal obtener(String codigo) {
		
		Optional<Sucursal> _sucursal = sucursalRepository.findByCodigo(codigo);
		Assert.isTrue(_sucursal.isPresent(), "No existe sucursal para dicho codigo");
		
		return _sucursal.get();
	}
}
