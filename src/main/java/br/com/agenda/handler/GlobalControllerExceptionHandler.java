package br.com.agenda.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.agenda.exceptions.BusinessException;
import br.com.agenda.exceptions.NotFoundException;
import br.com.agenda.exceptions.UserExistingException;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public ResponseEntity<ErrorInfo> businessExceptionHandler(HttpServletRequest req, BusinessException e) {
		ErrorInfo errorInfo = new ErrorInfo(req.getRequestURL().toString(), e.getMessage());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorInfo);
	}
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	public ResponseEntity<ErrorInfo> notFoundExceptionHandler(HttpServletRequest req, NotFoundException e) {
		ErrorInfo errorInfo = new ErrorInfo(req.getRequestURL().toString(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);
	}
	
	@ExceptionHandler(UserExistingException.class)
	@ResponseBody
	public ResponseEntity<ErrorInfo> userExistingExceptionHandler(HttpServletRequest req, UserExistingException e) {
		ErrorInfo errorInfo = new ErrorInfo(req.getRequestURL().toString(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);
	}
	
}
