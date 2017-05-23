app.service("servicioRating",["fabConstans","$http","$q","$resource",function(fabConstans,$http,$q,$resource){
	var vm= this;
  var dirServ = fabConstans.URL_BASE_SERVICIOS;

  vm.traerRatings = function (){
		return $resource(dirServ + "rating");
	}
	vm.calificarEstampa = function (){
		return $resource(dirServ + "rating/stamp");
	}
}]);
