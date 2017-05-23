var camisaAleatoria = angular.module("camisaAleatoria",[]);

camisaAleatoria.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/generar-camisa', {
    templateUrl: 'js/camisaAleatoria/camisaAleatoria.html',
    controller: 'camisaAleatoriaCtrl'
  });
}])

camisaAleatoria.controller("camisaAleatoriaCtrl",["$scope",function($scope){

  constructor();
  imprimirConEstampa();

  function constructor(){
    // cargar lista de artistas
    $scope.camisaGenerada = false;
  }

  $scope.generarCamisa = function (){
    console.log($scope.camisaAleatoria);
    $scope.camisaGenerada =!$scope.camisaGenerada;
  }


  function imprimirConEstampa(){
    var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');
    canvas.width = 700;
    canvas.height = 400;
    var img1 = new Image();
    var img2 = new Image();
    //context.globalAlpha = 1.0;
    //context.drawImage(img1, 0, 0,300,400);
    //context.drawImage(img2, 125, 175,50,80);
    img1.onload = function() {
      canvas.width = 400;
      canvas.height = 700;
      img2.src = "http://mascotafiel.com/wp-content/uploads/2016/03/hasta-qué-edad-crece-un-gato-1_opt.jpg";
    };
    img2.onload = function() {
      context.globalAlpha = 1.0;
      context.drawImage(img1, 0, 0,400,700);
      context.drawImage(img2, 110, 180,180,200);
      context.fillText("Texto en el Canvas para probar !",200,200);
    };
    img1.src = "http://issuprimentos.com.br/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/c/a/camiseta.jpg";
  }



}]);
