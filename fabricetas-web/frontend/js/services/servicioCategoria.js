app.service("servicioCategoria",["fabConstans","$http","$q","$resource",function(fabConstans,$http,$q,$resource){
	var vm= this;
	var dirServ = fabConstans.URL_BASE_SERVICIOS;

  vm.traerEstampasCategoria = function (tema){
		return $resource(dirServ + "stamp/home/?themeId=:tema",{tema:tema});
	}

	vm.traerCategorias = function (){
		return $resource(dirServ + "theme");
	}

	vm.actualizarCategoria = function (temaId){
		return $resource(dirServ + "theme",null, {update : {method : "PUT"}});
	}
	vm.crearCategoria = function (){
		return $resource(dirServ + "theme");
	}
}]);
