package cl.duoc.ipy.websdl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cl.duoc.ipy.websdl.domain.Libro;
import cl.duoc.ipy.websdl.repository.ILibroDAO;
import cl.duoc.ipy.websdl.repository.LibroRepository;
import cl.duoc.ipy.websdl.service.LibroService;
import cl.duoc.ipy.websdl.util.SearchCriteria;

@Service
public class LibroServiceImpl implements LibroService{

	private LibroRepository libroRepository;
	private ILibroDAO libroDAO;
	
	@Autowired
	public LibroServiceImpl(final LibroRepository libroRepository, final ILibroDAO libroDAO) {
		this.libroRepository = libroRepository;
		this.libroDAO = libroDAO;
	}
	
	@Override
	public Libro crear(Libro inputDTO) {
		
		Optional<Libro> _libro = libroRepository.findByIsbn(inputDTO.getIsbn());
		if (_libro.isPresent())
			return _libro.get();
		
		Libro libro = new Libro();
		libro.setAutor(inputDTO.getAutor());
		libro.setEditorial(inputDTO.getEditorial());
		libro.setIsbn(inputDTO.getIsbn());
		libro.setNombre(inputDTO.getNombre());
		libro.setPrecio(inputDTO.getPrecio());
		
		return libroRepository.save(libro);
	}

	@Override
	public List<Libro> consultar(String isbn, String partNombre, String partAutor, String partEditorial, Integer offset, Integer limit) {
		
		List<SearchCriteria> params = new ArrayList<>();
		
		if (isbn != null)
			params.add(new SearchCriteria("isbn", null, SearchCriteria.OPERATION.equal, isbn, null));
		if (partNombre != null)
			params.add(new SearchCriteria("nombre", null, SearchCriteria.OPERATION.like, partNombre, null));
		if (partAutor != null)
			params.add(new SearchCriteria("autor", null, SearchCriteria.OPERATION.like, partAutor, null));
		if (partEditorial != null)
			params.add(new SearchCriteria("editorial", null, SearchCriteria.OPERATION.like, partEditorial, null));

		return libroDAO.search(params, PageRequest.of(offset, limit));
	}

	@Override
	public Libro obtener(Long id) {
		
		Optional<Libro> _libro = libroRepository.findById(id);
		Assert.isTrue(_libro.isPresent(), "No existe libro con dicho ID");
		
		return _libro.get();
	}
	
	@Override
	public Libro obtener(String isbn) {
		
		Optional<Libro> _libro = libroRepository.findByIsbn(isbn);
		Assert.isTrue(_libro.isPresent(), "No existe libro con dicho ISBN");
		
		return _libro.get();
	}

	@Override
	public Boolean actualizar(Long id, Libro inputDTO) {
		
		Libro libro = obtener(id);
		
		libro.setAutor(inputDTO.getAutor());
		libro.setEditorial(inputDTO.getEditorial());
		libro.setIsbn(inputDTO.getIsbn());
		libro.setNombre(inputDTO.getNombre());
		libro.setPrecio(inputDTO.getPrecio());
		
		libroRepository.save(libro);
		
		return Boolean.TRUE;
	}

}
