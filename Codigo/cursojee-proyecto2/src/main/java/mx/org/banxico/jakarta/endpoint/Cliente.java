package mx.org.banxico.jakarta.endpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Cliente {

	public static void main(String[] args) 
			throws IOException, ParserConfigurationException, SAXException {
		
		URL url = new URL("http://localhost:8080/cursojee-proyecto2/ejercicios");
		
		String nombreArchivo = "archivoDesdeCliente.txt";
		byte[] contenidoArchivo = Files.readAllBytes(
				Paths.get("C:\\Servers\\glassfish7\\README.txt"));
		String archivoBase64 = Base64.getEncoder().encodeToString(contenidoArchivo);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		connection.setDoOutput(true);
		
		String request = "" 
				+ "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:end=\"http://endpoint.jakarta.banxico.org.mx/\">"
				+ "<soapenv:Header/>"
				+ " <soapenv:Body>"
				+ "     <end:ejercicio7>"
				+ "        <arg0>" + nombreArchivo + "</arg0>"
				+ "        <arg1>" + archivoBase64 + "</arg1>"
				+ "     </end:ejercicio7>"
				+ "  </soapenv:Body>"
				+ "</soapenv:Envelope>";	
		
		connection.getOutputStream().write(request.getBytes("UTF-8"));
		int codigoRespuesta = connection.getResponseCode();
		System.out.println("codigo: " + codigoRespuesta);
		
//		BufferedReader lector = new BufferedReader(
//			new InputStreamReader(connection.getInputStream()));
//		
//		String xml = lector.lines().collect(Collectors.joining(""));
		
		System.out.println("Resultado de la respuesta del WS: " + 
				procesarRespuesta(connection.getInputStream()));
	}
	
	public static String procesarRespuesta(InputStream inputStream) 
			throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
		DocumentBuilder constructor = fabrica.newDocumentBuilder();
		
		Document documento = constructor.parse(inputStream);
		NodeList listaNodos = documento.getElementsByTagName("return");
		
		if (listaNodos.getLength() > 0) {
			Node nodo = listaNodos.item(0);
			return nodo.getTextContent();
		}
		return null;
	}
}
