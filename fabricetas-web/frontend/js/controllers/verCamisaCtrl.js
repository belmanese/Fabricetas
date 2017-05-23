app.controller("verCamisaCtrl",["$scope","servicioCamiseta","$location","$routeParams","servicioCookies",function($scope,servicioCamiseta,$location,$routeParams,servicioCookies){
  init();
  function init(){
    let id = $routeParams.id;
    $scope.total;
    $scope.talla='Sin seleccionar';
    $scope.color='black';
    $scope.colorTexto='white';
    $scope.textTshirt="";
    $scope.estampaSeleccionada = servicioCookies.traerEstampaSeleccionada();
    servicioCamiseta.traerCamisasPorId(id).get().$promise.then(function (result) {
      console.log(result);
      $scope.camisetaSeleccionada = result;
      $scope.total = parseInt($scope.camisetaSeleccionada.price) + parseInt($scope.estampaSeleccionada.price);
      console.log($scope.total);
      imprimirConEstampa("black", "Hola probando");
    });
  }

  $scope.agregarCarrito = function (){
    if(servicioCookies.validarSiEstaAutenticado()){
      var canvas = document.getElementById('canvas');
      var urlResultado = canvas.toDataURL("image/png");
      
      servicioCookies.aregarAlCarrito($scope.camisetaSeleccionada,$scope.estampaSeleccionada,$scope.total, urlResultado);
      $location.path("/pagar");
    }
    else
    {
        $location.path("/login/"+$routeParams.id);
    }
  }

  $scope.descartar = function (){
    servicioCookies.eliminarCamisetaSeleccionada();
    servicioCookies.eliminarEstampaSeleccionada();
    $location.path('/');
  }
  function imprimirConEstampa(){
    var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');
    canvas.width = 300;
    canvas.height = 350;
    var img1 = new Image();
    var img2 = new Image();
    var timestamp = new Date().getTime();

    img1.onload = function() {
      canvas.width = 300;
      canvas.height = 300;
      img2.src = $scope.estampaSeleccionada.path;
    };
    img2.onload = function() {
      context.globalAlpha = 1.0;

      context.beginPath();
      context.rect(0, 0, 300, 300);
      context.fillStyle = $scope.color;
      context.fill();
      context.closePath();
      context.drawImage(img1, 0, 0,300,300);
      context.drawImage(img2, 125, 110,50,80);
      context.fillStyle =   $scope.colorTexto;
      context.font = "bold 9px sans-serif";
      context.textAlign="center";
      context.fillText($scope.textTshirt, 150, 200);
    };
    img1.src = $scope.camisetaSeleccionada.path;
    console.log("probando 2");
  }
  $scope.seleccionTalla = function (talla){
    $scope.talla = talla;
    console.log(talla);
  }
  $scope.seleccionColor = function (color, isXaTexto){
    if(isXaTexto)
      $scope.colorTexto = color;
    else
      $scope.color = color;

    imprimirConEstampa();
  }
  $scope.cambioTexto = function (){
      imprimirConEstampa();
  }
}]);
