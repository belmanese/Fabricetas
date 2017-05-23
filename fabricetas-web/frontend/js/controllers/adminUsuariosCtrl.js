app.controller("adminUsuariosCtrl",["$scope","servicioHome", "servicioCategoria","servicioCookies","servicioAdminUsuarios","$location",function($scope, servicioHome, servicioCategoria,servicioCookies,servicioAdminUsuarios,$location){
	init();


	$scope.editarUsuario = function (indice){
		$scope.titulo="Edición de usuario";
		$scope.nuevoOActualizar = true;
		$scope.accion="Actualizar";
		$scope.usuarioSeleccionado = $scope.listaUsuarios[indice];
		console.log($scope.usuarioSeleccionado);
	}

	$scope.crearUsuario = function (indice){
		$scope.titulo="Creación de usuario";
		$scope.nuevoOActualizar = true;
		$scope.accion="Crear";
		$scope.usuarioSeleccionado={};
		$scope.usuarioSeleccionado.userId=null;
		$scope.usuarioSeleccionado.ssoId=null;
		console.log($scope.usuarioSeleccionado);
	}

	$scope.eliminarUsuario = function (userId){
		servicioAdminUsuarios.eliminarUsuario(userId).delete().$promise.then((datos)=>{
			/*$scope.listaUsuarios.filter(function(usuario){
						// si retorna falso la funcion filter elimina el objeto
						return usuario.userId!==userId;
			});*/
			recargarUsuarios();
		},
		(err)=>{
			alert("err");
			console.log(err);
		});
	}

	$scope.crearOActualizarUsuario = function (){
		if ($scope.accion=='Actualizar')
		{
				$scope.usuarioSeleccionado.role[0]={};
				$scope.usuarioSeleccionado.role[0].roleId = $scope.usuarioSeleccionado.tipo;
				servicioAdminUsuarios.actualizarUsuario($scope.usuarioSeleccionado.userId).update($scope.usuarioSeleccionado).$promise.then((datos)=>{
					$scope.nuevoOActualizar=false;
					recargarUsuarios();
				},
				(err)=>{
						alert("error");
						console.log(err);
				});
		}
		else if ($scope.accion=="Crear")
		{
			console.log($scope.usuarioSeleccionado);
			$scope.usuarioSeleccionado.userId = $scope.usuarioSeleccionado.tipo;
			servicioAdminUsuarios.crearUsuario().save($scope.usuarioSeleccionado).$promise.then((datos)=>{
				$scope.nuevoOActualizar=false;
				 //$scope.listaUsuarios.push(datos)
				//console.log(datos);
				recargarUsuarios();
			},
			(err)=>{
				alert("error");
				console.log(err);
			});
		}
	}
	function recargarUsuarios(){
		$scope.listaUsuarios = servicioAdminUsuarios.traerUsuarios().query().$promise.then((datos)=>{
			$scope.listaUsuarios = datos;
			angular.forEach($scope.listaUsuarios,function(valor,llave){
				valor.indice=llave;
			});
		});
	}

	function init(){
		if(servicioCookies.validarSiEstaAutenticado())
		{
			recargarUsuarios();
		}
		else
		{
				// si no está logeado lo retorna a la pagina inicial
				$location.path("/");
		}
	}
}]);
