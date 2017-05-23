var perfil = angular.module("perfil",[]);

perfil.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/perfil', {
    templateUrl: 'js/perfil/perfil.html',
    controller: 'perfilCtrl'
  });
}])

perfil.controller("perfilCtrl",["$scope","servicioCookies","servicioPerfil","servicioAdminUsuarios","$timeout","fabConstans",
function($scope,servicioCookies,servicioPerfil,servicioAdminUsuarios,$timeout,fabConstans){
  init();

  function init(){
    $scope.changePassword = fabConstans.changePassword;
    $scope.changeAdress = fabConstans.changeAdress;
    $scope.usuario = servicioCookies.traerUsuarioAutenticado();
    servicioPerfil.traerPerfil().get({"id":$scope.usuario.userId}).$promise.then((datos=>{
      $scope.usuarioCompleto = datos;
      angular.forEach($scope.usuarioCompleto.address,function(valor,llave){
        valor.indice=llave;
      });
      console.log(datos);
    }));
  }

  $scope.agregarDireccion = function (){
    let direccion = {
      "addressId":"",
      "name":"",
      "user_id":$scope.usuarioCompleto.userId
    }
    $scope.usuarioCompleto.address.push(direccion);
    angular.forEach($scope.usuarioCompleto.address,function(valor,llave){
      valor.indice=llave;
    });
  }

  // $scope.eliminarDireccion = function (indice){
  //   $scope.usuarioCompleto.address.splice(indice,1);
  //   angular.forEach($scope.usuarioCompleto.address,function(valor,llave){
  //     valor.indice=llave;
  //   });
  // }

  $scope.actualizarDireccion = function (direccion){
    console.log(direccion);
    if(direccion.addressId!="")
    {
        // va a actualizar
        servicioPerfil.actualizarDireccion(direccion).update(direccion).$promise.then(function(datos){
          $scope.guardado = true;
          $timeout(function () {
            $scope.guardado = false;
          }, 2000);
        });
    }
    else
    {
      // va crear
      servicioPerfil.crearDireccion().save(direccion).$promise.then(function(datos){
        $scope.guardado = true;
        $timeout(function () {
          $scope.guardado = false;
        }, 2000);
      });
    }
  }
  $scope.actualizarDatosUsuario = function (){
    servicioAdminUsuarios.actualizarUsuario($scope.usuarioCompleto.userId).update($scope.usuarioCompleto).$promise.then((datos)=>{
      $scope.guardadoUsuario = true;
      $timeout(function () {
        $scope.guardadoUsuario = false;
      }, 2000);
    },
    (err)=>{
        alert("error");
        console.log(err);
    });
  }
}]);
