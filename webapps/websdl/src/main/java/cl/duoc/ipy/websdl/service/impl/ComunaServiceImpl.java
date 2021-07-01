package cl.duoc.ipy.websdl.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cl.duoc.ipy.websdl.domain.Comuna;
import cl.duoc.ipy.websdl.repository.ComunaRepository;
import cl.duoc.ipy.websdl.service.ComunaService;

@Service
public class ComunaServiceImpl implements ComunaService{
	
	private ComunaRepository comunaRepository;

	@Autowired
	public ComunaServiceImpl(final ComunaRepository comunaRepository) {
		this.comunaRepository = comunaRepository;
	}

	@Override
	public Comuna crear(Comuna inputDTO) {
		
		Optional<Comuna> _comuna = comunaRepository.findByNombre(inputDTO.getNombre().toUpperCase());
		if (_comuna.isPresent())
			return _comuna.get();
		
		Comuna comuna = new Comuna();
		comuna.setNombre(inputDTO.getNombre().toUpperCase());
		
		return comunaRepository.save(comuna);
	}

	@Override
	public List<Comuna> consultar(String partNombre) {
		
		if(partNombre != null)
			return comunaRepository.findByNombreLike(partNombre);
		else
			return IterableUtils.toList(comunaRepository.findAll());
		
	}

	@Override
	public Comuna obtener(Long id) {
		
		Optional<Comuna> _comuna = comunaRepository.findById(id);
		Assert.isTrue(_comuna.isPresent(), "No existe comuna con dicho ID");
		
		return _comuna.get();
	}

	@Override
	public Boolean actualizar(Long id, Comuna inputDTO) {
		
		Comuna comuna = obtener(id);
		comuna.setNombre(inputDTO.getNombre());
		
		comunaRepository.save(comuna);
		
		return Boolean.TRUE;
	}

	@Override
	public Comuna obtener(String nombre) {
		
		Optional<Comuna> _comuna = comunaRepository.findByNombre(nombre.toUpperCase());
		Assert.isTrue(_comuna.isPresent(), "La comuna [" + nombre + "] no existe");
		
		return _comuna.get();
	}
}
