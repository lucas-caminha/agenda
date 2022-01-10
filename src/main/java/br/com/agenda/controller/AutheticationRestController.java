package br.com.agenda.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.configuration.security.TokenService;
import br.com.agenda.dto.TokenDTO;
import br.com.agenda.dto.UserDTO;

@RestController
@RequestMapping("/auth")
public class AutheticationRestController {
	
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private TokenService tokenService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> authenticate(@RequestBody @Valid UserDTO userDTO){
		UsernamePasswordAuthenticationToken login = userDTO.toToken();
		try {
			Authentication authentication = authManager.authenticate(login);
			String token = tokenService.getToken(authentication);
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));	
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
}
