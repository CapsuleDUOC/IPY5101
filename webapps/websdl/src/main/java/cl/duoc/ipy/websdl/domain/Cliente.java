package cl.duoc.ipy.websdl.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String rut;
	
	private String nombre;
	
	private String mail;
	
	private String telefono;
	
	private String direccion;
	
	@ManyToOne
	private Comuna comuna;
}
