app.controller("navegarCtrl",["$scope","$routeParams","servicioHome",function($scope,$routeParams,servicioHome){

  init();
  if (typeof $routeParams.opcion!='undefined')
  {
    $scope.orden = $routeParams.opcion;
  }
  else
  {
      $scope.orden="nombre";
  }
  $scope.filtrarPor = function (){
    return -$scope.orden;
  }
	function init(){
    $scope.populares = servicioHome.traerEstampas().query();
	}

}]);
