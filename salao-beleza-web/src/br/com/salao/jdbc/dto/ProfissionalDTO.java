package br.com.salao.jdbc.dto;

import java.util.Date;

public class ProfissionalDTO {

	private int idPessoa;
	private String nome;
	private Long cpf;
	private char sexo;
	private Date dtNascimento;
	private String email;
	private String senha;
	private String celular;
	private EnderecoDTO enderecoDTO;
	private EspecialidadeDTO especialidadeDTO;
	
	public Integer getIdPessoa() {
		return idPessoa;
	}

	public EspecialidadeDTO getEspecialidadeDTO() {
		return especialidadeDTO;
	}

	public void setEspecialidadeDTO(EspecialidadeDTO especialidadeDTO) {
		this.especialidadeDTO = especialidadeDTO;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public EnderecoDTO getEnderecoDTO() {
		return enderecoDTO;
	}

	public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
}
