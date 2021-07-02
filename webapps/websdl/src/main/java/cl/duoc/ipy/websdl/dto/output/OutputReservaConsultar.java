package cl.duoc.ipy.websdl.dto.output;

import java.util.ArrayList;
import java.util.List;

import cl.duoc.ipy.websdl.dto.ReservaType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputReservaConsultar {

	private List<ReservaType> registros = new ArrayList<>();
	
 }
