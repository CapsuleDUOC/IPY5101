package cl.duoc.ipy.websdl.dto;

import java.math.BigInteger;
import java.time.LocalDate;

import cl.duoc.ipy.websdl.enums.EstadoVenta;
import cl.duoc.ipy.websdl.enums.TipoDespacho;
import cl.duoc.ipy.websdl.enums.TipoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaType {

	private String clienteRut;
	private String vendedorEmail;
	private EstadoVenta estado;
	private LocalDate fecha;
	private Long folio;
	private BigInteger montoBruto;
	private BigInteger montoIva;
	private BigInteger montoTotal;
	private String codigoSucursal;
	private TipoDespacho tipoDespacho;
	private Integer tipoDTE;
	private TipoPago tipoPago;
}
