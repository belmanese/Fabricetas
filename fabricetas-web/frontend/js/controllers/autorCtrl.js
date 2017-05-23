app.controller("autorCtrl",["$scope","$routeParams","servicioAutores","servicioHome","fabConstans",
function($scope,$routeParams,servicioAutores,servicioHome,fabConstans){
  init();

  function init(){

    /* Variabilidad  */
    $scope.list=fabConstans.list;
		$scope.gallery = fabConstans.gallery;
		$scope.advanceShearch = fabConstans.advanceShearch;
		$scope.galeria = angular.copy($scope.gallery);
		$scope.lista = angular.copy(!$scope.galeria);

    if(typeof $routeParams.autor!='undefined')
    {
      $scope.autor=$routeParams.autor;
      let autor =$routeParams.autor;
      servicioAutores.traerEstampasAutor(autor).query().$promise.then((datos)=>{
          $scope.estampasAutor = datos;
      })
    }
    else
    {
      $scope.autor=0;
      $scope.estampasAutor = servicioHome.traerEstampas().query();
    }
    $scope.autores = servicioAutores.traerAutores().query();
  }

}]);
