app.service("servicioHome",["fabConstans","$http","$q","$resource",function(fabConstans,$http,$q,$resource){
	var vm= this;
	var dirServ = fabConstans.URL_BASE_SERVICIOS;

	vm.traerEstampas = function (){
		return $resource(dirServ + "stamp/home");
	}
	vm.traerEstampasPorId = function (id){
		console.log("por aca paso2");
		return $resource(dirServ + "stamp/:id",{id:id});
	}
	vm.traerCamisas = function (){
		return $resource(dirServ + "camiseta");
	}
	vm.traerCamisasPorId = function (id){
		return $resource(dirServ + "camiseta/:id",{id:id});
	}

}]);
