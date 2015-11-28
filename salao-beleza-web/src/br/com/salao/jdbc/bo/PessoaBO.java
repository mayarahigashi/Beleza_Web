package br.com.salao.jdbc.bo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.salao.jdbc.dao.PessoaDAO;
import br.com.salao.jdbc.dto.ProfissionalDTO;
import br.com.salao.jdbc.exception.NegocioException;
import br.com.salao.jdbc.exception.ValidacaoException;

public class PessoaBO {

	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public void cadastrar(ProfissionalDTO profissionalDTO) throws NegocioException {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.inserir(profissionalDTO);
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		}
	}
	
	public List<ProfissionalDTO> listagem() throws NegocioException {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			return pessoaDAO.listarTodos();
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		} 
	}
	
	public boolean validaNome(String nome) throws ValidacaoException {
		boolean ehValido = true;
		if (nome == null || nome.equals("")) {
			ehValido = false;
			JOptionPane.showMessageDialog(null, "Campo nome � obrigat�rio!");
			throw new ValidacaoException("Campo nome � obrigat�rio!");
		} else if (nome.length() > 30) {
			ehValido = false;
			JOptionPane.showMessageDialog(null, "Campo nome comporta no m�ximo 30 caracteres!");
			throw new ValidacaoException("Campo nome comporta no m�ximo 30 chars!");
		}
		return ehValido;
	}
	
	public boolean validaCpf(String cpf) throws ValidacaoException {
		boolean ehValido = true;
		if (cpf == null || cpf.equals("")) {
			ehValido = false;
			JOptionPane.showMessageDialog(null, "Campo CPF � obrigat�rio!");
			throw new ValidacaoException("Campo CPF � obrigat�rio!");
		} else if (cpf.length() != 11) {
			ehValido = false;
			JOptionPane.showMessageDialog(null, "Campo CPF deve ter 11 d�gitos!");
			throw new ValidacaoException("Campo CPF deve ter 11 d�gitos!");
		} else {
			char[] digitos = cpf.toCharArray();
			for (char digito : digitos) {
				if (!Character.isDigit(digito)) {
					ehValido = false;
					JOptionPane.showMessageDialog(null, "Campo CPF � somente num�rico!");
					throw new ValidacaoException("Campo CPF � somente num�rico!");
				}
			}
		}
		return ehValido;
	}
	
	public boolean validaDtNasc(String dtNasc) throws ValidacaoException {
		boolean ehValido = true;
		if (dtNasc == null || dtNasc.equals("")) {
			ehValido = false;
			JOptionPane.showMessageDialog(null, "Campo Dt. Nasc. � obrigat�rio!");
			throw new ValidacaoException("Campo Dt. Nasc. � obrigat�rio!");
		} else {
			ehValido = false;
			try {
				dateFormat.parse(dtNasc);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "Formato inv�lido de data!!");
				throw new ValidacaoException("Formato inv�lido de data!");
			}
		}
		return ehValido;
	}
	
	public void removePessoa(Integer idPessoa, Integer idEndereco) throws NegocioException {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.deletar(idPessoa);
			pessoaDAO.deletarEndereco(idEndereco);
		} catch(Exception exception) {
			throw new NegocioException(exception.getMessage());
		}
	}

	public void removeTudo() throws NegocioException {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.deletarTudo();
		} catch(Exception e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	public ProfissionalDTO buscaPorId(Integer idPessoa) throws NegocioException {
		ProfissionalDTO pessoaDTO = null;
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDTO = pessoaDAO.buscarPorId(idPessoa);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
		return pessoaDTO;
	}
	
	public void atualizar(ProfissionalDTO pessoaDTO) throws NegocioException {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.atualizar(pessoaDTO);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
}
