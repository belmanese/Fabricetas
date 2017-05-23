app.service("servicioCamiseta",["fabConstans","$http","$q","$resource",function(fabConstans,$http,$q,$resource){
	var vm= this;
	var dirServ = fabConstans.URL_BASE_SERVICIOS;

	vm.traerCamisas = function (){
		return $resource(dirServ + "tshirt");
		//return $resource("http://52.88.20.109:3000/api/camisas");
	}
	vm.traerCamisasPorId = function (id){
		return $resource(dirServ + "tshirt/:id",{id:id});
		//return $resource("http://52.88.20.109:3000/api/camisas/:id",{id:id});
	}
	vm.actualizarCamiseta = function (camisetaId){
		return $resource(dirServ + "tshirt", null, {update : {method : "PUT"}});
	}
	vm.crearCamiseta = function (){
		return $resource(dirServ + "tshirt");
		//return $resource("http://localhost:8080/fabricetas/tema/");
	}

}]);
