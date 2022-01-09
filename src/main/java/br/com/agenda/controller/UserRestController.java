package br.com.agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.dto.UserDTO;
import br.com.agenda.service.MyUserDetailsService;

@RestController
@RequestMapping(value = "/user")
public class UserRestController {
	
	@Autowired
	private MyUserDetailsService userService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public ResponseEntity<UserDetails> login(@RequestBody UserDTO user) {	
		
		UserDetails userAuth = userService.loadUserByUsernameAndPassword(user);
		
		return ResponseEntity.status(HttpStatus.OK).body(userAuth);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/registrar")
	public ResponseEntity<UserDetails> register(@RequestBody UserDTO user){
		UserDetails registred = userService.registerNewUserAccount(user);
		if (registred != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(registred);
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
	}
	


}
