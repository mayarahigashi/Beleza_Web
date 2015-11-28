<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="br.com.salao.jdbc.dto.ServicoDTO"%>
<%@page import="br.com.salao.jdbc.dto.ProfissionalDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="msapplication-tap-highlight" content="no">
<meta name="description"
	content="Materialize is a Material Design Admin Template,It's modern, responsive and based on Material Design by Google. ">
<meta name="keywords"
	content="materialize, admin template, dashboard template, flat admin template, responsive admin template,">
<title>Editar Serviço</title>

<!-- Favicons-->
<link rel="icon" href="images/favicon/logo-salao_32_32.jpg" sizes="32x32">
	<!-- Favicons-->
<link rel="apple-touch-icon-precomposed"
	href="images/favicon/apple-touch-icon-152x152.png">
	<!-- For iPhone -->
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="images/favicon/mstile-144x144.png">
	<!-- For Windows Phone -->


	<!-- CORE CSS-->
<link href="css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection">
<link href="css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection">
	<!-- CSS for full screen (Layout-2)-->
<link href="css/style-fullscreen.css" type="text/css" rel="stylesheet"
	media="screen,projection">
	<!-- Custome CSS-->
<link href="css/custom-style.css" type="text/css" rel="stylesheet"
	media="screen,projection">
	<!-- CSS for full screen (Layout-2)-->
<link href="css/style-fullscreen.css" type="text/css" rel="stylesheet"
	media="screen,projection">


	<!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
<link href="js/plugins/perfect-scrollbar/perfect-scrollbar.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="js/plugins/jvectormap/jquery-jvectormap.css" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="js/plugins/chartist-js/chartist.min.css" type="text/css"
	rel="stylesheet" media="screen,projection">
</head>

<body>
	<!-- Start Page Loading -->
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<!-- End Page Loading -->

	<!-- //////////////////////////////////////////////////////////////////////////// -->

	<!-- START HEADER -->
	<header id="header" class="page-topbar">

		<!-- start header nav-->
		<div class="navbar-fixed">
			<nav class="pink darken-3">
				<div class="nav-wrapper">
					<ul class="left">
						<li class="no-hover"><a href="#" data-activates="slide-out"
							class="menu-sidebar-collapse btn-floating btn-flat btn-medium waves-effect waves-light pink darken-3"><i
								class="mdi-navigation-menu"></i></a></li>
					</ul>
					<div class="header-search-wrapper hide-on-med-and-down">
						<i class="mdi-action-search"></i> <input type="text" name="Search"
							class="header-search-input z-depth-2" placeholder="Buscar" />
					</div>
					<ul class="right hide-on-med-and-down">
						<li><a href="javascript:void(0);"
							class="waves-effect waves-block waves-light toggle-fullscreen"><i
								class="mdi-action-settings-overscan"></i></a></li>
						<li><a href="javascript:void(0);"
							class="waves-effect waves-block waves-light"><i
								class="mdi-social-notifications"></i></a></li>

					</ul>
				</div>
			</nav>
		</div>
		<!-- end header nav-->

	</header>
	<!-- END HEADER -->

	<!-- //////////////////////////////////////////////////////////////////////////// -->

	<!-- START MAIN -->
	<div id="main">
		<!-- START WRAPPER -->
		<div class="wrapper">

			<!-- START LEFT SIDEBAR NAV-->
            <aside id="left-sidebar-nav">
                <ul id="slide-out" class="side-nav leftside-navigation">
                    <li class=" pink darken-3">
                        <div class="row">
                            <div class="col col s4 m4 l4">
                                <img src="images/perfil.jpg" alt="" class="circle responsive-img valign profile-image">
                            </div>
                            <div class="col col s8 m8 l8">
                                <p class="user-roal">Administrator</p>
                            </div>
                        </div>
                    </li>
                    <li class="bold"><a href="index.jsp" class="waves-effect waves-cyan"><i class="mdi-action-dashboard"></i> Dashboard</a>
            		</li>
                    <li class="bold"><a href="agenda.jsp" class="waves-effect waves-cyan"><i class="mdi-editor-insert-invitation"></i> Agenda</a>
                    </li>
                    <li class="no-padding">
                        <ul class="collapsible collapsible-accordion">
                            <li class="bold"><a class="collapsible-header waves-effect waves-cyan"><i class="mdi-image-timer-auto"></i> Clientes</a>
                                <div class="collapsible-body">
                                    <ul>
                                        <li><a href="cliente?acao=listar">Lista de Clientes</a>
                                        </li>                                        
                                        <li><a href="cadastrar-cliente.jsp">Cadastrar Cliente</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="bold"><a class="collapsible-header waves-effect waves-cyan"><i class="mdi-social-group"></i> Profissionais</a>
                                <div class="collapsible-body">
                                    <ul>
                                        <li><a href="profissional?acao=listar">Lista de Profissionais</a>
                                        </li>                                        
                                        <li><a href="cadastrar-profissional.jsp">Cadastrar Profissional</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="bold"><a class="collapsible-header waves-effect waves-cyan"><i class="mdi-action-view-headline"></i> Serviços</a>
                                <div class="collapsible-body">
                                    <ul>
                                        <li><a href="servico?acao=listar">Lista de Serviços</a>
                                        </li>                                        
                                        <li><a href="cadastrar-servico.jsp">Cadastrar Serviço</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            
                            <li class="divider"></li>
                            
                            <li><a href="login.jsp"><i class="mdi-hardware-keyboard-tab"></i> Sair</a></li>
                            
                        </ul>
                    </li>
                </ul>
                
            </aside>
            <!-- END LEFT SIDEBAR NAV-->

			<!-- START CONTENT -->
			<section id="content">

				<!--breadcrumbs start-->
				<div id="breadcrumbs-wrapper" class=" grey lighten-3">
					<!-- Search for small screen -->
					<div class="header-search-wrapper grey hide-on-large-only">
						<i class="mdi-action-search active"></i> <input type="text"
							name="Search" class="header-search-input z-depth-2"
							placeholder="Explore Materialize">
					</div>
					<div class="container">
						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title">EDIÇÃO DE SERVIÇO</h5>
								<ol class="breadcrumb">
									<li><a href="index.jsp">Dashboard</a></li>
									<li class="active">Editar Serviço</li>
								</ol>
							</div>
						</div>
					</div>
				</div>
				<!--breadcrumbs end-->

				<!--start container-->
				<div class="container">
				<br/>
					<!--Form Advance-->
					<div class="row">
						<div class="col s12 m12 l12">
							<div class="card-panel">
								<div class="row">
								<%
									String msg = (String) request.getAttribute("msg");
									ServicoDTO servicoDTO = (ServicoDTO) request.getAttribute("servicoDTO");
								%>
								<%= msg != null ? msg : "" %>
								
									<form class="col s12" action="servico?acao=atualizar" method="post">
										<input type="hidden" name="id" value="<%= servicoDTO.getIdServico() %>"/>
										<div class="row">
											<div class="input-field col s3">
												<input id="nomeServico" name="nomeServico" type="text" value="<%= servicoDTO.getNomeServico() %>" />
												<label for="nomeServico">SERVIÇO</label>
											</div>
											<div class="input-field col s3">
												<input id="valorServico" name="valorServico" type="text" value="<%= servicoDTO.getValorServico() %>" />
												<label for="valorServico">VALOR DO SERVIÇO</label>
											</div>
											<div class="input-field col s2">
												<input id="tempoExecucao" name="tempoExecucao" type="text" value="<%= servicoDTO.getTempoExecucao() %>" />
												<label for="tempoExecucao">TEMPO DO SERVIÇO</label>
											</div>
											<div class="input-field col s1">
												<label>PROFISSIONAL:</label>
											</div>
											<div class="input-field col s3">
												<select id="profissional" name="profissional" class="browser-default">
													<%
														List<ProfissionalDTO> listaPro = (List<ProfissionalDTO>) session.getAttribute("listaPro");
																for (ProfissionalDTO profissionalDTO : listaPro) {
																	int idProfissional = servicoDTO.getProfissionalDTO().getIdPessoa();
																	if (profissionalDTO.getIdPessoa() == idProfissional) {
													%>
														<option value="<%= profissionalDTO.getIdPessoa() %>" selected="selected">
															<%= profissionalDTO.getNome() %>
														</option>
													<%
														} else {
													%>
														<option value="<%= profissionalDTO.getIdPessoa() %>">
															<%= profissionalDTO.getNome() %>
														</option>
													<%
														}
													}
													%>
												</select>
											</div>
										</div>
										<div class="row">
											<div class="input-field col s6">
												<textarea id="observacoes" name="observacoes" class="materialize-textarea" length="120">
													<%= servicoDTO.getObservacoes() %>
												</textarea>
                        						<label for="observacoes">OBSERVAÇÕES</label>
											</div>
										</div>
										<div class="row">
											<div class="input-field col s12">
												<button
													class="btn pink darken-3 waves-effect waves-light right"
													type="submit" name="action">
													Salvar <i class="mdi-content-send right"></i>
												</button>
											</div>
										</div>
					
									</form>
							</div>
						</div>
					</div>
				</div>
		</div>

	<!--end container-->
	</section>
	<!-- END CONTENT -->

	</div>
	<!-- END WRAPPER -->

	</div>
	<!-- END MAIN -->
	<!-- //////////////////////////////////////////////////////////////////////////// -->

	<!-- START FOOTER -->
	<footer class="page-footer pink darken-3">
		<div class="footer-copyright">
			<div class="container">
				<span class="right"> Desenvolvido por <a
					class="grey-text text-lighten-4" href="http://geekslabs.com/">Salão
						de Beleza</a></span>
			</div>
		</div>
	</footer>
	<!-- END FOOTER -->


	<!-- ================================================
    Scripts
    ================================================ -->

	<!-- jQuery Library -->
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<!--materialize js-->
	<script type="text/javascript" src="js/materialize.js"></script>
	<!--scrollbar-->
	<script type="text/javascript"
		src="js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>


	<!-- chartist -->
	<script type="text/javascript"
		src="js/plugins/chartist-js/chartist.min.js"></script>

	<!-- chartjs -->
	<script type="text/javascript" src="js/plugins/chartjs/chart.min.js"></script>
	<script type="text/javascript" src="js/plugins/chartjs/chart-script.js"></script>

	<!-- sparkline -->
	<script type="text/javascript"
		src="js/plugins/sparkline/jquery.sparkline.min.js"></script>
	<script type="text/javascript"
		src="js/plugins/sparkline/sparkline-script.js"></script>

	<!--jvectormap-->
	<script type="text/javascript"
		src="js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script type="text/javascript"
		src="js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script type="text/javascript"
		src="js/plugins/jvectormap/vectormap-script.js"></script>


	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript" src="js/plugins.js"></script>

</body>

</html>