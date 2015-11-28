package br.com.salao.jdbc.dto;

import java.io.Serializable;

public class UfDTO implements Serializable {

	private static final long serialVersionUID = -920832010032279616L;

	private int idUf;
	
	private String siglaUf;
	
	private String descricao;

	public int getIdUf() {
		return idUf;
	}

	public void setIdUf(int idUf) {
		this.idUf = idUf;
	}

	public String getSiglaUf() {
		return siglaUf;
	}

	public void setSiglaUf(String siglaUf) {
		this.siglaUf = siglaUf;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
