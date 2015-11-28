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

import br.com.salao.jdbc.bo.ClienteBO;
import br.com.salao.jdbc.dto.ClienteDTO;
import br.com.salao.jdbc.dto.EnderecoDTO;
import br.com.salao.jdbc.dto.UfDTO;


@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String proxPage = "index.jsp";
		try {
			ClienteBO clienteBO = new ClienteBO();
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
				String celular = request.getParameter("telefone");
				String uf = request.getParameter("uf");
				
				clienteBO.validaNome(nome);
				clienteBO.validaCpf(cpf);
				clienteBO.validaDtNasc(dtNasc);
				
				ClienteDTO clienteDTO = new ClienteDTO();
				clienteDTO.setCpf(Long.parseLong(cpf));
				clienteDTO.setDtNascimento(dateFormat.parse(dtNasc));
				
				EnderecoDTO enderecoDTO = new EnderecoDTO();
				enderecoDTO.setBairro(bairro);
				enderecoDTO.setCep(Integer.parseInt(cep));
				enderecoDTO.setCidade(cidade);
				enderecoDTO.setLogradouro(logradouro);
				enderecoDTO.setNumero(Long.parseLong(numero));
				
				UfDTO ufDTO = new UfDTO();
				ufDTO.setIdUf(Integer.parseInt(uf));
				enderecoDTO.setUfDTO(ufDTO);
				clienteDTO.setEnderecoDTO(enderecoDTO);
				clienteDTO.setNomeCliente(nome);
				clienteDTO.setSexo(sexo.charAt(0));
				clienteDTO.setEmail(email);
				clienteDTO.setCelular(celular);
				
				clienteBO.cadastrar(clienteDTO);
				request.setAttribute("msg", "Cadastro efetuado com sucesso!");
				proxPage = "cliente?acao=listar";
				} else if (acao.equals("listar")) {
					List<ClienteDTO> lista = clienteBO.listagem();
					request.setAttribute("listaClientes", lista);
					proxPage = "listar-cliente.jsp";
				} else if (acao.equals("editar")) {
					String id = request.getParameter("id");
					ClienteDTO clienteDTO = clienteBO.buscaPorId(Integer.parseInt(id));
					request.setAttribute("clienteDTO", clienteDTO);
					proxPage = "editar-cliente.jsp";
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
					String celular = request.getParameter("telefone");
					String uf = request.getParameter("uf");
					
					ClienteDTO clienteDTO = new ClienteDTO();
					clienteDTO.setIdCliente(Integer.parseInt(idProfissional));
					clienteDTO.setCpf(Long.parseLong(cpf));
					clienteDTO.setDtNascimento(dateFormat.parse(dtNasc));
					
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
					clienteDTO.setEnderecoDTO(enderecoDTO);
					clienteDTO.setNomeCliente(nome);
					clienteDTO.setSexo(sexo.charAt(0));
					clienteDTO.setEmail(email);
					clienteDTO.setCelular(celular);
					
					clienteBO.atualizar(clienteDTO);
					proxPage = "cliente?acao=listar";
				} else if (acao.equals("remover")) {
					String idCliente =  request.getParameter("idCliente");
					String idEndereco =  request.getParameter("idEndereco");
					clienteBO.removePessoa(Integer.parseInt(idCliente), Integer.parseInt(idEndereco));
					request.setAttribute("msg", "Cliente removido com sucesso!");
					proxPage = "cliente?acao=listar"; 
				}
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}
		request.getRequestDispatcher(proxPage).forward(request, response);
	}

}
