package br.com.salao.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.salao.jdbc.ConexaoUtil;
import br.com.salao.jdbc.dto.ProfissionalDTO;
import br.com.salao.jdbc.dto.ServicoDTO;
import br.com.salao.jdbc.exception.PersistenciaException;


public class ServicoDAO implements GenericoDAO<ServicoDTO> {

	@Override
	public void inserir(ServicoDTO servicoDTO) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "INSERT INTO servico(nomeServico, valorServico, tempoExecucao, observacoes, idProfissional) " +
					"VALUES(?, ?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, servicoDTO.getNomeServico());
			statement.setDouble(2, servicoDTO.getValorServico());
			statement.setString(3, servicoDTO.getTempoExecucao());
			statement.setString(4, servicoDTO.getObservacoes());
			statement.setInt(5, servicoDTO.getProfissionalDTO().getIdPessoa());
			
			statement.execute();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	
	@Override
	public void atualizar(ServicoDTO servicoDTO) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "UPDATE servico " +
					" SET nomeServico = ?, " +
					" valorServico = ?," +
					" tempoExecucao = ?," +
					" observacoes = ?, " +
					" idProfissional = ? " +
					" WHERE idServico = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, servicoDTO.getNomeServico());
			statement.setDouble(2, servicoDTO.getValorServico());
			statement.setString(3, servicoDTO.getTempoExecucao());
			statement.setString(4, servicoDTO.getObservacoes());
			statement.setInt(5, servicoDTO.getProfissionalDTO().getIdPessoa());
			statement.setInt(6, servicoDTO.getIdServico());
			
			statement.execute();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}
	
	@Override
	public void deletar(Integer idServico) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "DELETE FROM servico WHERE idServico = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idServico);
			
			statement.execute();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}
	
	public void deletarEndereco(Integer idServico) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "DELETE FROM servico WHERE idServico = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idServico);
			
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
			
			String sql = "DELETE FROM servico";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.execute();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public List<ServicoDTO> listarTodos() throws PersistenciaException {
		List<ServicoDTO> listaServicos = new ArrayList<ServicoDTO>();
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM servico";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				ServicoDTO servicoDTO = new ServicoDTO();
				servicoDTO.setIdServico(resultSet.getInt("idServico"));
				servicoDTO.setNomeServico(resultSet.getString("nomeServico"));
				servicoDTO.setValorServico(resultSet.getDouble("valorServico"));
				servicoDTO.setTempoExecucao(resultSet.getString("tempoExecucao"));
				servicoDTO.setObservacoes(resultSet.getString("observacoes"));
				servicoDTO.setProfissionalDTO(buscaProfissionalPorId(resultSet.getInt("idProfissional")));
				
				listaServicos.add(servicoDTO);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaServicos;
	}
	
	public ProfissionalDTO buscaProfissionalPorId(int idProfissional) throws PersistenciaException {
		ProfissionalDTO profissionalDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM profissional WHERE idProfissional = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idProfissional);
			
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				profissionalDTO = new ProfissionalDTO();
				profissionalDTO.setIdPessoa(result.getInt(1));
				profissionalDTO.setNome(result.getString(2));
	
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return profissionalDTO;
	}
	
	@Override
	public ServicoDTO buscarPorId(Integer id) throws PersistenciaException {
		ServicoDTO servicoDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM servico WHERE idServico = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				servicoDTO = new ServicoDTO();
				servicoDTO.setIdServico(resultSet.getInt("idServico"));
				servicoDTO.setNomeServico(resultSet.getString("nomeServico"));
				servicoDTO.setValorServico(resultSet.getDouble("valorServico"));
				servicoDTO.setTempoExecucao(resultSet.getString("tempoExecucao"));
				servicoDTO.setObservacoes(resultSet.getString("observacoes"));
				servicoDTO.setProfissionalDTO(buscaProfissionalPorId(resultSet.getInt("idProfissional")));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return servicoDTO;
	}
	
}
