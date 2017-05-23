 app.controller('adminCamisetasCtrl',["$scope","servicioCamiseta","servicioCookies","$timeout",function($scope,servicioCamiseta,servicioCookies,$timeout){
   init();


   function init(){
     $scope.thumbnail = {};
     $scope.botonCancelar = false;
     $scope.accionRealizar = "Guardar camiseta";
     $scope.camisetaNuevaCargada = false;
     $scope.camisetaActualizada = false;

     if(servicioCookies.validarSiEstaAutenticado()){
       $scope.artista = servicioCookies.traerUsuarioAutenticado();
       console.log($scope.artista);
       recargarCamisetas();
        if(servicioCookies.validarSiEstaAutenticado())
        {
            $scope.autor = servicioCookies.traerUsuarioAutenticado();
        }
     }
   }
   function recargarCamisetas(){
     servicioCamiseta.traerCamisas().query().$promise.then((datos) => {
        $scope.camisetas = datos;
        angular.forEach($scope.camisetas,function(valor, llave){
          valor.indice = llave;
        });
      });
   }
   $scope.editarCamiseta = function (indice){
     $scope.accionRealizar = "Actualizar camiseta";
     $scope.botonCancelar = true;
     $scope.camisetaNueva = $scope.camisetas[indice];
     $scope.thumbnail.dataUrl = $scope.camisetas[indice].path;
     $scope.camisetaNuevaCargada = false;
     $scope.camisetaActualizada= false;
   }

   $scope.cancelar = function (){
     $scope.accionRealizar = "Guardar camiseta";
     $scope.botonCancelar = false;
     $scope.camisetaNueva ={};
     $scope.thumbnail={};
   }

   $scope.fileReaderSupported = window.FileReader != null;
   $scope.photoChanged = function(files){
     if (files != null) {
           var file = files[0];
       if ($scope.fileReaderSupported && file.type.indexOf('image') > -1)
       {
           $timeout(function() {
               var fileReader = new FileReader();
               fileReader.readAsDataURL(file);
               fileReader.onload = function(e) {
                   $timeout(function(){
                     // base64 para usar en el hmtl
                      $scope.thumbnail.dataUrl = e.target.result;
                      $scope.camisetaNuevaCargada = false;
                      $scope.camisetaActualizada= false;
                   });
               }
           });
       }
     }
   };

   $scope.cargarImagen = function (){
     if ($scope.accionRealizar == "Actualizar camiseta")
     {
        $scope.camisetaNueva.path = $scope.thumbnail.dataUrl;
        console.log($scope.camisetaNueva);
        servicioCamiseta.actualizarCamiseta($scope.camisetaNueva.camisetaId).update($scope.camisetaNueva).$promise.then(function(){
          $scope.camisetaActualizada= true;
          $scope.cancelar();
       });
     }
     else
     {
       var name = $scope.name;
       var file = $scope.file;
       $scope.camisetaNueva.path = $scope.thumbnail.dataUrl;
       //$scope.temaNuevo.temaId = 0;
       console.log($scope.camisetaNueva);
       servicioCamiseta.crearCamiseta().save($scope.camisetaNueva).$promise.then(function(datos){
        $scope.thumbnail.dataUrl;
        recargarCamisetas();
        $scope.cancelar();
       })
       .catch(function(err){
         console.log(err);
       });
     }
   }
 }]);
