package cl.duoc.ipy.websdl.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cl.duoc.ipy.websdl.domain.Cliente;
import cl.duoc.ipy.websdl.domain.Libro;
import cl.duoc.ipy.websdl.domain.Sucursal;
import cl.duoc.ipy.websdl.domain.Vendedor;
import cl.duoc.ipy.websdl.domain.Venta;
import cl.duoc.ipy.websdl.enums.EstadoVenta;
import cl.duoc.ipy.websdl.enums.TipoDespacho;
import cl.duoc.ipy.websdl.enums.TipoPago;
import cl.duoc.ipy.websdl.repository.IVentaDAO;
import cl.duoc.ipy.websdl.repository.VentaRepository;
import cl.duoc.ipy.websdl.service.ClienteService;
import cl.duoc.ipy.websdl.service.LibroService;
import cl.duoc.ipy.websdl.service.VendedorService;
import cl.duoc.ipy.websdl.service.VentaLibroService;
import cl.duoc.ipy.websdl.service.VentaService;
import cl.duoc.ipy.websdl.util.SearchCriteria;

@Service
public class VentaServiceImpl implements VentaService{

	private VentaRepository ventaRepository;
	private VentaLibroService ventaLibroService;
	private ClienteService clienteService;
	private VendedorService vendedorService;
	private LibroService libroService;
	private IVentaDAO ventaDAO;
	
	@Autowired
	public VentaServiceImpl(final VentaRepository ventaRepository, final VentaLibroService ventaLibroService, final ClienteService clienteService, final LibroService libroService, final IVentaDAO ventaDAO) {
		this.ventaRepository = ventaRepository;
		this.ventaLibroService = ventaLibroService;
		this.clienteService = clienteService;
		this.libroService = libroService;
		this.ventaDAO = ventaDAO;
	}

	@Override
	public Venta crear(Sucursal sucursal, Venta inputDTO) {
		
		Optional<Venta> _venta = ventaRepository.findBySucursalAndTipoDteAndFolio(sucursal, inputDTO.getTipoDte(), inputDTO.getFolio());
		if (_venta.isPresent())
			return _venta.get();
		
		Venta venta = new Venta();
		venta.setCliente(clienteService.obtener(inputDTO.getCliente().getRut()));
		venta.setVendedor(vendedorService.obtener(sucursal, inputDTO.getVendedor().getMail()));
		venta.setEstado(inputDTO.getEstado());
		venta.setFecha(inputDTO.getFecha());
		venta.setFolio(inputDTO.getFolio());
		venta.setMontoBruto(inputDTO.getMontoBruto());
		venta.setMontoIva(inputDTO.getMontoIva());
		venta.setMontoTotal(inputDTO.getMontoTotal());
		venta.setSucursal(sucursal);
		venta.setTipoDespacho(inputDTO.getTipoDespacho());
		venta.setTipoDte(inputDTO.getTipoDte());
		venta.setTipoPago(inputDTO.getTipoPago());
		
		
		venta = ventaRepository.save(venta);
		
		for (RegistroVentaLibro registro  : inputDTO.getLibros()) {
			
			 Libro libro = libroService.obtener(registro.getIsbn());
			 ventaLibroService.crear(venta, libro, registro.getCantidad(), registro.getPrecioUnitario());
		}

		return venta;
	}

	@Override
	public List<Venta> consultar(Sucursal sucursal, String tipoDTE, Long folio, LocalDate fechaEmision,
			String clienteRut, String vendedorEmail, EstadoVenta estado, TipoPago tipoPago, TipoDespacho tipoDespacho) {
		
		List<SearchCriteria> params = new ArrayList<>();
		params.add(new SearchCriteria("sucursal", null, SearchCriteria.OPERATION.equal, sucursal, null));
		if (tipoDTE != null)
			params.add(new SearchCriteria("tipoDte", null, SearchCriteria.OPERATION.equal, tipoDTE, null));
		if (folio != null)
			params.add(new SearchCriteria("folio", null, SearchCriteria.OPERATION.equal, folio, null));
		if (fechaEmision != null)
			params.add(new SearchCriteria("fecha", null, SearchCriteria.OPERATION.equal, fechaEmision, null));
		if (clienteRut != null) {
			Cliente cliente = clienteService.obtener(clienteRut);
			params.add(new SearchCriteria("cliente", null, SearchCriteria.OPERATION.equal, cliente, null));
		}
		if (vendedorEmail != null) {
			Vendedor vendedor = vendedorService.obtener(sucursal, vendedorEmail);
			params.add(new SearchCriteria("vendedor", null, SearchCriteria.OPERATION.equal, vendedor, null));
		}
		if (estado != null)
			params.add(new SearchCriteria("estado", null, SearchCriteria.OPERATION.equal, estado, null));
		if (tipoPago != null)
			params.add(new SearchCriteria("tipoPago", null, SearchCriteria.OPERATION.equal, tipoPago, null));
		if (tipoDespacho != null)
			params.add(new SearchCriteria("tipoDespacho", null, SearchCriteria.OPERATION.equal, tipoDespacho, null));

		return ventaDAO.search(params);
	}

	@Override
	public Venta obtener(Sucursal sucursal, Long id) {
		
		Optional<Venta> _venta = ventaRepository.findBySucursalAndId(sucursal, id);
		Assert.isTrue(_venta.isPresent(), "No existe venta para dicho ID");
		
		return _venta.get();
	}

	@Override
	public Boolean actualizar(Sucursal sucursal, Long id, EstadoVenta estado) {
		
		Venta venta = obtener(sucursal, id);
		venta.setEstado(estado);
		
		ventaRepository.save(venta);
		
		return Boolean.TRUE;
	}

	@Override
	public Venta obtener(Long id) {
		
		Optional<Venta> _venta = ventaRepository.findById(id);
		Assert.isTrue(_venta.isPresent(), "No existe venta para dicho ID");
		
		return _venta.get();
	}

}
