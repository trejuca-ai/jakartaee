package mx.org.banxico.jakarta.endpoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ArchivoHelper {

	public static boolean guardarArchivo(
			String nombreArchivo,
			byte[] contenido) {
		
		try {
			File archivo = new File("c:\\Servers\\" + nombreArchivo);
			
			OutputStream stream = new FileOutputStream(archivo);
			stream.write(contenido);
			stream.close();
			
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
