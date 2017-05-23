app.controller("homeCtrl",["$scope","servicioHome", "servicioCategoria","servicioCookies","fabConstans","$rootScope",
function($scope, servicioHome, servicioCategoria,servicioCookies,fabConstans,$rootScope){
	init();

	function init(){
		/* Variabilidad  */
		$scope.list=fabConstans.list;
		$scope.gallery = fabConstans.gallery;
		$scope.advanceShearch = fabConstans.advanceShearch;

		$scope.galeria = angular.copy($scope.gallery);
		$scope.lista = angular.copy(!$scope.galeria);



		$scope.populares= servicioHome.traerEstampas().query();
		$scope.temas= servicioCategoria.traerCategorias().query();

		/*
		ejemplo de put
		var data = {
		 'id': '5',
		 "title":"camisaActualizada"
		};
		servicioHome.actualizarAlgo().update(data);*/
	}
}]);
