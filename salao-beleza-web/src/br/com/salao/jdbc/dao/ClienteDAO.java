package br.com.salao.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.salao.jdbc.ConexaoUtil;
import br.com.salao.jdbc.dto.EnderecoDTO;
import br.com.salao.jdbc.dto.ClienteDTO;
import br.com.salao.jdbc.dto.UfDTO;
import br.com.salao.jdbc.exception.PersistenciaException;


public class ClienteDAO implements GenericoDAO<ClienteDTO> {

	@Override
	public void inserir(ClienteDTO clienteDTO) throws PersistenciaException {
		try {
			int chaveEnd = insereEndereco(clienteDTO.getEnderecoDTO());
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "INSERT INTO cliente(nomeCliente, cpf, sexo, dtNascimento, email, celular, idEndereco) " +
					"VALUES(?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, clienteDTO.getNomeCliente());
			statement.setLong(2, clienteDTO.getCpf());
			statement.setString(3, String.valueOf(clienteDTO.getSexo()));
			statement.setDate(4, new Date(clienteDTO.getDtNascimento().getTime()));
			statement.setString(5, clienteDTO.getEmail());
			statement.setString(6, clienteDTO.getCelular());
			statement.setInt(7, chaveEnd);
			
			statement.execute();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	private int insereEndereco(EnderecoDTO enderecoDTO) throws PersistenciaException {
		int chave = 0;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "INSERT INTO endereco(logradouro, bairro, cidade, numero, cep, idUf) " +
					"VALUES(?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, enderecoDTO.getLogradouro());
			statement.setString(2, enderecoDTO.getBairro());
			statement.setString(3, enderecoDTO.getCidade());
			statement.setLong(4, enderecoDTO.getNumero());
			statement.setInt(5, enderecoDTO.getCep());
			statement.setInt(6, enderecoDTO.getUfDTO().getIdUf());
			
			statement.execute();
			ResultSet result = statement.getGeneratedKeys();
			if (result.next()) {
				chave = result.getInt(1);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return chave;
	}
	
	
	@Override
	public void atualizar(ClienteDTO clienteDTO) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "UPDATE cliente " +
					" SET nomeCliente = ?, " +
					" cpf = ?," +
					" sexo = ?," +
					" dtNascimento = ?, " +
					" email = ?, " +
					" celular = ? " +
					" WHERE idCliente = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, clienteDTO.getNomeCliente());
			statement.setLong(2, clienteDTO.getCpf());
			statement.setString(3, String.valueOf(clienteDTO.getSexo()));
			statement.setDate(4, new Date(clienteDTO.getDtNascimento().getTime()));
			statement.setString(5, clienteDTO.getEmail());
			statement.setString(6, clienteDTO.getCelular());
			statement.setInt(7, clienteDTO.getIdCliente());
			
			statement.execute();
			connection.close();
			// Atualiza agora o relacionamento da pessoa
			atualizaEndereco(clienteDTO.getEnderecoDTO());
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}
	
	private void atualizaEndereco(EnderecoDTO enderecoDTO) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "UPDATE endereco " +
					" SET logradouro = ?," +
					" bairro = ?, " +
					" cidade = ?," +
					" numero = ?," +
					" cep = ?," +
					" idUf = ?" +
					" WHERE idEndereco = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, enderecoDTO.getLogradouro());
			preparedStatement.setString(2, enderecoDTO.getBairro());
			preparedStatement.setString(3, enderecoDTO.getCidade());
			preparedStatement.setLong(4, enderecoDTO.getNumero());
			preparedStatement.setInt(5, enderecoDTO.getCep());
			preparedStatement.setInt(6, enderecoDTO.getUfDTO().getIdUf());
			preparedStatement.setInt(7, enderecoDTO.getIdEndereco());
			
			preparedStatement.execute();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public void deletar(Integer idCliente) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "DELETE FROM cliente WHERE idCliente = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idCliente);
			
			statement.execute();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}
	
	public void deletarEndereco(Integer idEndereco) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "DELETE FROM endereco WHERE idEndereco = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idEndereco);
			
			statement.execute();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}
	
	public void deletarTudo() throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "DELETE FROM cliente";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.execute();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public List<ClienteDTO> listarTodos() throws PersistenciaException {
		List<ClienteDTO> listaPessoas = new ArrayList<ClienteDTO>();
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM cliente";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				ClienteDTO clienteDTO = new ClienteDTO();
				clienteDTO.setIdCliente(resultSet.getInt("idCliente"));
				clienteDTO.setNomeCliente(resultSet.getString("nomeCliente"));
				clienteDTO.setCpf(resultSet.getLong("cpf"));
				clienteDTO.setDtNascimento(resultSet.getDate("dtNascimento"));
				clienteDTO.setSexo(resultSet.getString("sexo").charAt(0));
				clienteDTO.setEmail(resultSet.getString("email"));
				clienteDTO.setCelular(resultSet.getString("celular"));
				clienteDTO.setEnderecoDTO(buscaEnderecoPorId(resultSet.getInt("idEndereco")));
				
				listaPessoas.add(clienteDTO);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaPessoas;
	}
	
	public EnderecoDTO buscaEnderecoPorId(int idEndereco) throws PersistenciaException {
		EnderecoDTO enderecoDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM endereco WHERE idEndereco = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idEndereco);
			
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				enderecoDTO = new EnderecoDTO();
				enderecoDTO.setIdEndereco(result.getInt(1));
				enderecoDTO.setLogradouro(result.getString(2));
				enderecoDTO.setBairro(result.getString(3));
				enderecoDTO.setCidade(result.getString(4));
				enderecoDTO.setNumero(result.getLong(5));
				enderecoDTO.setCep(result.getInt(6));
				enderecoDTO.setUfDTO(buscaUFPorID(result.getInt(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return enderecoDTO;
	}
	
	
	
	private UfDTO buscaUFPorID(int idUf) throws PersistenciaException {
		UfDTO ufDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM tabela_uf WHERE idUf = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idUf);
			
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				ufDTO = new UfDTO();
				ufDTO.setIdUf(result.getInt(1));
				ufDTO.setSiglaUf(result.getString(2));
				ufDTO.setDescricao(result.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return ufDTO;
	}

	@Override
	public ClienteDTO buscarPorId(Integer id) throws PersistenciaException {
		ClienteDTO clienteDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM cliente WHERE idCliente = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				clienteDTO = new ClienteDTO();
				clienteDTO.setIdCliente(resultSet.getInt("idCliente"));
				clienteDTO.setNomeCliente(resultSet.getString("nomeCliente"));
				clienteDTO.setCpf(resultSet.getLong("cpf"));
				clienteDTO.setDtNascimento(resultSet.getDate("dtNascimento"));
				clienteDTO.setSexo(resultSet.getString("sexo").charAt(0));
				clienteDTO.setEmail(resultSet.getString("email"));
				clienteDTO.setCelular(resultSet.getString("celular"));
				clienteDTO.setEnderecoDTO(buscaEnderecoPorId(resultSet.getInt("idEndereco")));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return clienteDTO;
	}
	
	
}
