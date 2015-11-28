package br.com.salao.jdbc.dto;

public class ServicoDTO {
	
	private int idServico;
	private String nomeServico;
	private Double valorServico;
	private String tempoExecucao;
	private String observacoes;
	private ProfissionalDTO profissionalDTO;
	
	public int getIdServico() {
		return idServico;
	}
	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}
	public String getNomeServico() {
		return nomeServico;
	}
	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}
	public Double getValorServico() {
		return valorServico;
	}
	public void setValorServico(Double valorServico) {
		this.valorServico = valorServico;
	}
	public String getTempoExecucao() {
		return tempoExecucao;
	}
	public void setTempoExecucao(String tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public ProfissionalDTO getProfissionalDTO() {
		return profissionalDTO;
	}
	public void setProfissionalDTO(ProfissionalDTO profissionalDTO) {
		this.profissionalDTO = profissionalDTO;
	}
	
	

}
