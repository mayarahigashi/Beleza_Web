package br.com.salao.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.salao.jdbc.ConexaoUtil;
import br.com.salao.jdbc.dto.EspecialidadeDTO;
import br.com.salao.jdbc.exception.PersistenciaException;

public class EspecialidadeDAO {

	public List<EspecialidadeDTO> listaEspecialidades() throws PersistenciaException {
		List<EspecialidadeDTO> lista = new ArrayList<EspecialidadeDTO>();
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM especialidade";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultado = preparedStatement.executeQuery();
			while (resultado.next()) {
				EspecialidadeDTO especialidadeDTO = new EspecialidadeDTO();
				especialidadeDTO.setIdEspecialidade(resultado.getInt(1));
				especialidadeDTO.setDescricao(resultado.getString(2));
				
				lista.add(especialidadeDTO);
			}
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return lista;
	}

}
