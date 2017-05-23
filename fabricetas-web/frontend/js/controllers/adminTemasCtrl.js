 app.controller('adminTemasCtrl',["$scope","servicioCategoria","servicioAutores","servicioCookies","$timeout",function($scope,servicioCategoria,servicioAutores,servicioCookies,$timeout){
   init();


   function init(){
     $scope.thumbnail = {};
     $scope.botonCancelar = false;
     $scope.accionRealizar = "Guardar Tema";
     $scope.temaNuevoCargado = false;
     $scope.temaActualizado = false;

     //if(servicioCookies.validarSiEstaAutenticado()){
       $scope.artista = servicioCookies.traerUsuarioAutenticado();
       console.log($scope.artista);
       recargarTemas();
        if(servicioCookies.validarSiEstaAutenticado())
        {
            $scope.autor = servicioCookies.traerUsuarioAutenticado();
        }
     //}
   }
   function recargarTemas(){
     servicioCategoria.traerCategorias().query().$promise.then((datos) => {
        $scope.temas = datos;
        angular.forEach($scope.temas,function(valor, llave){
          valor.indice = llave;
        });
      });
   }
   $scope.editarTema = function (indice){
     $scope.accionRealizar = "Actualizar tema";
     $scope.botonCancelar = true;
     $scope.temaNuevo = $scope.temas[indice];
     $scope.thumbnail.dataUrl = $scope.temas[indice].path;
     $scope.temaNuevoCargado = false;
     $scope.temaActualizado = false;
   }

   $scope.cancelar = function (){
     $scope.accionRealizar = "Guardar tema";
     $scope.botonCancelar = false;
     $scope.temaNuevo ={};
     $scope.thumbnail ={};
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
                      $scope.temaNuevoCargado = false;
                      $scope.temaActualizado = false;
                   });
               }
           });
       }
     }
   };

   $scope.cargarImagen = function (){
     if ($scope.accionRealizar == "Actualizar tema")
     {
        $scope.temaNuevo.path = $scope.thumbnail.dataUrl;
        console.log($scope.temaNuevo);
        servicioCategoria.actualizarCategoria($scope.temaNuevo.temaId).update($scope.temaNuevo).$promise.then(function(){
          $scope.temaActualizado = true;
          $scope.cancelar();
       });
     }
     else
     {
       var name = $scope.name;
       var file = $scope.file;
       $scope.temaNuevo.path = $scope.thumbnail.dataUrl;
       //$scope.temaNuevo.temaId = 0;
       console.log($scope.temaNuevo);
       servicioCategoria.crearCategoria().save($scope.temaNuevo).$promise.then(function(datos){
         console.log(datos);
         $scope.temaNuevoCargado = true;
         recargarTemas();
         $scope.cancelar();
       })
       .catch(function(err){
         console.log(err);
       });
     }
   }
 }]);
