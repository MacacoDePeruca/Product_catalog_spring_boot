package com.br.vitornascimento.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.br.vitornascimento.data.model.ExceptionMessage;
import com.br.vitornascimento.exception.ExceptionResponse;
import com.br.vitornascimento.exception.InvalidJwtAutheticationException;
import com.br.vitornascimento.exception.InvalidProductFormatException;
import com.br.vitornascimento.exception.ProductNotfoundByIdException;
import com.br.vitornascimento.exception.ResourceNotfoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(InvalidJwtAutheticationException.class)
	public final ResponseEntity<ExceptionResponse> invalidJwtAutheticationException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(InvalidProductFormatException.class)
	public final ResponseEntity<ExceptionMessage> invalidProductFormatException(Exception ex, WebRequest request) {

		ExceptionMessage message = new ExceptionMessage(HttpStatus.BAD_REQUEST.value(), "invalid format of product");

		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	};

	@ExceptionHandler(ResourceNotfoundException.class)
	public final ResponseEntity<ExceptionMessage> productNotFoundException(Exception ex, WebRequest request) {

		ExceptionMessage message = new ExceptionMessage(HttpStatus.NOT_FOUND.value(), "product not found");

		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	};

	@ExceptionHandler(ProductNotfoundByIdException.class)
	public final ResponseEntity<Object> productNotFoundByIdException(Exception ex, WebRequest request) {

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	};

}
