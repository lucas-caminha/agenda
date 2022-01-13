package br.com.agenda.security.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.agenda.dto.UserDTO;
import br.com.agenda.entity.MyUserPrincipal;
import br.com.agenda.exceptions.UserExistingException;

@Transactional
@SpringBootTest
class CustomUserDetailsServiceTest {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Test
	void deveRetornarUserExistingExceptionAoTentarRegistrarUmUsuarioComUsernameExistente() {
		UserDTO userDTO = new UserDTO("admin", "admin");
		assertThrows(UserExistingException.class, () -> userDetailsService.registerNewUserAccount(userDTO));
	}
	
	@Test
	@Rollback(value = true)
	void deveRetornarMyUserPrincipalAoTentarRegistrarUmUsuario() {
		UserDTO userDTO = new UserDTO("teste", "teste");
		assertEquals(MyUserPrincipal.class, userDetailsService.registerNewUserAccount(userDTO).getClass());
	}
	
	@Test
	void deveRetornarUsernameNotFoundExceptionAoTentarLogarComUsernameNaoExistente() {
		UserDTO userDTO = new UserDTO("admin999", "admin");
		assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(userDTO.getUsername()));
	}
	
	@Test
	@Rollback(value = true)
	void deveRetornarMyUserPrincipalAoLogarComCredenciaisExistentes() {
		UserDTO userDTO = new UserDTO("admin", "admin");
		assertEquals(MyUserPrincipal.class, userDetailsService.loadUserByUsername(userDTO.getUsername()).getClass());
	}

}
