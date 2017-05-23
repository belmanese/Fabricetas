var camisaAleatoria = angular.module("camisaAleatoria",[]);

camisaAleatoria.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/generar-camisa', {
    templateUrl: 'js/camisaAleatoria/camisaAleatoria.html',
    controller: 'camisaAleatoriaCtrl'
  });
}])

camisaAleatoria.controller("camisaAleatoriaCtrl",["$scope","$location","servicioAutores","servicioCategoria","servicioCamisaAleatoria","servicioCookies",
function($scope,$location,servicioAutores,servicioCategoria,servicioCamisaAleatoria,servicioCookies){

  constructor();

  function constructor(){
    // cargar lista de temas
    $scope.texto = {};
    servicioCategoria.traerCategorias().query().$promise.then((datos)=>{
      $scope.listaTemas = datos;
    });

    $scope.camisaGenerada = false;
    //traer artistas
    servicioAutores.traerAutores().query().$promise.then((datos)=>{
      $scope.listaArtistas = datos;
      console.log(datos);
    },
    (err)=>{
      console.log(err);
    });
  }

  $scope.generarCamisa = function (){
    try
    {
      let valores = {
          "precioEstampa":$scope.camisaAleatoria.precioEstampa,
          "precioCamisa":$scope.camisaAleatoria.precioCamisa,
          "artistaId":$scope.camisaAleatoria.artista.userId,
          "temaId":$scope.camisaAleatoria.tema.themeId
      }
      console.log(valores);
      servicioCamisaAleatoria.traerCamisaGenerada(valores).query().$promise.then(function(datos){
        console.log(datos);
      })
    }
    catch (e)
    {
      console.log(e);
      let valores = {
          "precioEstampa":"",
          "precioCamisa":"",
          "artistaId":"",
          "temaId":""
      }
      console.log(valores);
      servicioCamisaAleatoria.traerCamisaGenerada(valores).get().$promise.then(function(datos){
        $scope.objetoCamisa = datos;
        $scope.imprimirConEstampa(datos.stamp[0].path,datos.path);
        $scope.imgEstampa=datos.stamp[0].path;
        $scope.imgCamisa=datos.path;
        console.log(datos);
      })
    }
    $scope.camisaGenerada =!$scope.camisaGenerada;
  }


  $scope.imprimirConEstampa =function (imgEstampa,imgCamisa){
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
      img2.src = imgEstampa;
    };
    img2.onload = function() {
      context.globalAlpha = 1.0;
      context.drawImage(img1, 0, 0,400,700);
      context.drawImage(img2, 110, 180,180,200);
      context.fillText($scope.texto.texto,$scope.texto.coordx,$scope.texto.coordy);
    };
    img1.src = imgCamisa;
  }

  $scope.agregarCarrito = function (){
    if(servicioCookies.validarSiEstaAutenticado()){
      var canvas = document.getElementById('canvas');
      var urlResultado = canvas.toDataURL("image/png");
      $scope.total = parseInt($scope.objetoCamisa.price) + parseInt($scope.objetoCamisa.stamp[0].price);
      var estampa = angular.copy($scope.objetoCamisa);
      delete $scope.objetoCamisa["stamp"];
      servicioCookies.aregarAlCarrito($scope.objetoCamisa,estampa.stamp[0],$scope.total, urlResultado);
      $location.path("/pagar");
    }
    else {
      alert("Primero debes iniciar sesión");
    }
  }

}]);
