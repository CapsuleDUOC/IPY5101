package cl.duoc.ipy.websdl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.ipy.websdl.domain.Venta;
import cl.duoc.ipy.websdl.domain.VentaBitacora;
import cl.duoc.ipy.websdl.repository.VentaBitacoraRepository;
import cl.duoc.ipy.websdl.service.VentaBitacoraService;

@Service
public class VentaBitacoraServiceImpl implements VentaBitacoraService{

	private VentaBitacoraRepository ventaBitacoraRepository;
	
	@Autowired
	public VentaBitacoraServiceImpl(final VentaBitacoraRepository ventaBitacoraRepository) {
		this.ventaBitacoraRepository = ventaBitacoraRepository;
	}

	@Override
	public List<VentaBitacora> consultar(Venta venta) {
		return ventaBitacoraRepository.findByVenta(venta);
	}
}
