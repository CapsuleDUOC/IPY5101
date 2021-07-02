package cl.duoc.ipy.websdl.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputSucursalLibroCrear {

	private Long libroId;
	private Integer stock;
}
