package br.com.agenda.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.agenda.dto.UserDTO;
import br.com.agenda.entity.MyUserPrincipal;
import br.com.agenda.entity.User;
import br.com.agenda.exceptions.UserExistingException;
import br.com.agenda.repository.UserRepository;
import br.com.agenda.util.MessageUtil;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) {		
		Optional<User> findByUsername = userRepository.findByUsername(username);		
		if (findByUsername.isPresent()) {
			return new MyUserPrincipal(findByUsername.get());
		}		
		throw new UsernameNotFoundException(username);
	}
		
	public UserDetails registerNewUserAccount(UserDTO user) {
			
		Optional<User> find = userRepository.findByUsername(user.getUsername());
			
		if (find.isEmpty()) {
			User entity = new User();
			entity.setUsername(user.getUsername());
			entity.setPassword(passwordEncoder.encode(user.getPassword()));
			
			User saved = userRepository.save(entity);
			return new MyUserPrincipal(saved);
		}
		
		throw new UserExistingException(MessageUtil.USUARIO_EXISTENTE);
	}

	
	
}
