package cl.duoc.ipy.websdl.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import cl.duoc.ipy.websdl.enums.EstadoDespacho;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Despacho {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Venta venta;
	
	private String direccion;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Comuna comuna;
	
	@Enumerated(EnumType.STRING)
	private EstadoDespacho estado;
}
