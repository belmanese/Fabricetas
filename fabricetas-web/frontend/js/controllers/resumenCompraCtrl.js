app.controller("resumenCompraCtrl",["$scope","$routeParams","servicioAutores","servicioHome","servicioCookies","fabConstans","servicioRating","$location","$mdToast",
function($scope,$routeParams,servicioAutores,servicioHome,servicioCookies,fabConstans,servicioRating,$location,$mdToast){
  init();
  function init(){
    $scope.twitter=fabConstans.twitter;
    $scope.facebook=fabConstans.facebook;
    $scope.socialNetwork=fabConstans.socialNetwork;
    $scope.rating=fabConstans.rating;
    $scope.resumenCompra = servicioCookies.retornarResumenCompra();
    angular.forEach($scope.resumenCompra,function(conf, llave){
      conf.ratingId = "0";
      conf.isCalificada = false;
      conf.mensajeBoton = "Calificar";
    });
    servicioRating.traerRatings().query().$promise.then((datos) => {
       $scope.ratings = datos;
     });
  }
  $scope.limpiarCarrito = function(){
    servicioCookies.descartarCarrito();
    $location.path("/");
  }
  $scope.enviarRating = function(configCalificada){
    let calificacion ={
      "ratingId":configCalificada.ratingId,
      "stampId":configCalificada.estampaId
    }
    if(!configCalificada.isCalificada)
    {
      servicioRating.calificarEstampa().save(calificacion).$promise.then(() => {
        configCalificada.isCalificada = true;
        configCalificada.mensajeBoton = "Ya calificada";
      });
    }
    console.log("Rating: " + configCalificada.ratingId + "para estampa: " + configCalificada.estampaId);
  }
}]);
