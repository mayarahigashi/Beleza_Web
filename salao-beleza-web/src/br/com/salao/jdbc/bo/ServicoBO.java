package br.com.salao.jdbc.bo;

import java.util.List;

import br.com.salao.jdbc.dao.ServicoDAO;
import br.com.salao.jdbc.dto.ServicoDTO;
import br.com.salao.jdbc.exception.NegocioException;

public class ServicoBO {

	public void cadastrar(ServicoDTO servicoDTO) throws NegocioException {
		try {
			ServicoDAO servicoDAO = new ServicoDAO();
			servicoDAO.inserir(servicoDTO);
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		}
	}
	
	public List<ServicoDTO> listagem() throws NegocioException {
		try {
			ServicoDAO servicoDAO = new ServicoDAO();
			return servicoDAO.listarTodos();
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		} 
	}
	
	public void removeServico(Integer idServico) throws NegocioException {
		try {
			ServicoDAO servicoDAO = new ServicoDAO();
			servicoDAO.deletar(idServico);
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		}
	}

	public void removeTudo() throws NegocioException {
		try {
			ServicoDAO servicoDAO = new ServicoDAO();
			servicoDAO.deletarTudo();
		} catch(Exception e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	public ServicoDTO buscaPorId(Integer idServico) throws NegocioException {
		ServicoDTO servicoDTO = null;
		try {
			ServicoDAO servicoDAO = new ServicoDAO();
			servicoDTO = servicoDAO.buscarPorId(idServico);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
		return servicoDTO;
	}
	
	public void atualizar(ServicoDTO servicoDTO) throws NegocioException {
		try {
			ServicoDAO servicoDAO = new ServicoDAO();
			servicoDAO.atualizar(servicoDTO);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
}
