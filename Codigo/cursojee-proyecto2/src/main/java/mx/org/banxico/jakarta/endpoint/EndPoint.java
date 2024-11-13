package mx.org.banxico.jakarta.endpoint;

import java.io.File;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.persistence.GenerationType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.ws.Holder;
import mx.org.banxico.jakarta.jaxb.Actor;
import mx.org.banxico.jakarta.jaxb.Pelicula;

@WebService(
	name = "ejercicios", 
	serviceName = "ejercicios")
public class EndPoint {

	@WebMethod(operationName = "ejercicio1")
	public String ejercicio1() {
		return "Hola servucio web";
	}
	
	@WebMethod(operationName = "ejercicio2")
	public Actor ejercicio2() {
		Actor actor = new Actor();
		actor.setNombre("Juan");
		actor.setApellido("Trejo");
		actor.setFechaNacimiento(new Date());
		actor.setId(1);
		
		return actor;
	}
	
	@WebMethod(operationName = "ejercicio3")
	public Actor ejercicio3() {
		
		Actor actor = new Actor();
		actor.setNombre("Juan Carlos");
		actor.setApellido("Trejo");
		actor.setId(1);
		actor.setFechaNacimiento(new Date());
		
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
		
		return actor;
	}
	
	@WebMethod(operationName = "ejercicio4")
	public Actor ejercicio4(String json) throws JSONException, ParseException {
		
		JSONObject jsonObject = new JSONObject(json);
		
		Actor actor = new Actor();
		actor.setId(jsonObject.getInt("id"));
		actor.setNombre(jsonObject.getString("nombre"));
		actor.setApellido(jsonObject.getString("apellido"));
		actor.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(
				jsonObject.getString("fechaNacimiento")));
		
		return actor;
	}
	
	@WebMethod(operationName = "ejercicio5")
	public Actor ejercicio5(String xml) throws JAXBException {
		
		JAXBContext context = JAXBContext.newInstance(Actor.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		Actor actor = (Actor)unmarshaller.unmarshal(new StringReader(xml));
		
		return actor;
	}
	
	@WebMethod(operationName = "ejercicio6")
	public void ejercicio6(
			int x, int y,
			@WebParam(name = "suma", mode = WebParam.Mode.OUT) Holder<Integer> suma,
			@WebParam(name = "resta", mode = WebParam.Mode.OUT) Holder<Integer> resta) {
		
		suma.value = x + y;
		resta.value = x - y;
	}
	
	@WebMethod(operationName = "ejercicio7")
	public Boolean ejercicio7(
			String nombreArchivo,
			byte[] contenido) {
		return ArchivoHelper.guardarArchivo(nombreArchivo, contenido);
	}
}
