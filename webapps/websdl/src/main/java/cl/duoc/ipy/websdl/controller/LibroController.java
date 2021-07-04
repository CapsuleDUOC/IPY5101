package cl.duoc.ipy.websdl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ipy.websdl.domain.Libro;
import cl.duoc.ipy.websdl.service.LibroService;

@RestController
@RequestMapping("/libro")
public class LibroController {

	private LibroService libroService;

	@Autowired
	public LibroController(final LibroService libroService) {
		this.libroService = libroService;
	}

	@PostMapping
	ResponseEntity<Libro> crear(@RequestBody Libro inputDTO) {
		Libro libro = libroService.crear(inputDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(libro);
	}

	@GetMapping
	ResponseEntity<List<Libro>> consultar(@RequestParam(name = "isbn", required = false) final String isbn,
			@RequestParam(name = "partNombre", required = false) final String partNombre,
			@RequestParam(name = "partAutor", required = false) final String partAutor,
			@RequestParam(name = "partEditorial", required = false) final String partEditorial) {

		List<Libro> libros = libroService.consultar(isbn, partNombre, partAutor, partEditorial);

		return ResponseEntity.ok(libros);
	}

	@GetMapping("/{id}")
	ResponseEntity<Libro> obtener(@PathVariable(name = "id") Long id) {
		Libro libro = libroService.obtener(id);
		return ResponseEntity.ok(libro);
	}

	@PutMapping("/{id}")
	ResponseEntity<Boolean> actualizar(@PathVariable(name = "id") Long id, @RequestBody Libro inputDTO) {
		return ResponseEntity.ok(libroService.actualizar(id, inputDTO));
	}
}
