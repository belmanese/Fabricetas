app.controller("verEstampaCtrl",["$scope","servicioHome","$routeParams","servicioCookies","$location","servicioCategoria",function($scope,servicioHome,$routeParams,servicioCookies,$location, servicioCategoria){
  init();
  function init (){
    let id = $routeParams.id;
    servicioHome.traerEstampasPorId(id).get().$promise.then((datos)=>{
        $scope.estampa = datos;
    });
    $scope.temas = servicioCategoria.traerCategorias().query();
  }
  $scope.irSeleccionCamisa = function (){
    servicioCookies.crearCookieEstampaSeleccionada($scope.estampa);
    $location.path("/camisas");
  }
}]);
