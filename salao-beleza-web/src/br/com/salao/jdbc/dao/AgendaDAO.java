package br.com.salao.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.salao.jdbc.ConexaoUtil;
import br.com.salao.jdbc.dto.AgendaDTO;
import br.com.salao.jdbc.dto.ClienteDTO;
import br.com.salao.jdbc.dto.EnderecoDTO;
import br.com.salao.jdbc.dto.EspecialidadeDTO;
import br.com.salao.jdbc.dto.ProfissionalDTO;
import br.com.salao.jdbc.dto.ServicoDTO;
import br.com.salao.jdbc.dto.UfDTO;
import br.com.salao.jdbc.exception.PersistenciaException;


public class AgendaDAO implements GenericoDAO<AgendaDTO> {

	@Override
	public void inserir(AgendaDTO agendaDTO) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			String sql = "INSERT INTO agenda(data, horario, observacoes, idProfissional, idCliente, idServico) " +
					"VALUES(?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDate(1, new Date(agendaDTO.getData().getTime()));
			statement.setDate(2, new Date(agendaDTO.getHora().getTime()));
			statement.setString(3, agendaDTO.getObservacoes());
			statement.setInt(4, agendaDTO.getProfissionalDTO().getIdPessoa());
			statement.setInt(5, agendaDTO.getClienteDTO().getIdCliente());
			
			statement.execute();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	
	@Override
	public void atualizar(AgendaDTO agendaDTO) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "UPDATE agenda " +
					" SET data = ?, " +
					" horario = ?," +
					" observacoes = ?," +
					" idProfissional = ?, " +
					" idCliente = ?, " +
					" idServico = ?, " +
					" WHERE idAgenda = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDate(1, new Date(agendaDTO.getData().getTime()));
			statement.setDate(2, new Date(agendaDTO.getHora().getTime()));
			statement.setString(3, agendaDTO.getObservacoes());
			statement.setInt(4, agendaDTO.getProfissionalDTO().getIdPessoa());
			statement.setInt(5, agendaDTO.getClienteDTO().getIdCliente());
			
			statement.execute();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}
	
	
	@Override
	public void deletar(Integer idAgenda) throws PersistenciaException {
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "DELETE FROM agenda WHERE idAgenda = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idAgenda);
			
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
			
			String sql = "DELETE FROM ageda";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.execute();
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
	}

	@Override
	public List<AgendaDTO> listarTodos() throws PersistenciaException {
		List<AgendaDTO> listaAgenda = new ArrayList<AgendaDTO>();
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM ageda";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				AgendaDTO agendaDTO = new AgendaDTO();
				agendaDTO.setIdAgenda(resultSet.getInt("idAgenda"));
				agendaDTO.setData(resultSet.getDate("data"));
				agendaDTO.setHora(resultSet.getDate("horario"));
				agendaDTO.setObservacoes(resultSet.getString("observacoes"));
				agendaDTO.setProfissionalDTO(buscaProfissionalPorId(resultSet.getInt("idProfissional")));
				agendaDTO.setClienteDTO(buscaClientePorId(resultSet.getInt("idCliente")));
				agendaDTO.setServicoDTO(buscaServicoPorId(resultSet.getInt("idServico")));
				
				listaAgenda.add(agendaDTO);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return listaAgenda;
	}
	
	@Override
	public AgendaDTO buscarPorId(Integer id) throws PersistenciaException {
		AgendaDTO agendaDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM agenda WHERE idAgenda = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				agendaDTO = new AgendaDTO();
				agendaDTO.setIdAgenda(resultSet.getInt("idAgenda"));
				agendaDTO.setData(resultSet.getDate("data"));
				agendaDTO.setHora(resultSet.getDate("horario"));
				agendaDTO.setObservacoes(resultSet.getString("observacoes"));
				agendaDTO.setProfissionalDTO(buscaProfissionalPorId(resultSet.getInt("idProfissional")));
				agendaDTO.setClienteDTO(buscaClientePorId(resultSet.getInt("idCliente")));
				agendaDTO.setServicoDTO(buscaServicoPorId(resultSet.getInt("idServico")));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return agendaDTO;
	}
	
	private ProfissionalDTO buscaProfissionalPorId(int idProfissional) throws PersistenciaException {
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
	
	private ClienteDTO buscaClientePorId(int idCliente) throws PersistenciaException {
		ClienteDTO clienteDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM cliente WHERE idCliente = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idCliente);
			
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				clienteDTO = new ClienteDTO();
				clienteDTO.setIdCliente(result.getInt(1));
				clienteDTO.setNomeCliente(result.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return clienteDTO;
	}
	
	private ServicoDTO buscaServicoPorId(int idServico) throws PersistenciaException {
		ServicoDTO servicoDTO = null;
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM cliente WHERE idCliente = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idServico);
			
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				servicoDTO = new ServicoDTO();
				servicoDTO.setIdServico(result.getInt(1));
				servicoDTO.setNomeServico(result.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return servicoDTO;
	}
}
