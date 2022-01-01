package br.com.agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.service.MyUserDetailsService;

@RestController
@RequestMapping(value = "/users")
public class UserRestController {
	
	@Autowired
	private MyUserDetailsService userService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public ResponseEntity<UserDetails> login(@RequestParam(required = false, name = "username") String username,
			@RequestParam(required = false, name = "password") String password) {	
		
		System.out.println(username + " " + password);
		UserDetails user = userService.loadUserByUsername(username);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}


}
