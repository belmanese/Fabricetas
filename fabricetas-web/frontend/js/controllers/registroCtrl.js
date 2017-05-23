app.controller("registroCtrl",["$scope","servicioHome","servicioLogin",function($scope,servicioHome,servicioLogin){
  init();
  function init(){
  }
  $scope.crearUsuario = function (){
    $scope.usuarioNuevo.direccion=[];
    $scope.usuarioNuevo.login = [];
    $scope.mensajeCreacion=false;
    //$scope.usuarioNuevo.direccion.push($scope.direccion);
    //$scope.usuarioNuevo.login.push($scope.login);
    $scope.usuarioNuevo.userId=$scope.usuarioNuevo.tipo;
    $scope.usuarioNuevo.ssoId=null;
    servicioLogin.crearUsuario().save($scope.usuarioNuevo).$promise.then(function(datos){
      console.log(datos);
      $scope.usuarioNuevo={};
      $scope.mensajeCreacion=true;
      $scope.mostrarMensajeCreacion = "animated fadeIn";
    });

  }
}]);
