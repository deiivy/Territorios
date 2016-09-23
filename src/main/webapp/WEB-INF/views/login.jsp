<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
	<head>
		<link rel="shortcut icon" href="<c:url value="/resources/images/fav1.png" />" >
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="<c:url value="/resources/css/estilos.css" />">
		
		<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	<body style="text-align: center; background-color: #5cb85c">
		<div class="row">
			<div class="col-xs-12 col-md-3">
			</div>
			<div class="col-xs-12 col-md-6" style="background-color: rgba(10, 10, 10, 0.58); padding: 80px; margin-top: 50px;">
				<h1 style="color: white; margin: -10 20 5 0;"><spring:message code="login.titulo" /></h1>
				<br>
				<a href="?lang=es" class="idiomas">
					<img src="http://www.crackbusinessenglish.com/public/static/images/contenidos/bandera_esp.png">
				</a> 
				<a href="?lang=en" class="idiomas">
					<img src="http://glmty.com/images/18ebe1ab-839d-4abc-b8cc-a3f2307641e9.png">
				</a>
				<form style="margin-top: 40px;" action="login" method="POST" modelAttribute="Usuario">
				  <div class="form-group">
				    <input type="text" class="form-control" id="usuario" name="usuario" required placeholder="<spring:message code="login.usuario" />">
				  </div>
				  <div class="form-group">
				    <input type="password" class="form-control" id="clave" name="clave" required placeholder="<spring:message code="login.clave" />">
				  </div>
				  <button type="submit" class="btn btn-success"><spring:message code="login.ingresar" /></button>
				</form>
			</div>
			<div class="col-xs-12 col-md-3">
			</div>
		</div>
	</body>
</html>