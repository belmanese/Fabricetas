app.service("servicioReportes",["fabConstans","$http","$q","$resource",function(fabConstans,$http,$q,$resource){
	var vm= this;
	var dirServ = fabConstans.URL_BASE_SERVICIOS;

	//http://52.88.20.109:8080/fabricetas/reporte_artista_fecha/{artistaId}_{fechaInicial}_{fechaFinal}

	vm.reportePorFechas = function (artistaId,fechaInicial,fechaFinal){
		var url = artistaId+"_"+fechaInicial+"_"+fechaFinal;
    return $resource(dirServ+"reporte_artista_fecha/"+url);
  }

	vm.reportePorTemas = function (artistaId,temaId){
		var url= "/reporte_artista_tema/"+artistaId+"_"+temaId;
		return $resource(dirServ+url);
	}

	vm.reportePorEstampas = function (userId){
		var url ="reporte_estampas_"+userId;
		return $resource(dirServ+url);
	}

	vm.reportePorEstampasAdmin =function (){
		var url ="reporte_estampas";
		return $resource(dirServ+url);
	}

	vm.reportePorArtista = function (artistaId){
		// return $resource("http://52.88.20.109:8080/fabricetas/estampa"); deberia ser un post
    let objeto =[
      {
				"artista":"Bernad",
        "nombre":"Devil may Cry",
        "tema":"Videojuegos",
        "valor":20000,
        "cantidad":4,
        "total":80000
      },
      {
				"artista":"Bernad",
        "nombre":"Abuelo",
        "tema":"Animaciones",
        "valor":22000,
        "cantidad":1,
        "total":22000
      },
      {
				"artista":"Bernad",
        "nombre":"Assasins creed",
        "tema":"Videojuegos",
        "valor":10000,
        "cantidad":4,
        "total":40000
      },
      {
				"artista":"Bernad",
        "nombre":"React JS",
        "tema":"Tecnología",
        "valor":15000,
        "cantidad":4,
        "total":60000
      }
    ]
    return objeto;
	}

	vm.reportePorCamiseta = function (){
		return $resource(dirServ+"reporte_camisetas");
	}

}]);
