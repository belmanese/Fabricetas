 app.controller('pagarCtrl',["$scope","servicioCookies","$location","$timeout","servicioFactura",function($scope,servicioCookies,$location,$timeout,servicioFactura){
   init();
   function  init(){
     $scope.odireccion={};
     $scope.direccion=false;
     $scope.compraRealizada = false;
     $scope.tieneItemsAgregados=false;
     if(servicioCookies.validarSiEstaAutenticado())
     {
       $scope.tieneItemsAgregados = true;
       $scope.datosUsuario = servicioCookies.traerUsuarioAutenticado();
       $scope.carrito = servicioCookies.retornarCarrito();
       $scope.total = servicioCookies.valorCarrito();
       $scope.datosUsuario.direccion="Av 102 # 10 - 12";
       console.log($scope.datosUsuario);
     }
     else
     {
       $scope.tieneItemsAgregados=false;
     }
   }
   $scope.cambiarDireccion = function (){
     console.log($scope.direccion);
   }
   $scope.descartarCarrito = function (){
     servicioCookies.descartarCarrito();
     $location.path("/");
   }
   $scope.registrarFactura = function (){
     var estampas = new Array();
     var camisa;
     var itemInvoiceDto=new Array();
     var temp={}; // para ccambiar nombres de llaves del json
     $scope.usuario=servicioCookies.traerUsuarioAutenticado();
     if(typeof $scope.usuario.userId!="undefined" || typeof $scope.usuario.id!="undefined")
     {

       angular.forEach($scope.carrito, function(value, key) {
         angular.forEach(value, function(valores, key) {
           if(typeof valores.stampId!='undefined')
           {
             temp.estampaId=valores.stampId;
             temp.nombre = valores.name;
             temp.descripcion = valores.description;
             temp.urlImagen = valores.path;
             temp.rating = valores.rating;
             temp.valor = valores.price;
             temp.temaId=valores.theme;
             temp.temaNombre =valores.theme;
             temp.precio= valores.price;
             temp.urlEstampa = valores.path;
             estampas.push(temp);
             temp ={};
           }
           else
           {
             temp.camisetaId =valores.tshirtId;
             temp.nombre =valores.name;
             temp.descripcion =valores.description;
             temp.urlImagen =valores.path;
             temp.material =valores.material;
             temp.color =valores.color;
             temp.valor =valores.price;
             temp.urlCamiseta =valores.path;
             camisa = temp;
             temp ={};
           }
           console.log(valores,key);
         });
         itemInvoiceDto.push({"tshirtForInvoiceDto":camisa,"stampForInvoiceDto":estampas});
       });
       var usuarioComprador;
       if(typeof $scope.usuario.userId=='undefined'){
         usuarioComprador = $scope.usuario.id;
       }
       else{
         usuarioComprador = $scope.usuario.userId;
       }
       let objetoFactura ={
         "userId": usuarioComprador,
         "itemInvoiceDto":itemInvoiceDto
       }
       console.log("debugPagar");
       console.log(objetoFactura);
       servicioFactura.guardarFactura().save(objetoFactura).$promise.then(function(datos){
         console.log(datos);
         $scope.compraRealizada = true;
         $location.path("/resumenCompra");
       })
       .catch(function(err){
          console.log(err);
          $scope.compraRealizada = true;
          $location.path("/resumenCompra");
       })
     }
   }
   $scope.verResumenCompra = function (){
     servicioCookies.inicializarCarrito();
     $location.path("/");
   }
   $scope.seguirComprando = function (){
     $location.path("/");
   }
 }]);
