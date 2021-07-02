package cl.duoc.ipy.websdl.dto.output;

import java.util.ArrayList;
import java.util.List;

import cl.duoc.ipy.websdl.dto.DespachoType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutputDespachoConsultar {

	private List<DespachoType> registros = new ArrayList<>();
}
