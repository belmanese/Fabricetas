app.service("servicioPerfil",["fabConstans","$http","$q","$resource",function(fabConstans,$http,$q,$resource){
	var vm= this;
	var dirServ = fabConstans.URL_BASE_SERVICIOS;

  vm.traerPerfil = function(userId){
    return $resource(dirServ+"user/:id/?fetch=address",{id:userId});
  }

  vm.actualizarDireccion = function (){
    return $resource(dirServ+"address", null, {update : {method : "PUT"}});
  }

  vm.crearDireccion = function (){
    return $resource(dirServ+"address");
  }

}]);
