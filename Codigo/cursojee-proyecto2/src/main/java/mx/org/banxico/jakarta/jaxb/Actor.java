package mx.org.banxico.jakarta.jaxb;

import java.util.Date;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "actor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
	propOrder = {"id","fechaNacimiento","apellido","peliculas","genero"})
public class Actor {

	@XmlAttribute
	private Integer id;
	//@XmlElement
	@XmlTransient
	private String nombre;
	@XmlElement
	private String apellido;
	@XmlElement
	private Genero genero;
	@XmlJavaTypeAdapter(ActorFechaAdapter.class)
	private Date fechaNacimiento;
	@XmlElement(name = "pelicula")
	@XmlElementWrapper(name = "peliculas")
	private List<Pelicula> peliculas;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public List<Pelicula> getPeliculas() {
		return peliculas;
	}
	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	@Override
	public String toString() {
		return "Actor [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", genero=" + genero
				+ ", fechaNacimiento=" + fechaNacimiento + ", peliculas=" + peliculas + "]";
	}
}

@XmlEnum
enum Genero {
	@XmlEnumValue("Masculino")
	MASCULINO,
	@XmlEnumValue("Femenino")
	FEMENINO
}
