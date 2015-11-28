package br.com.salao.jdbc.bo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.salao.jdbc.dao.ClienteDAO;
import br.com.salao.jdbc.dto.ClienteDTO;
import br.com.salao.jdbc.exception.NegocioException;
import br.com.salao.jdbc.exception.ValidacaoException;

public class ClienteBO {

	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public void cadastrar(ClienteDTO clienteDTO) throws NegocioException {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.inserir(clienteDTO);
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		}
	}
	
	public List<ClienteDTO> listagem() throws NegocioException {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			return clienteDAO.listarTodos();
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		} 
	}
	
	public boolean validaNome(String nome) throws ValidacaoException {
		boolean ehValido = true;
		if (nome == null || nome.equals("")) {
			ehValido = false;
			JOptionPane.showMessageDialog(null, "Campo nome é obrigatório!");
			throw new ValidacaoException("Campo nome é obrigatório!");
		} else if (nome.length() > 30) {
			ehValido = false;
			JOptionPane.showMessageDialog(null, "Campo nome comporta no máximo 30 caracteres!");
			throw new ValidacaoException("Campo nome comporta no máximo 30 chars!");
		}
		return ehValido;
	}
	
	public boolean validaCpf(String cpf) throws ValidacaoException {
		boolean ehValido = true;
		if (cpf == null || cpf.equals("")) {
			ehValido = false;
			JOptionPane.showMessageDialog(null, "Campo CPF é obrigatório!");
			throw new ValidacaoException("Campo CPF é obrigatório!");
		} else if (cpf.length() != 11) {
			ehValido = false;
			JOptionPane.showMessageDialog(null, "Campo CPF deve ter 11 dígitos!");
			throw new ValidacaoException("Campo CPF deve ter 11 dígitos!");
		} else {
			char[] digitos = cpf.toCharArray();
			for (char digito : digitos) {
				if (!Character.isDigit(digito)) {
					ehValido = false;
					JOptionPane.showMessageDialog(null, "Campo CPF é somente numérico!");
					throw new ValidacaoException("Campo CPF é somente numérico!");
				}
			}
		}
		return ehValido;
	}
	
	public boolean validaDtNasc(String dtNasc) throws ValidacaoException {
		boolean ehValido = true;
		if (dtNasc == null || dtNasc.equals("")) {
			ehValido = false;
			JOptionPane.showMessageDialog(null, "Campo Dt. Nasc. é obrigatório!");
			throw new ValidacaoException("Campo Dt. Nasc. é obrigatório!");
		} else {
			ehValido = false;
			try {
				dateFormat.parse(dtNasc);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Formato inválido de data!!");
				throw new ValidacaoException("Formato inválido de data!");
			}
		}
		return ehValido;
	}
	
	public void removePessoa(Integer idPessoa, Integer idEndereco) throws NegocioException {
		try {
			ClienteDAO ClienteDAO = new ClienteDAO();
			ClienteDAO.deletar(idPessoa);
			ClienteDAO.deletarEndereco(idEndereco);
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		}
	}

	public void removeTudo() throws NegocioException {
		try {
			ClienteDAO ClienteDAO = new ClienteDAO();
			ClienteDAO.deletarTudo();
		} catch(Exception e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	public ClienteDTO buscaPorId(Integer idPessoa) throws NegocioException {
		ClienteDTO clienteDTO = null;
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDTO = clienteDAO.buscarPorId(idPessoa);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
		return clienteDTO;
	}
	
	public void atualizar(ClienteDTO clienteDTO) throws NegocioException {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.atualizar(clienteDTO);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
}
