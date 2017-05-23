camisaAleatoria.service("servicioCamisaAleatoria",["fabConstans","$http","$q","$resource",
function(fabConstans,$http,$q,$resource){
  var vm = this;
  var dirServ = fabConstans.URL_BASE_SERVICIOS;

  vm.traerCamisaGenerada = function (valores){
    if(valores.precioEstampa=="")
    {
      return $resource(dirServ+"tshirt/random");      
    }
    else
    {
      var ruta = "precioEstampa="+valores.precioEstampa+"&precioCamisa="+valores.precioCamisa+"&artistaId="+valores.artistaId+"&temaId="+valores.temaId;
      return $resource(dirServ+"tshirt/random/?"+ruta);
    }
  }
}])
