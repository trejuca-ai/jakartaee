package mx.org.banxico.jakarta.jaxb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class ActorFechaAdapter extends XmlAdapter<String, Date> {

	private static DateFormat formatoFecha;
	
	static {
		formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	@Override
	public Date unmarshal(String v) throws Exception {
		return formatoFecha.parse(v);
	}

	@Override
	public String marshal(Date v) throws Exception {
		return formatoFecha.format(v);
	}
}
