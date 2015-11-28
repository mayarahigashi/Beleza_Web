package br.com.salao.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.salao.jdbc.bo.PessoaBO;
import br.com.salao.jdbc.dto.EnderecoDTO;
import br.com.salao.jdbc.dto.EspecialidadeDTO;
import br.com.salao.jdbc.dto.ProfissionalDTO;
import br.com.salao.jdbc.dto.UfDTO;


@WebServlet("/profissional")
public class ProfissionalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String proxPage = "index.jsp";
		try {
			PessoaBO pessoaBO = new PessoaBO();
			if (acao.equals("cadastrar")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String cpf = request.getParameter("cpf");
				String nome = request.getParameter("nome");
				String dtNasc = request.getParameter("dataNascimento");
				String sexo = request.getParameter("sexo");
				String logradouro = request.getParameter("logradouro");
				String bairro = request.getParameter("bairro");
				String cidade = request.getParameter("cidade");
				String numero = request.getParameter("numero");
				String cep = request.getParameter("cep");
				String email = request.getParameter("email");
				String senha = request.getParameter("senha");
				String celular = request.getParameter("telefone");
				String uf = request.getParameter("uf");
				String especialidade = request.getParameter("especialidade");
				
				pessoaBO.validaNome(nome);
				pessoaBO.validaCpf(cpf);
				pessoaBO.validaDtNasc(dtNasc);
				
				ProfissionalDTO profissionalDTO = new ProfissionalDTO();
				profissionalDTO.setCpf(Long.parseLong(cpf));
				profissionalDTO.setDtNascimento(dateFormat.parse(dtNasc));
				
				EnderecoDTO enderecoDTO = new EnderecoDTO();
				enderecoDTO.setBairro(bairro);
				enderecoDTO.setCep(Integer.parseInt(cep));
				enderecoDTO.setCidade(cidade);
				enderecoDTO.setLogradouro(logradouro);
				enderecoDTO.setNumero(Long.parseLong(numero));
				
				UfDTO ufDTO = new UfDTO();
				ufDTO.setIdUf(Integer.parseInt(uf));
				enderecoDTO.setUfDTO(ufDTO);
				profissionalDTO.setEnderecoDTO(enderecoDTO);
				profissionalDTO.setNome(nome);
				profissionalDTO.setSexo(sexo.charAt(0));
				profissionalDTO.setEmail(email);
				profissionalDTO.setSenha(senha);
				profissionalDTO.setCelular(celular);
				
				EspecialidadeDTO especialidadeDTO = new EspecialidadeDTO();
				especialidadeDTO.setIdEspecialidade(Integer.parseInt(especialidade));
				profissionalDTO.setEspecialidadeDTO(especialidadeDTO);
				
				pessoaBO.cadastrar(profissionalDTO);
				request.setAttribute("msg", "Cadastro efetuado com sucesso!");
				proxPage = "profissional?acao=listar";
				} else if (acao.equals("listar")) {
					List<ProfissionalDTO> lista = pessoaBO.listagem();
					request.setAttribute("listaProfissionais", lista);
					proxPage = "listar-profissional.jsp";
				} else if (acao.equals("editar")) {
					String id = request.getParameter("id");
					ProfissionalDTO profissionalDTO = pessoaBO.buscaPorId(Integer.parseInt(id));
					request.setAttribute("profissionalDTO", profissionalDTO);
					proxPage = "editar-profissional.jsp";
				} else if (acao.equals("atualizar")) {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String idProfissional = request.getParameter("id");
					String idEndereco = request.getParameter("idEndereco");
					String idUf = request.getParameter("idUF");
					String cpf = request.getParameter("cpf");
					String nome = request.getParameter("nome");
					String dtNasc = request.getParameter("dataNascimento");
					String sexo = request.getParameter("sexo");
					String logradouro = request.getParameter("logradouro");
					String bairro = request.getParameter("bairro");
					String cidade = request.getParameter("cidade");
					String numero = request.getParameter("numero");
					String cep = request.getParameter("cep");
					String email = request.getParameter("email");
					String senha = request.getParameter("senha");
					String celular = request.getParameter("telefone");
					String uf = request.getParameter("uf");
					String especialidade = request.getParameter("especialidade");
					
					ProfissionalDTO profissionalDTO = new ProfissionalDTO();
					profissionalDTO.setIdPessoa(Integer.parseInt(idProfissional));
					profissionalDTO.setCpf(Long.parseLong(cpf));
					profissionalDTO.setDtNascimento(dateFormat.parse(dtNasc));
					
					EnderecoDTO enderecoDTO = new EnderecoDTO();
					enderecoDTO.setIdEndereco(Integer.parseInt(idEndereco));
					enderecoDTO.setBairro(bairro);
					enderecoDTO.setCep(Integer.parseInt(cep));
					enderecoDTO.setCidade(cidade);
					enderecoDTO.setLogradouro(logradouro);
					enderecoDTO.setNumero(Long.parseLong(numero));
					
					UfDTO ufDTO = new UfDTO();
					ufDTO.setIdUf(Integer.parseInt(uf));
					enderecoDTO.setUfDTO(ufDTO);
					profissionalDTO.setEnderecoDTO(enderecoDTO);
					profissionalDTO.setNome(nome);
					profissionalDTO.setSexo(sexo.charAt(0));
					profissionalDTO.setEmail(email);
					profissionalDTO.setSenha(senha);
					profissionalDTO.setCelular(celular);
					
					EspecialidadeDTO especialidadeDTO = new EspecialidadeDTO();
					especialidadeDTO.setIdEspecialidade(Integer.parseInt(especialidade));
					profissionalDTO.setEspecialidadeDTO(especialidadeDTO);
					
					pessoaBO.atualizar(profissionalDTO);
					proxPage = "profissional?acao=listar";
				} else if (acao.equals("remover")) {
					String idProfissional =  request.getParameter("idProfissional");
					String idEndereco =  request.getParameter("idEndereco");
					pessoaBO.removePessoa(Integer.parseInt(idProfissional), Integer.parseInt(idEndereco));
					request.setAttribute("msg", "Profissional removido com sucesso!");
					proxPage = "profissional?acao=listar"; 
				}
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}
		request.getRequestDispatcher(proxPage).forward(request, response);
	}

}
