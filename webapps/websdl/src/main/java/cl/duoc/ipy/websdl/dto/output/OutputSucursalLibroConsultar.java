package cl.duoc.ipy.websdl.dto.output;

import java.util.List;

import cl.duoc.ipy.websdl.dto.SucursalLibroType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputSucursalLibroConsultar {

	private List<SucursalLibroType> registros;
}
