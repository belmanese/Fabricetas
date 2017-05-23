app.service("servicioAutores",["fabConstans","$http","$q","$resource",function(fabConstans,$http,$q,$resource){
	var vm= this;
  //var dirServ = "http://52.88.20.109:8080/fabricetas/";
	var dirServ = fabConstans.URL_BASE_SERVICIOS;

	vm.traerEstampasAutor = function (autor){
		return $resource(dirServ + "stamp/home/?artistId=:id",{id:autor});
	}

	vm.traerEstampasAutorNode = function (autor){
		return $resource("http://52.88.20.109:3000/api/estampas/autor/:id",{id:autor});
		//return $resource("http://52.88.20.109:3000/api/estampas/autor/:id",{id:autor});
	}

	vm.traerAutores = function (autor){
		return $resource(dirServ + "user/role/artist");
	}
	vm.cargarEstampa = function (){
		return $resource(dirServ + "stamp");
	}

	vm.actualizarEstampa = function (){
		return $resource(dirServ + "stamp",null,{update : {method : "PUT"}});
	}

	/* metodo viejo no funciona*/
	vm.cargarNuevaEstampa = function(file,name,detalle){
			var deferred = $q.defer();
			var formData = new FormData();
			formData.append("name", name);
			formData.append("description", file);

			/*formData.append("nombre",detalle.nombre);
			formData.append("valor", detalle.valor);
			formData.append("descripcion", detalle.descripcion);
			formData.append("tema", detalle.tema);
			formData.append("autor", detalle.autor);
			console.log(formData.nombre);*/
			return $http.post("http://52.88.20.109:8080/fabricetas/add-document-3", formData, {
				headers: {
					"Content-type": "multipart/form-data"
				},
				transformRequest: angular.identity
			})
			.then(function(res)
			{
				deferred.resolve(res);
			})
			.catch(function(msg, code)
			{
				deferred.reject(msg);
			})
			return deferred.promise;
	}

}]);
