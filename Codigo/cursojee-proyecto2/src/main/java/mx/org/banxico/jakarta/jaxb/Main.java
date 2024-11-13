package mx.org.banxico.jakarta.jaxb;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Main {

	public static void main(String[] args) {
		try {
			unmarshall();
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public static void marshall() throws JAXBException {
		
		Actor actor = new Actor();
		actor.setNombre("Juan Carlos");
		actor.setApellido("Trejo");
		actor.setId(1);
		actor.setFechaNacimiento(new Date());
		actor.setGenero(Genero.MASCULINO);
		
		List<Pelicula> peliculas = new ArrayList<Pelicula>();
		
		Pelicula pelicula1 = new Pelicula();
		pelicula1.setId(1);
		pelicula1.setTitulo("Transformers");
		pelicula1.setGenero("Accion");
		
		Pelicula pelicula2 = new Pelicula();
		pelicula2.setId(2);
		pelicula2.setTitulo("Codigo Da vinci");
		pelicula2.setGenero("suspenso");
		
		peliculas.add(pelicula1);
		peliculas.add(pelicula2);
		actor.setPeliculas(peliculas);
		
		JAXBContext context = JAXBContext.newInstance(Actor.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(actor, new File("./actor.xml"));
	}
	
	public static void unmarshall() throws JAXBException {
		
		JAXBContext context = JAXBContext.newInstance(Actor.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		Actor actor = (Actor)unmarshaller.unmarshal(new File("./actor.xml"));
		System.out.println(actor);
	}
}
