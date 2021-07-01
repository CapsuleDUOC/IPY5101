package cl.duoc.ipy.websdl.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.ipy.websdl.domain.Libro;
import cl.duoc.ipy.websdl.domain.Venta;
import cl.duoc.ipy.websdl.domain.VentaLibro;
import cl.duoc.ipy.websdl.repository.VentaLibroRepository;
import cl.duoc.ipy.websdl.service.VentaLibroService;

@Service
public class VentaLibroServiceImpl implements VentaLibroService{

	private VentaLibroRepository ventaLibroRepository;
	
	@Autowired
	public VentaLibroServiceImpl(final VentaLibroRepository ventaLibroRepository) {
		this.ventaLibroRepository = ventaLibroRepository;
	}

	@Override
	public List<VentaLibro> consultar(Venta venta) {
		return ventaLibroRepository.findByVenta(venta);
	}

	@Override
	public VentaLibro crear(Venta venta, Libro libro, Integer cantidad, BigInteger precioUnitario) {
		
		VentaLibro ventaLibro = new VentaLibro();
		ventaLibro.setVenta(venta);
		ventaLibro.setLibro(libro);
		ventaLibro.setCantidad(cantidad);
		ventaLibro.setPrecioUnitario(precioUnitario);
		
		return ventaLibroRepository.save(ventaLibro);
	}
}
