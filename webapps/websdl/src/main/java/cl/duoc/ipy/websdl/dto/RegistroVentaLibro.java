package cl.duoc.ipy.websdl.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroVentaLibro {

	private String isbn;
	private Integer cantidad;
	private BigInteger precioUnitario;
}
