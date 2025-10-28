package com.demo.documentapi;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.demo.documentapi.dto.DocumentErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class DocumentApiExceptionHandlers {

	@ExceptionHandler(exception = NoResourceFoundException.class)
	public ResponseEntity<DocumentErrorResponse> handleResourceNotFoundException(final HttpServletRequest request, NoResourceFoundException exception) {
		
		DocumentErrorResponse errorResponse = DocumentErrorResponse.builder()
												.requestDate(LocalDate.now())
												.requestURLString(request.getRequestURI())
												.errorDescription("Requested document was not found- " + exception.getResourcePath())
												.statusCode(exception.getStatusCode().toString())
												.traceId(UUID.randomUUID()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		
		
	}
	
}
