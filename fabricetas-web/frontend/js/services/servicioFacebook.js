app.service("servicioFacebook",["fabConstans","$http","$q","$resource",function(fabConstans,$http,$q,$resource){
	var vm= this;
	var datosUsuario;

	vm.cerrarSesionFacebook = function (){
    var deferred = $q.defer();
    FB.logout(function(response){
      deferred.resolve(response);
    });
  }
	vm.getUserInfo = function (){
		var deferred = $q.defer();
		FB.api('/me',function(response) {
				if (!response || response.error) {
						deferred.reject('Error occured');
				} else {
						deferred.resolve(response);
				}
		});
		return deferred.promise;
	}


	vm.logeado = function() {
		var deferred = $q.defer();
		  FB.Event.subscribe('auth.authResponseChange', function(res) {
		    if (res.status === 'connected')
				{
					vm.getUserInfo().then((datos)=>{
						console.log(datos);
						deferred.resolve(datos);
					})
		    }
		    else
				{
					//error en login
		    }
		  });
			return deferred.promise;
		}

		vm.retornarUsuario = function (){
			return datosUsuario;
		}


}]);
