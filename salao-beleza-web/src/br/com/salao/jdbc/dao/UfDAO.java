package br.com.salao.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.salao.jdbc.ConexaoUtil;
import br.com.salao.jdbc.dto.UfDTO;
import br.com.salao.jdbc.exception.PersistenciaException;

public class UfDAO {

	public List<UfDTO> listaEstados() throws PersistenciaException {
		List<UfDTO> lista = new ArrayList<UfDTO>();
		try {
			Connection connection = ConexaoUtil.getInstance().getConnection();
			
			String sql = "SELECT * FROM tabela_uf";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultado = preparedStatement.executeQuery();
			while (resultado.next()) {
				UfDTO ufDTO = new UfDTO();
				ufDTO.setIdUf(resultado.getInt(1));
				ufDTO.setSiglaUf(resultado.getString(2));
				ufDTO.setDescricao(resultado.getString(3));
				
				lista.add(ufDTO);
			}
			connection.close();
		} catch(Exception e) {
			e.printStackTrace();
			throw new PersistenciaException(e.getMessage(), e);
		}
		return lista;
	}

}
