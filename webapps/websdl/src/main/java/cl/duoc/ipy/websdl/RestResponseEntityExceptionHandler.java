package cl.duoc.ipy.websdl;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler {

	@Autowired
	ObjectMapper mapper;

	@Autowired(required = false)
	BuildProperties buildProperties;

	@ExceptionHandler({ ResponseStatusException.class })
	protected ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
		return httpClientGenericErrorException(ex, request, ex.getStatus());
	}

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		return httpClientGenericErrorException(ex, request, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		return httpClientGenericErrorException(ex, request, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler({ HttpClientErrorException.class })
	public ResponseEntity<Object> httpClientErrorException(Exception ex, WebRequest request) {
		return httpClientGenericErrorException(ex, request, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler({ Exception.class, NullPointerException.class })
	public ResponseEntity<Object> exception(Exception ex, WebRequest request) {
		return httpClientGenericErrorException(ex, request, HttpStatus.CONFLICT);
	}

	public ResponseEntity<Object> httpClientGenericErrorException(Exception ex, WebRequest request, HttpStatus status) {
		log.error(ex.getMessage(), ex);

		if (status == HttpStatus.FORBIDDEN)
			return new ResponseEntity<>("Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);

		final ObjectNode dataTable = mapper.createObjectNode().put("Error",
				(ex.getMessage() != null ? ex.getMessage() : "Null"));

		return new ResponseEntity<>(dataTable.toString(), new HttpHeaders(), status);
	}

	
}
