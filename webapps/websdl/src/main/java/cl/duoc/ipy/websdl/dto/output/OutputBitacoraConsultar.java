package cl.duoc.ipy.websdl.dto.output;

import java.util.List;

import cl.duoc.ipy.websdl.dto.BitacoraType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputBitacoraConsultar {

	private List<BitacoraType> registros;
}
