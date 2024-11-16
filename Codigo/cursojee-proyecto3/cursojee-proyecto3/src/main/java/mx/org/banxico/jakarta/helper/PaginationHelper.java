package mx.org.banxico.jakarta.helper;

import java.util.List;

import jakarta.inject.Inject;
import mx.org.banxico.jakarta.dto.PaginationDto;

public class PaginationHelper<T> {
	
	public static final int PAGE_SIZE = 10;
	public static final int MIN_PAGE = 1;
	
	private PaginationDto<T> paginationData;

	public PaginationHelper() {
		
	}

	@Inject
	public PaginationHelper(PaginationDto<T> paginationData) {
		this.paginationData = paginationData;
	}
	
	public PaginationDto<T> getPaginatedData(
			List<T> data, Long totalData, Integer pageNumber) {
		
		paginationData.setContenido(data);
		paginationData.setTotalElementos(totalData);
		paginationData.setTamanioPagina(PAGE_SIZE);
		paginationData.setTotalPaginas(totalData / PAGE_SIZE);
		paginationData.setNumeroPagina(pageNumber);
		
		return paginationData;
	}
	
	public Integer getPageNumber(Integer pageNumber) {
		return (pageNumber - 1) * PAGE_SIZE;
	}
	
	public Integer getMinPageNumber(Integer pageNumber) {
		return pageNumber < MIN_PAGE
				? MIN_PAGE
				: pageNumber;
	}
}
