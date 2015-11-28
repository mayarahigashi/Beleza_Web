package br.com.salao.jdbc.bo;

import java.util.List;

import br.com.salao.jdbc.dao.EspecialidadeDAO;
import br.com.salao.jdbc.dto.EspecialidadeDTO;
import br.com.salao.jdbc.exception.NegocioException;

public class EspecialidadeBO {

	public List<EspecialidadeDTO> listaEspecialidades() throws NegocioException {
		List<EspecialidadeDTO> lista = null;
		try {
			EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
			lista = especialidadeDAO.listaEspecialidades();
		} catch (Exception e) {
			throw new NegocioException(e.getMessage(), e);
		}
		return lista;
	}
	
}
