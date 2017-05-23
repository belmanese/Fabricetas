app.service("servicioFactura",["fabConstans","$http","$q","$resource",function(fabConstans,$http,$q,$resource){
	var vm= this;
  var dirServ = fabConstans.URL_BASE_SERVICIOS;

  vm.guardarFactura = function (){
		return $resource(dirServ + "invoice");
	}

}]);
