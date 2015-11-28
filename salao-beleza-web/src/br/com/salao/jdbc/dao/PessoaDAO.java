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
import br.com.salao.jdbc.dto.EspecialidadeDTO;
import br.com.salao.jdbc.dto.ProfissionalDTO;
import br.com.salao.jdbc.dto.UfDTO;
import br.com.salao.jdbc.exception.PersistenciaException;


public class PessoaDAO implements GenericoDAO<ProfissionalDTO> {

	@Override
	public void inserir(ProfissionalDTO profissionalDTO) throws PersistenciaException {
		try {
			int chaveEnd = insereEndereco(profissionalDTO.getEnderecoDTO());
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "INSERT INTO profissional(nomeProfissional, cpf, sexo, dtNascimento, email, senha, celular, idEndereco, idEspecialidade) " +
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, profissionalDTO.getNome());
			statement.setLong(2, profissionalDTO.getCpf());
			statement.setString(3, String.valueOf(profissionalDTO.getSexo()));
			statement.setDate(4, new Date(profissionalDTO.getDtNascimento().getTime()));
			statement.setString(5, profissionalDTO.getEmail());
			statement.setString(6, profissionalDTO.getSenha());
			statement.setString(7, profissionalDTO.getCelular());
			statement.setInt(8, chaveEnd);
			statement.setInt(9, profissionalDTO.getEspecialidadeDTO().getIdEspecialidade());
			
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
	public void atualizar(ProfissionalDTO pessoaDTO) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "UPDATE profissional " +
					" SET nomeProfissional = ?, " +
					" cpf = ?," +
					" sexo = ?," +
					" dtNascimento = ?, " +
					" email = ?, " +
					" senha = ?, " +
					" celular = ?, " +
					" idEspecialidade = ? " +
					" WHERE idProfissional = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, pessoaDTO.getNome());
			statement.setLong(2, pessoaDTO.getCpf());
			statement.setString(3, String.valueOf(pessoaDTO.getSexo()));
			statement.setDate(4, new Date(pessoaDTO.getDtNascimento().getTime()));
			statement.setString(5, pessoaDTO.getEmail());
			statement.setString(6, pessoaDTO.getSenha());
			statement.setString(7, pessoaDTO.getCelular());
			statement.setInt(8, pessoaDTO.getEspecialidadeDTO().getIdEspecialidade());
			statement.setInt(9, pessoaDTO.getIdPessoa());
			
			statement.execute();
			connection.close();
			// Atualiza agora o relacionamento da pessoa
			atualizaEndereco(pessoaDTO.getEnderecoDTO());
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
	public void deletar(Integer idProfissional) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "DELETE FROM profissional WHERE idProfissional = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idProfissional);
			
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
			
			String sql = "DELETE FROM profissional";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.execute();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public List<ProfissionalDTO> listarTodos() throws PersistenciaException {
		List<ProfissionalDTO> listaPessoas = new ArrayList<ProfissionalDTO>();
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM profissional";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				ProfissionalDTO profissionalDTO = new ProfissionalDTO();
				profissionalDTO.setIdPessoa(resultSet.getInt("idProfissional"));
				profissionalDTO.setNome(resultSet.getString("nomeProfissional"));
				profissionalDTO.setCpf(resultSet.getLong("cpf"));
				profissionalDTO.setDtNascimento(resultSet.getDate("dtNascimento"));
				profissionalDTO.setSexo(resultSet.getString("sexo").charAt(0));
				profissionalDTO.setEmail(resultSet.getString("email"));
				profissionalDTO.setSenha(resultSet.getString("senha"));
				profissionalDTO.setCelular(resultSet.getString("celular"));
				profissionalDTO.setEnderecoDTO(buscaEnderecoPorId(resultSet.getInt("idEndereco")));
				profissionalDTO.setEspecialidadeDTO(buscaEspecialidadePorId(resultSet.getInt("idEspecialidade")));
				
				listaPessoas.add(profissionalDTO);
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
	
	public EspecialidadeDTO buscaEspecialidadePorId(int idEspecialidade) throws PersistenciaException {
		EspecialidadeDTO especialidadeDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM especialidade WHERE idEspecialidade = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idEspecialidade);
			
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				especialidadeDTO = new EspecialidadeDTO();
				especialidadeDTO.setIdEspecialidade(result.getInt(1));
				especialidadeDTO.setDescricao(result.getString(2));
	
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return especialidadeDTO;
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
	public ProfissionalDTO buscarPorId(Integer id) throws PersistenciaException {
		ProfissionalDTO profissionalDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM profissional WHERE idProfissional = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				profissionalDTO = new ProfissionalDTO();
				profissionalDTO.setIdPessoa(resultSet.getInt("idProfissional"));
				profissionalDTO.setNome(resultSet.getString("nomeProfissional"));
				profissionalDTO.setCpf(resultSet.getLong("cpf"));
				profissionalDTO.setDtNascimento(resultSet.getDate("dtNascimento"));
				profissionalDTO.setSexo(resultSet.getString("sexo").charAt(0));
				profissionalDTO.setEmail(resultSet.getString("email"));
				profissionalDTO.setSenha(resultSet.getString("senha"));
				profissionalDTO.setCelular(resultSet.getString("celular"));
				profissionalDTO.setEnderecoDTO(buscaEnderecoPorId(resultSet.getInt("idEndereco")));
				profissionalDTO.setEspecialidadeDTO(buscaEspecialidadePorId(resultSet.getInt("idEspecialidade")));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return profissionalDTO;
	}
	
	public List<ProfissionalDTO> filtraPessoa(String nome, Long cpf, String sexo, String orderBy) throws PersistenciaException {
		List<ProfissionalDTO> lista = new ArrayList<ProfissionalDTO>();
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM usuario ";
			boolean ultimo = false;
			if (nome != null && !nome.equals("")) {
				sql += " WHERE NOME LIKE ?";
				ultimo = true;
			}
			
			if (cpf != null && !cpf.equals("")) {
				if (ultimo) {
					sql += " AND ";
				} else {
					sql += " WHERE ";
					ultimo = true;
				}
				sql += " CPF LIKE ?";
			}
			
			if (sexo != null && !sexo.equals("")) {
				if (ultimo) {
					sql += " AND ";
				} else {
					sql += " WHERE ";
				}
				sql += " SEXO = ?";
			}
			sql += " ORDER BY " + orderBy;
			
			PreparedStatement statement = connection.prepareStatement(sql);
			int cont = 0;
			if (nome != null && !nome.equals("")) {
				statement.setString(++cont, "%" + nome + "%");
			}
			
			if (cpf != null && !cpf.equals("")) {
				statement.setString(++cont, "%" + cpf + "%");
			}
			
			if (sexo != null && !sexo.equals("")) {
				statement.setString(++cont, sexo);
			}
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				ProfissionalDTO pessoaDTO = new ProfissionalDTO();
				pessoaDTO.setIdPessoa(resultSet.getInt("id_pessoa"));
				pessoaDTO.setNome(resultSet.getString("nome"));
				pessoaDTO.setCpf(resultSet.getLong("cpf"));
				pessoaDTO.setDtNascimento(resultSet.getDate("dt_nasc"));
				pessoaDTO.setSexo(resultSet.getString("sexo").charAt(0));
				
				lista.add(pessoaDTO);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return lista;
	}
}
