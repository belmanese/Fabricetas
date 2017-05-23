app.controller("camisasCtrl",["$scope","servicioCamiseta","servicioCookies","$location",function($scope,servicioCamiseta,servicioCookies,$location){
  init();

$scope.verDetalleCamiseta = function (camisa_id){
  let camisetaSeleccionada = $scope.listaCamisas.filter(function(element){
      return element.tshirtId ===camisa_id;
  });
  servicioCookies.crearCookieCamisetaSeleccionada(camisetaSeleccionada[0]);
  $location.path("ver-camisa/"+camisetaSeleccionada[0].tshirtId);
}

//Elimina la cookie donde se almacena la camiseta seleccionada
  $scope.volverASeleccion = function (){
    servicioCookies.eliminarEstampaSeleccionada();
    $location.path("/");
  }

  // carga los datos iniciales de la vista
  function init(){
    $scope.estampaSeleccionada = [];
      $scope.estampaSeleccionada = servicioCookies.traerEstampaSeleccionada();
      $scope.listaCamisas = servicioCamiseta.traerCamisas().query();
  }
}]);
