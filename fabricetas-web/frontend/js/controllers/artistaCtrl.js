 app.controller('artistaCtrl',["$scope","servicioAutores","servicioCookies","$timeout","servicioCategoria",function($scope,servicioAutores,servicioCookies,$timeout, servicioCategoria){
   init();

   function init(){
     $scope.thumbnail = {};
     $scope.botonCancelar = false;
     $scope.accionEstampa="Guardar estampa";
     $scope.estampaNuevaCargada = false;
     $scope.estampaActualizada = false;
     var stampToSend = {};
     //if(servicioCookies.validarSiEstaAutenticado()){
       $scope.artista = servicioCookies.traerUsuarioAutenticado();
       console.log($scope.artista);
       recargarEstampas();
        if(servicioCookies.validarSiEstaAutenticado())
        {
            $scope.autor = servicioCookies.traerUsuarioAutenticado();
        }
     //}
     $scope.temas = servicioCategoria.traerCategorias().query();
  }

   function recargarEstampas(){
     servicioAutores.traerEstampasAutor($scope.artista.userId).query().$promise.then((datos)=>{
     //servicioAutores.traerEstampasAutor(1).query().$promise.then((datos)=>{
       console.log(datos);
       $scope.estampasArtista = datos;
       angular.forEach($scope.estampasArtista,function(valor,llave){
         valor.indice=llave;
       });
     });
   }

   function armarInfoEnviar(isActualizar){
      stampToSend ={};
      if(isActualizar)
       stampToSend.stampId = $scope.estampaNueva.stampId;
     stampToSend.name = $scope.estampaNueva.name;
     stampToSend.description = $scope.estampaNueva.description;
     stampToSend.path = $scope.estampaNueva.path;
     stampToSend.price = $scope.estampaNueva.price;
     stampToSend.user = {};
     stampToSend.user.userId = $scope.artista.userId;;
     stampToSend.theme = {};
     stampToSend.theme.themeId = $scope.estampaNueva.themeId;
   }

   $scope.editarEstampa = function (indice){
     $scope.accionEstampa ="Actualizar estampa";
     $scope.botonCancelar = true;
     $scope.estampaNueva = $scope.estampasArtista[indice];
     $scope.thumbnail.dataUrl = $scope.estampasArtista[indice].path;
     $scope.estampaNuevaCargada = false;
     $scope.estampaActualizada = false;
   }

   $scope.cancelar = function (){
     $scope.accionEstampa="Guardar estampa";
     $scope.botonCancelar = false;
     $scope.estampaNueva ={};
     $scope.thumbnail = {};
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
                      $scope.estampaNuevaCargada = false;
                      $scope.estampaActualizada = false;
                   });
               }
           });
       }
     }
   };

   $scope.cargarImagen = function (){
     if ($scope.accionEstampa=="Actualizar estampa")
     {
       typeof $scope.thumbnail.dataUrl ==='undefined' ? "":$scope.estampaNueva.path = $scope.thumbnail.dataUrl;

       armarInfoEnviar(true);

       servicioAutores.actualizarEstampa().update(stampToSend).$promise.then((datos)=>{
         console.log(datos);
         $scope.estampaActualizada = true;
         $scope.cancelar();
       },(err)=>{
         console.log(err);
       });
     }
     else
     {
       $scope.estampaNueva.path = $scope.thumbnail.dataUrl;
       armarInfoEnviar(false);
       console.log("Crea Estampa");
       console.log($scope.estampaNueva);
       servicioAutores.cargarEstampa().save(stampToSend).$promise.then((datos)=>{
          console.log(datos);
          $scope.estampaNuevaCargada = true;
          $scope.cancelar();
           recargarEstampas();
       },(err)=>{
         console.log(err);
       });
     }
   }
 }]);
