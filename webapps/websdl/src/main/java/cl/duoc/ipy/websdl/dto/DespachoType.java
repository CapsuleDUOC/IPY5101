package cl.duoc.ipy.websdl.dto;

import cl.duoc.ipy.websdl.enums.EstadoDespacho;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DespachoType {

	private Long id;
	private String clienteRut;
	private String comuna;
	private String direccion;
	private EstadoDespacho estado;
	private Long ventaId;
}
