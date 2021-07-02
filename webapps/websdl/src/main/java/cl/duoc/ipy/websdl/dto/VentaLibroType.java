package cl.duoc.ipy.websdl.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaLibroType {

	private Long id;
	private Long ventaId;
	private String nombreLibro;
	private Integer cantidad;
	private BigInteger precioLUnitario;
}
