package br.com.salao.jdbc.bo;

import java.util.List;

import br.com.salao.jdbc.dao.AgendaDAO;
import br.com.salao.jdbc.dto.AgendaDTO;
import br.com.salao.jdbc.exception.NegocioException;

public class AgendaBO {
	
	public void cadastrar(AgendaDTO profissionalDTO) throws NegocioException {
		try {
			AgendaDAO agendaDAO = new AgendaDAO();
			agendaDAO.inserir(profissionalDTO);
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		}
	}
	
	public List<AgendaDTO> listagem() throws NegocioException {
		try {
			AgendaDAO agendaDAO = new AgendaDAO();
			return agendaDAO.listarTodos();
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		} 
	}
	
	public void removeTudo() throws NegocioException {
		try {
			AgendaDAO agendaDAO = new AgendaDAO();
			agendaDAO.deletarTudo();
		} catch(Exception e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	public AgendaDTO buscaPorId(Integer idPessoa) throws NegocioException {
		AgendaDTO agendaDTO = null;
		try {
			AgendaDAO agendaDAO = new AgendaDAO();
			agendaDTO = agendaDAO.buscarPorId(idPessoa);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
		return agendaDTO;
	}
	
	public void atualizar(AgendaDTO agendaDTO) throws NegocioException {
		try {
			AgendaDAO agendaDAO = new AgendaDAO();
			agendaDAO.atualizar(agendaDTO);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
}
