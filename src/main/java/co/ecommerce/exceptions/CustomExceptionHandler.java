package co.ecommerce.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.ecommerce.exceptions.dto.ErrorResponseDto;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(EntityNotFoundException ex, WebRequest request) {
		return new ResponseEntity(ErrorResponseDto.builder().mensaje(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ValidateException.class)
	public final ResponseEntity<Object> handleValidationException(ValidateException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		ErrorResponseDto error = new ErrorResponseDto(ex.getMessage() + details.toString());
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponseDto error = new ErrorResponseDto("Validacion de campos fallida: " + details.toString());
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

}
