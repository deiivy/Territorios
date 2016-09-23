var app = angular.module('territorios', ['ngRoute', 'ngResource', 'angularUtils.directives.dirPagination']);

app.factory('DataService', function ($http, $log) {

    return {

    	getPaises: function ( callback ) {
    		$http.get('api/Pais').success( callback );
        },
        
        getDepartamentos: function ( callback ) {
    		$http.get('api/Departamento').success( callback );
        },
        
    	getCiudades: function ( callback ) {
    		$http.get('api/Ciudad').success( callback );
        },
        
        eliminarPais: function ( paisEliminar, callback ) {
        	$http.delete('api/Pais/' + paisEliminar).success( callback );
        },

        addPais: function ( pais, callback ) {
        	$http.post('api/Pais', pais).success( callback );
        },

        addDepartamento: function ( departamento, callback ) {
        	$http.post('api/Departamento', departamento).success( callback );
        },
        
        addCiudad: function ( ciudad, callback ) {
        	$http.post('api/Ciudad', ciudad).success( callback );
        },
        
        actualizarPais: function ( pais, callback ) {
        	$http.put('api/Pais/'+ pais.id_pais, pais).success( callback );
        }
    };

});

app.controller('TerritoriosController', [ '$scope', '$http', '$q', 'DataService', function($scope, $http, $q, DataService) {

	$scope.paises = "";
	$scope.departamentos = "";
	$scope.ciudades = "";
	$scope.mensaje = "";
	$scope.error = false;
	$scope.ok = false;
	
	$scope.pais = {
            id_pais: "",
            nombre: "",
            cantidad_departamentos: ""
        };
	
	$scope.departamento = {
            id_departamento: "",
            nombre: "",
            id_pais: "",
            cantidad_ciudades: "",
            pais: "",
        };
	
	$scope.ciudad = {
            id_ciudad: "",
            nombre: "",
            id_departamento: "",
            departamento: "",
        };
	
    $scope.reloadPaises = function() {
	    DataService.getPaises(function( results ){
	        $scope.paises =  results;
	    });
    }
    
    $scope.reloadDepartamentos = function() {
	    DataService.getDepartamentos(function( results ){
	        $scope.departamentos =  results;
	    });
    }
    
    $scope.reloadCiudades = function() {
	    DataService.getCiudades(function( results ){
	        $scope.ciudades =  results;
	    });
    }
    
    $scope.setSelected = function(id_pais) {
    	$scope.paisSelected = id_pais;
    }
    
    $scope.eliminar = function(id_pais) {
        DataService.eliminarPais(id_pais, function( results ){
        	$scope.success(results.mensaje);
        	$scope.reloadPaises();
        });
    }

    $scope.actualizar = function(pais) {
    	$scope.pais = pais;
    }
    
    $scope.reset = function(){
    	$scope.pais = {};
    	$scope.departamento = {};
    	$scope.departamento.pais = {};
    	$scope.ciudad = {};
    }
    
    $scope.success = function(mensaje){
    	$scope.ok = true;
    	$scope.error = false;
    	$scope.mensaje = mensaje;
    }
    
    $scope.hide = function(){
    	$scope.ok = false;
    	$scope.error = false;
    	$scope.mensaje = "";
    }
    
    $scope.reloadPaises();
    $scope.reloadDepartamentos();
    $scope.reloadCiudades();
    
}])

app.controller('PaisesController', [ '$scope', '$http', '$q', 'DataService', function($scope, $http, $q, DataService) {
	
	$scope.guardar = function() {
	    if ($scope.form.$valid) {
	    	if($scope.pais.id_pais != null){
		    	DataService.actualizarPais($scope.pais, function( results ){
		    		$scope.success(results.mensaje);
		    		$scope.reloadPaises();
		    	});
	    	}else{
		    	DataService.addPais($scope.pais, function( results ){
		    		$scope.success(results.mensaje);
		    		$scope.reloadPaises();
		    	});
	    	}
	    	$('#nuevoPais').modal('hide');
	    } else {
	        alert("Hay datos inválidos");
	    }
	};
			
}])

app.controller('DepartamentosController', [ '$scope', '$http', '$q', 'DataService', function($scope, $http, $q, DataService) {
	
	$scope.guardar = function() {
	    if ($scope.form.$valid) {
	    	DataService.addDepartamento($scope.departamento, function( results ){
	    		$scope.success(results.mensaje);
	    		$scope.reloadPaises();
	    		$scope.reloadDepartamentos();
	        });
	    	$('#nuevoDepartamento').modal('hide');
	    } else {
	        alert("Hay datos inválidos");
	    }
	};
}])

app.controller('CiudadesController', [ '$scope', '$http', '$q', 'DataService', function($scope, $http, $q, DataService) {
	
	$scope.guardar = function() {
	    if ($scope.form.$valid) {
	    	DataService.addCiudad($scope.ciudad, function( results ){
	    		$scope.success(results.mensaje);
	    		$scope.reloadDepartamentos();
	    		$scope.reloadCiudades();
	        });
	    	$('#nuevaCiudad').modal('hide');
	    } else {
	        alert("Hay datos inválidos");
	    }
	};
			
}])

app.controller('DetalleController', [ '$scope', '$http', '$q', function($scope, $http, $q) {
	
			
}])