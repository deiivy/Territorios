<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html ng-app="territorios">
<head>
	<title><spring:message code="inicio.divisiones.titulo" /></title>
	
	<link rel="shortcut icon" href="<c:url value="/resources/images/fav1.png" />" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="<c:url value="/resources/css/estilos.css" />">
	
	<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular-route.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.8/angular-resource.min.js"></script>
	<script src="<c:url value="/resources/js/script.js" />"></script>
	<script src="<c:url value="/resources/js/dirPagination.js" />"></script>
</head>
<body ng-controller="TerritoriosController">

	<div class="row banner">
		<spring:message code="inicio.divisiones.titulo" />
		<br>
		<a href="?lang=es" class="idiomas">
			<img src="http://www.crackbusinessenglish.com/public/static/images/contenidos/bandera_esp.png">
		</a> 
		
		<a href="?lang=en" class="idiomas">
			<img src="http://glmty.com/images/18ebe1ab-839d-4abc-b8cc-a3f2307641e9.png">
		</a>
	</div>
	<div class="container">
	
		<div class="col-xs-12 col-md-12" ng-show="error">
			<div class="alert alert-danger alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <span class="glyphicon glyphicon-thumbs-down"></span> {{mensaje}}
			</div>
		</div>
		
		<div class="col-xs-12 col-md-12" ng-show="ok">
			<div class="alert alert-success alert-dismissible" role="alert">
			  <button type="button" class="close" ng-click="hide()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <span class="glyphicon glyphicon-thumbs-up"></span> {{mensaje}}
			</div>
		</div>
	
		<div class="col-xs-12 col-md-12">
			<button type="button" class="btn btn-success" ng-click="reset()" data-toggle="modal" data-target="#nuevoPais"><spring:message code="inicio.pais.crear" /></button>
			<button type="button" class="btn btn-success" ng-click="reset()" data-toggle="modal" data-target="#nuevoDepartamento"><spring:message code="inicio.departamento.crear" /></button>
			<button type="button" class="btn btn-success" ng-click="reset()" data-toggle="modal" data-target="#nuevaCiudad"><spring:message code="inicio.ciudad.crear" /></button>
		</div>
		
		<div class="col-xs-12 col-md-4" style="margin: 20 0 20 0;">
			<input class="form-control" type="text" ng-model="busqueda" placeholder="<spring:message code="inicio.pais.buscar" />">
		</div>
		
		<div class="col-xs-12 col-md-12">
			<table class="table table-striped table-bordered" ng-table="tablePaises">
				<thead>
		    		<tr>
		        		<th><spring:message code="inicio.nombre" /></th>
		        		<th><spring:message code="inicio.pais.departamentos" /></th>
		        		<th><spring:message code="inicio.opciones" /></th>
		      		</tr>
		    	</thead>
		    	<tbody>
		    		<tr dir-paginate="pais in paises | filter: busqueda | itemsPerPage:5 ">
					    <td>{{pais.nombre}}</td>
					    <td><span class="badge">{{pais.cantidad_departamentos}}</span></td>
					    <td>
					    	<!-- Split button -->
							<div class="btn-group">
							  <button type="button" class="btn btn-sm btn-success"><spring:message code="inicio.accion" /></button>
							  <button type="button" style="height: 30px;" class="btn btn-sm btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							    <span class="caret"></span>
							    <span class="sr-only">Toggle Dropdown</span>
							  </button>
							  <ul class="dropdown-menu">
							    <li><a data-toggle="modal" data-target="#detalle" ng-click="setSelected(pais.id_pais)"><spring:message code="inicio.detalle" /></a></li>
							    <li role="separator" class="divider"></li>
							    <li><a href="#" ng-click="actualizar(pais)" data-toggle="modal" data-target="#nuevoPais"><spring:message code="inicio.editar" /></a></li>
							    <li><a href="#" ng-click="eliminar(pais.id_pais)"><spring:message code="inicio.eliminar" /></a></li>
							  </ul>
							</div>
					    </td>
					</tr>
		    	</tbody>
			</table>
		</div>
		
		<div class="col-xs-12 col-md-12">
			<center>
				<dir-pagination-controls
					max-size="5"
					direction-links="true"
					boundary-links="true" >
				</dir-pagination-controls>
			</center>
		</div>
		
		<!-- Modal para nuevo país -->
		<div class="modal fade bs-example-modal-sm" id="nuevoPais" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog modal-sm" role="document">
		    <div class="modal-content" ng-controller="PaisesController">
		      <form class="form-horizontal" name="form" novalidate>
			      <div class="modal-header" style="background-color: #56b056;">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel"><spring:message code="inicio.pais.crear" /></h4>
			      </div>
			      <div class="modal-body">
				  	<div class="form-group">
						<div class="col-sm-12 col-md-12">
						  <input type="hidden" ng-model="pais.id_pais" >
					      <input type="text" class="form-control" ng-model="pais.nombre" required="true" placeholder="<spring:message code="inicio.nombre" />">
					    </div>
					</div>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="inicio.cancelar" /></button>
			        <input type="submit" class="btn btn-success" ng-click="guardar()" ng-disabled="form.$invalid" value="<spring:message code="inicio.guardar" />" />
			      </div>
		      </form>
		    </div>
		  </div>
		</div>
		
		<!-- Modal para nuevo departamento -->
		<div class="modal fade bs-example-modal-sm" id="nuevoDepartamento" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog modal-sm" role="document">
		    <div class="modal-content" ng-controller="DepartamentosController">
		      <form class="form-horizontal" name="form" novalidate>
			      <div class="modal-header" style="background-color: #56b056;">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel"><spring:message code="inicio.departamento.crear" /></h4>
			      </div>
			      <div class="modal-body">
					  <div class="form-group">
					    <div class="col-sm-12 col-md-12">
					    	<select class="form-control" ng-model="departamento.pais" ng-options="pais.nombre for pais in paises | orderBy:'nombre'">
							  <option value=""><spring:message code="inicio.pais.titulo" /></option>
							</select>
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-12 col-md-12">
					      <input type="text" class="form-control" ng-disabled="!departamento.pais" required ng-model="departamento.nombre" placeholder="<spring:message code="inicio.nombre" />">
					    </div>
					  </div>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="inicio.cancelar" /></button>
			        <input type="submit" class="btn btn-success" ng-click="guardar()" ng-disabled="form.$invalid" value="<spring:message code="inicio.guardar" />" />
			      </div>
		      </form>
		    </div>
		  </div>
		</div>
		
		<!-- Modal para nueva ciudad -->
		<div class="modal fade bs-example-modal-sm" id="nuevaCiudad" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog modal-sm" role="document">
		    <div class="modal-content" ng-controller="CiudadesController">
		     <form class="form-horizontal" name="form" novalidate>
		      <div class="modal-header" style="background-color: #56b056;">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel"><spring:message code="inicio.ciudad.crear" /></h4>
		      </div>
		      <div class="modal-body">
				  <div class="form-group">
				    <div class="col-sm-12 col-md-12">
				    	<select class="form-control" ng-model="departamento.pais" ng-options="pais.nombre for pais in paises | orderBy:'nombre'">
						  <option value=""><spring:message code="inicio.pais.titulo" /></option>
						</select>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-12 col-md-12">
				    	<select class="form-control" ng-model="ciudad.departamento" ng-disabled="!departamento.pais" ng-options="departamento.nombre for departamento in departamentos 
				    			| orderBy:'nombre' | filter: {id_pais : departamento.pais.id_pais}">
						  <option value=""><spring:message code="inicio.departamento.titulo" /></option>
						</select>
				    </div>
				  </div>
				  {{ciudad.id_pais}}
				  <div class="form-group">
				    <div class="col-sm-12 col-md-12">
				      <input type="text" class="form-control" ng-model="ciudad.nombre" ng-disabled="!ciudad.departamento" required placeholder="<spring:message code="inicio.nombre" />">
				    </div>
				  </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="inicio.cancelar" /></button>
			    <input type="submit" class="btn btn-success" ng-click="guardar()" ng-disabled="form.$invalid" value="<spring:message code="inicio.guardar" />" />
		      </div>
		     </form>
		    </div>
		  </div>
		</div>
		
		<!-- Modal para detalle de país -->
		<div class="modal fade bs-example-modal-lg" id="detalle" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content" ng-controller="DetalleController">
		      <div class="modal-header" style="background-color: #56b056;">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel"><spring:message code="inicio.detalle" /></h4>
		      </div>
		      <div class="modal-body">
		      	<div class="row" >
		      		<div class="col-xs-12 col-md-4" style="margin: 20 0 20 0;">
		      			<input class="form-control" type="text" ng-model="busquedaDepartamento" placeholder="<spring:message code="inicio.departamento.buscar" />">
		      		</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-md-12">
				        <div class="panel-group" role="tablist" > 
					        <div class="panel panel-default" ng-repeat="departamento in departamentos | filter: {id_pais : paisSelected} | filter: busquedaDepartamento">
						        <div class="panel-heading" role="tab" id="collapseListGroupHeading{{$index}}"> 
							        <div class="row">
								        <div class="col-xs-12 col-md-6">
									        <h4 class="panel-title"> 
										        <a href="#collapseListGroup{{$index}}" class="" role="button" data-toggle="collapse" aria-expanded="true" aria-controls="collapseListGroup{{$index}}"> 
										        	{{departamento.nombre}} 
										        </a> 
									        </h4> 
								        </div>
								        <div class="col-xs-12 col-md-6 cantidad-ciudades">
								        	<span class="badge">{{departamento.cantidad_ciudades}}</span>
								        </div>
							        </div>
						        </div> 
						        <div class="panel-collapse collapse" role="tabpanel" id="collapseListGroup{{$index}}" aria-labelledby="collapseListGroupHeading{{$index}}" aria-expanded="true">
							        <ul class="list-group"> 
								        <li class="list-group-item" ng-repeat="ciudad in ciudades | filter: {id_departamento : departamento.id_departamento}">{{ciudad.nombre}}</li>
							        </ul> 
						        </div> 
					        </div> 
				        </div>
			        </div>
		        </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="inicio.cancelar" /></button>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
</body>
</html>
