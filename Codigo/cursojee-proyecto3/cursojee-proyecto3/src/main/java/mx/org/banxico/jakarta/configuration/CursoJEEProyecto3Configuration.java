package mx.org.banxico.jakarta.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.modelmapper.ModelMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import mx.org.banxico.jakarta.dto.PaginationDto;
import mx.org.banxico.jakarta.helper.PaginationHelper;

@ApplicationScoped
public class CursoJEEProyecto3Configuration {

	private static final String MESSAGES_FILE = "messages.properties";
	
	public static enum Role {
		Administrador,
		UsuarioRegular,
	}
	
	@Produces
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Produces
	public Properties produceConfig() {
		
		Properties properties = new Properties();
		
		InputStream input = getClass()
			.getClassLoader()
			.getResourceAsStream(MESSAGES_FILE);
		
		try {
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return properties;
	}
	
	@Produces
	public <T> PaginationHelper<T> paginationHelper() {
		return new PaginationHelper<T>(new PaginationDto<>());
	}
}
