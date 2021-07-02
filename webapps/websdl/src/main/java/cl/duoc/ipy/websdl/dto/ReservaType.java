package cl.duoc.ipy.websdl.dto;

import cl.duoc.ipy.websdl.enums.EstadoReserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaType {
	
	private Long id;
	private EstadoReserva estado;
	private Long ventaId;
}
