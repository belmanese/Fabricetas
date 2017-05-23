app.controller("categoriaCtrl", ["$scope", "$routeParams", "servicioCategoria", "servicioHome","fabConstans", function($scope, $routeParams, servicioCategoria, servicioHome,fabConstans){
	init();
	  function init(){
			$scope.list=fabConstans.list;
			$scope.gallery = fabConstans.gallery;
			$scope.advanceShearch = fabConstans.advanceShearch;
			$scope.galeria = angular.copy($scope.gallery);
			$scope.lista = angular.copy(!$scope.galeria);

	    if(typeof $routeParams.id != 'undefined'){

				$scope.titulo=$routeParams.id;
	      let id =$routeParams.id
				servicioCategoria.traerEstampasCategoria(id).query().$promise.then((datos)=>{
					$scope.estampasTema = datos;
				});
	    }
	    else{
				$scope.titulo=0;
				//$scope.titulo="Estampas organizadas según su tema";
				$scope.estampasTema = servicioHome.traerEstampas().query();
	    }
			$scope.temas = servicioCategoria.traerCategorias().query();
	  }
}]);
