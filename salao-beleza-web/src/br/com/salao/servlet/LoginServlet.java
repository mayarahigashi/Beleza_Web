package br.com.salao.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.salao.jdbc.bo.EspecialidadeBO;
import br.com.salao.jdbc.bo.LoginBO;
import br.com.salao.jdbc.bo.PessoaBO;
import br.com.salao.jdbc.bo.ServicoBO;
import br.com.salao.jdbc.bo.UfBO;
import br.com.salao.jdbc.dto.ClienteDTO;
import br.com.salao.jdbc.dto.EspecialidadeDTO;
import br.com.salao.jdbc.dto.LoginDTO;
import br.com.salao.jdbc.dto.ProfissionalDTO;
import br.com.salao.jdbc.dto.ServicoDTO;
import br.com.salao.jdbc.dto.UfDTO;
import br.com.salao.jdbc.exception.NegocioException;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		LoginBO loginBO = new LoginBO();
		String login = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		HttpSession sessao = request.getSession();
		
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setNome(login);
		loginDTO.setSenha(senha);
		
		String proxPage = "index.jsp";
		try {
			boolean resposta = loginBO.logar(loginDTO);
			if (!resposta) {
				request.setAttribute("msg", "Usuário/Senha inválidos!");
				proxPage = "login.jsp";
			} else {
				UfBO ufBO = new UfBO();
				List<UfDTO> lista = ufBO.listaUfs();
				sessao.setAttribute("listaUfs", lista);
				
				EspecialidadeBO especialidadeBO = new EspecialidadeBO();
				List<EspecialidadeDTO> listaEsp = especialidadeBO.listaEspecialidades();
				sessao.setAttribute("lista", listaEsp);
				
				PessoaBO pessoaBO = new PessoaBO();
				List<ProfissionalDTO> listaProfissional = pessoaBO.listagem();
				sessao.setAttribute("listaPro", listaProfissional);
			} 
		} catch (NegocioException e) {
			proxPage = "login.jsp";
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(proxPage);
		dispatcher.forward(request, response);
	}

}
