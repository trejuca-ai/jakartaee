package mx.org.banxico.jakarta.configuration;

import org.modelmapper.ModelMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class CursoJEEProyecto3Configuration {

	@Produces
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
