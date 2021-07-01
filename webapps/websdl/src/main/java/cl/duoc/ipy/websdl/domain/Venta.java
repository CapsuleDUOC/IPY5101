package cl.duoc.ipy.websdl.domain;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import cl.duoc.ipy.websdl.enums.EstadoVenta;
import cl.duoc.ipy.websdl.enums.TipoDespacho;
import cl.duoc.ipy.websdl.enums.TipoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Venta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Integer tipoDte;
	
	private Long folio;
	
	private LocalDate fecha;
	
	private EstadoVenta estado;
	
	private TipoPago tipoPago;
	
	private TipoDespacho tipoDespacho;
	
	private BigInteger montoBruto;
	
	private BigInteger montoIva;
	
	private BigInteger montoTotal;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Sucursal sucursal;
	
	@ManyToOne
	private Vendedor vendedor;
}
