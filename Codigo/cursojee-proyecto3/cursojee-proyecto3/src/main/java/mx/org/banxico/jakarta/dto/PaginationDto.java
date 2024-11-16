package mx.org.banxico.jakarta.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaginationDto<T> {
	private Integer numeroPagina;
	private Integer tamanioPagina;
	private Long totalElementos;
	private Long totalPaginas;
	private List<T> contenido;
}
