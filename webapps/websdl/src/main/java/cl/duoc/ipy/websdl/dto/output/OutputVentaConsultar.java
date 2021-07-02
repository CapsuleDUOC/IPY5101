package cl.duoc.ipy.websdl.dto.output;

import java.util.ArrayList;
import java.util.List;

import cl.duoc.ipy.websdl.dto.VentaType;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputVentaConsultar {

	private List<VentaType> registros = new ArrayList<>();
}
