package br.com.agenda.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.agenda.entity.MyUserPrincipal;
import br.com.agenda.entity.User;
import br.com.agenda.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		Optional<User> findByUsername = userRepository.findByUsername(username);
		
		if (findByUsername.isPresent()) {
			return new MyUserPrincipal(findByUsername.get());
		}
			
		throw new UsernameNotFoundException(username);
	}
	
	public UserDetails registra(String username, String password) {
		User saved = userRepository.save(new User(password, password));
		return new MyUserPrincipal(saved);
	}

}
