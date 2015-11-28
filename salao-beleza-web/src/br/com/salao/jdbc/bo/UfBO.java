package br.com.salao.jdbc.bo;

import java.util.List;

import br.com.salao.jdbc.dao.UfDAO;
import br.com.salao.jdbc.dto.UfDTO;
import br.com.salao.jdbc.exception.NegocioException;

public class UfBO {

	public List<UfDTO> listaUfs() throws NegocioException {
		List<UfDTO> lista = null;
		try {
			UfDAO ufDAO = new UfDAO();
			lista = ufDAO.listaEstados();
		} catch (Exception e) {
			throw new NegocioException(e.getMessage(), e);
		}
		return lista;
	}
	
}
