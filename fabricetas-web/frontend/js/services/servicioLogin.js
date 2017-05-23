app.service("servicioLogin",["fabConstans","$http","$q","$resource",function(fabConstans,$http,$q,$resource){
	var vm= this;
	var dirServ = fabConstans.URL_BASE_SERVICIOS;

	vm.crearUsuario = function (){
    return $resource(dirServ+"user");
  }

	vm.validarUsuario = function (){
		console.log("por aca paso");
		return $resource(dirServ + "user/usuario/autenticar");
	}
}]);
