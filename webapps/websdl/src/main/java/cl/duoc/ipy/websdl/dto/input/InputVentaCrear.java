package cl.duoc.ipy.websdl.dto.input;

import java.util.List;

import cl.duoc.ipy.websdl.dto.RegistroVentaLibro;
import cl.duoc.ipy.websdl.dto.VentaType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputVentaCrear extends VentaType{

	List<RegistroVentaLibro> libros;
}
