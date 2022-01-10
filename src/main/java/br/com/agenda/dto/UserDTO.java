package br.com.agenda.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserDTO {
	
	@NotNull
	private String username;
	@NotNull
	@Size(min = 8, max = 16)
	private String password;
	
	public UserDTO() {
	}
	
	public UserDTO(@NotNull String username, @NotNull @Size(min = 8, max = 16) String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UsernamePasswordAuthenticationToken toToken() {
		return new UsernamePasswordAuthenticationToken(this.username, this.password);
	}

}
