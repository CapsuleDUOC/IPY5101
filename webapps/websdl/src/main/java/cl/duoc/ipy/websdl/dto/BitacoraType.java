package cl.duoc.ipy.websdl.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BitacoraType {

	private Long id;
	private LocalDateTime instante;
	private String nota;
}
