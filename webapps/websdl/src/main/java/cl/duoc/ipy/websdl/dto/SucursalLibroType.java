package cl.duoc.ipy.websdl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SucursalLibroType {
	
	private Long id;
	private String nombreLibro;
	private String codigoSucursal;
	private Integer stock;
}
