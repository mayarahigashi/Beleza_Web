package br.com.salao.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.salao.jdbc.bo.ServicoBO;
import br.com.salao.jdbc.dto.ProfissionalDTO;
import br.com.salao.jdbc.dto.ServicoDTO;


@WebServlet("/servico")
public class ServicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String proxPage = "index.jsp";
		try {
			ServicoBO servicoBO = new ServicoBO();
			if (acao.equals("cadastrar")) {
				String nomeServico = request.getParameter("nomeServico");
				String valorServico = request.getParameter("valorServico");
				String tempoExecucao = request.getParameter("tempoExecucao");
				String observacoes = request.getParameter("observacoes");
				String profissional = request.getParameter("profissional");
				
				ServicoDTO servicoDTO = new ServicoDTO();
				servicoDTO.setNomeServico(nomeServico);
				servicoDTO.setValorServico(Double.parseDouble(valorServico));
				servicoDTO.setTempoExecucao(tempoExecucao);
				servicoDTO.setObservacoes(observacoes);
				
				ProfissionalDTO profissionalDTO = new ProfissionalDTO();
				profissionalDTO.setIdPessoa(Integer.parseInt(profissional));
				servicoDTO.setProfissionalDTO(profissionalDTO);
				
				servicoBO.cadastrar(servicoDTO);
				request.setAttribute("msg", "Cadastro efetuado com sucesso!");
				proxPage = "servico?acao=listar";
				} else if (acao.equals("listar")) {
					List<ServicoDTO> lista = servicoBO.listagem();
					request.setAttribute("listaServicos", lista);
					proxPage = "listar-servico.jsp";
				} else if (acao.equals("editar")) {
					String id = request.getParameter("id");
					ServicoDTO servicoDTO = servicoBO.buscaPorId(Integer.parseInt(id));
					request.setAttribute("servicoDTO", servicoDTO);
					proxPage = "editar-servico.jsp";
				} else if (acao.equals("atualizar")) {
					String nomeServico = request.getParameter("nomeServico");
					String valorServico = request.getParameter("valorServico");
					String tempoExecucao = request.getParameter("tempoExecucao");
					String observacoes = request.getParameter("observacoes");
					String profissional = request.getParameter("profissional");
					
					ServicoDTO servicoDTO = new ServicoDTO();
					servicoDTO.setNomeServico(nomeServico);
					servicoDTO.setValorServico(Double.parseDouble(valorServico));
					servicoDTO.setTempoExecucao(tempoExecucao);
					servicoDTO.setObservacoes(observacoes);
					
					ProfissionalDTO profissionalDTO = new ProfissionalDTO();
					profissionalDTO.setIdPessoa(Integer.parseInt(profissional));
					servicoDTO.setProfissionalDTO(profissionalDTO);
					
					servicoBO.atualizar(servicoDTO);
					proxPage = "servico?acao=listar";
				} else if (acao.equals("remover")) {
					String idServico =  request.getParameter("idServico");
					servicoBO.removeServico(Integer.parseInt(idServico));
					request.setAttribute("msg", "Servico removido com sucesso!");
					proxPage = "servico?acao=listar"; 
				}
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
		}
		request.getRequestDispatcher(proxPage).forward(request, response);
	}

}
