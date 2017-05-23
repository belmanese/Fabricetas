app.service("servicioAdminUsuarios",["fabConstans","$http","$q","$resource",function(fabConstans,$http,$q,$resource){
	var vm= this;
	var dirServ = fabConstans.URL_BASE_SERVICIOS;

  vm.traerUsuarios = function (){
    return $resource(dirServ+"user");
  }

  vm.actualizarUsuario = function (userId){
    return $resource(dirServ+"user", null, {update : {method : "PUT"}});
  }

  vm.crearUsuario = function (){
    return $resource(dirServ+"user");
  }

  vm.eliminarUsuario = function (userId){
    return $resource(dirServ+"user/:id",{id:userId})
  }
}]);
