package br.com.agenda;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgendaConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
