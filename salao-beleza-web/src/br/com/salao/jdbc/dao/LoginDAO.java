package br.com.salao.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.salao.jdbc.ConexaoUtil;
import br.com.salao.jdbc.dto.ClienteDTO;
import br.com.salao.jdbc.dto.LoginDTO;
import br.com.salao.jdbc.exception.PersistenciaException;

public class LoginDAO implements GenericoDAO<LoginDTO>{

	public boolean logar(LoginDTO loginDTO) throws PersistenciaException {
		boolean resultado = false;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM login WHERE nome = ? AND senha = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, loginDTO.getNome());
			statement.setString(2, loginDTO.getSenha());
			
			ResultSet resultSet = statement.executeQuery();
			resultado = resultSet.next();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return resultado;
	}
	
	@Override
	public void inserir(LoginDTO obj) throws PersistenciaException {
	}

	@Override
	public void atualizar(LoginDTO obj) throws PersistenciaException {
	}

	@Override
	public void deletar(Integer id) throws PersistenciaException {
	}

	@Override
	public List<LoginDTO> listarTodos() throws PersistenciaException {
		return null;
	}

	@Override
	public LoginDTO buscarPorId(Integer id) throws PersistenciaException {
		LoginDTO loginDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM login WHERE idLogin = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				loginDTO = new LoginDTO();
				loginDTO.setId(resultSet.getInt("idLogin"));
				loginDTO.setNome(resultSet.getString("nome"));
				loginDTO.setSenha(resultSet.getString("senha"));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return loginDTO;
	}

}
