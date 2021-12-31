package br.com.agenda.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.agenda.exceptions.BusinessException;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public ErrorInfo businessExceptionHandler(HttpServletRequest req, BusinessException e) {
		return new ErrorInfo(req.getRequestURL().toString(), e.getMessage());
	}
	
}
