package cl.duoc.ipy.websdl.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cl.duoc.ipy.websdl.domain.Libro;
import cl.duoc.ipy.websdl.domain.Sucursal;
import cl.duoc.ipy.websdl.domain.SucursalLibro;
import cl.duoc.ipy.websdl.repository.SucursalLibroRepository;
import cl.duoc.ipy.websdl.service.LibroService;
import cl.duoc.ipy.websdl.service.SucursalLibroService;

@Service
public class SucursalLibroServiceImpl implements SucursalLibroService {

	private SucursalLibroRepository sucursalLibroRepository;
	private LibroService libroService;

	@Autowired
	public SucursalLibroServiceImpl(final SucursalLibroRepository sucursalLibroRepository,
			final LibroService libroService) {
		this.sucursalLibroRepository = sucursalLibroRepository;
		this.libroService = libroService;
	}

	@Override
	public SucursalLibro crear(Sucursal sucursal, SucursalLibro inputDTO) {
		
		Libro libro = libroService.obtener(inputDTO.getLibro().getId());

		Optional<SucursalLibro> _sucursalLibro = sucursalLibroRepository.findBySucursalAndLibro(sucursal, libro);
		if (_sucursalLibro.isPresent())
			return _sucursalLibro.get();
		
		SucursalLibro sucursalLibro = new SucursalLibro();
		sucursalLibro.setLibro(libro);
		sucursalLibro.setSucursal(sucursal);
		sucursalLibro.setStock(inputDTO.getStock());

		return sucursalLibroRepository.save(sucursalLibro);
	}

	@Override
	public List<SucursalLibro> consultar(Sucursal sucursal) {
		return IterableUtils.toList(sucursalLibroRepository.findAll());
	}

	@Override
	public SucursalLibro obtener(Sucursal sucursal, Long id) {
		
		Optional<SucursalLibro> _sucursalLibro = sucursalLibroRepository.findBySucursalAndId(sucursal, id);
		Assert.isTrue(_sucursalLibro.isPresent(), "No existe relacion de Sucursal - Libro para dicho ID");
		
		return _sucursalLibro.get();
	}

	@Override
	public Boolean actualizarStock(Sucursal sucursal, Long id, Integer stock) {

		SucursalLibro sucursalLibro = obtener(sucursal, id);
		
		sucursalLibro.setStock(stock);
		
		sucursalLibroRepository.save(sucursalLibro);
		
		return Boolean.TRUE;
	}
}
