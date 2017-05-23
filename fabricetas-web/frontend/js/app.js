var app = angular.module('fabrisetas', ['ngRoute','ngResource','ngMaterial','camisaAleatoria','720kb.socialshare']);
app.value('fabConstans', {
    //URL_BASE_SERVICIOS:"http://52.88.20.109:8080/fabricetas/"
    URL_BASE_SERVICIOS:"http://localhost:8080/fabricetas/"
    ,twitter:true
    ,facebook:false
    ,socialNetwork:false
    ,byArtist:false
    ,desingsByArtists:false
    ,rating:true
    ,reports:true
    ,changeAdress:false
    ,changePassword:true
    ,shareOnSocialNetwork:false
    ,SC_RamdonTShirt:false
    ,color:false
    ,text:true
    ,authFacebook:false
    ,authTwitter:false
    ,BD:true
    ,list:true
    ,gallery:false
    ,rateProducts:true
    ,advanceShearch:false
});

/*
{ 'get':    {method:'GET'},
  'save':   {method:'POST'},
  'query':  {method:'GET', isArray:true},
  'remove': {method:'DELETE'},
  'delete': {method:'DELETE'} };
*/


app.run(['$rootScope', '$window','servicioCookies',"$location","fabConstans",
  function($rootScope, $window,servicioCookies,$location,fabConstans) {
     function watchLoginChange() {

       var _self = this;

       FB.Event.subscribe('auth.authResponseChange', function(res) {

         if (res.status === 'connected')
         {
           getUserInfo();
           if(typeof $rootScope.user.name!='undefined')
           {
             servicioCookies.crearCookieUsuarioAutenticado($rootScope.user);
             $location.path("/");
           }
         }
         else
         {
         }

       });

     }

     /*inicializar todas las variables en el root de angular para acceder desde todos los controaldores*/
     $rootScope.rateProducts=fabConstans.twitter;
     $rootScope.rateProducts=fabConstans.facebook;
     $rootScope.rateProducts=fabConstans.socialNetwork;
     $rootScope.rateProducts=fabConstans.byArtist;
     $rootScope.rateProducts=fabConstans.desingsByArtists;
     $rootScope.rateProducts=fabConstans.rating;
     $rootScope.rateProducts=fabConstans.reports;
     $rootScope.rateProducts=fabConstans.changeAdress;
     $rootScope.rateProducts=fabConstans.changePassword;
     $rootScope.rateProducts=fabConstans.shareOnSocialNetwork;
     $rootScope.rateProducts=fabConstans.SC_RamdonTShirt;
     $rootScope.rateProducts=fabConstans.color;
     $rootScope.rateProducts=fabConstans.text;
     $rootScope.rateProducts=fabConstans.authFacebook;
     $rootScope.rateProducts=fabConstans.authTwitter;
     $rootScope.rateProducts=fabConstans.BD;
     $rootScope.rateProducts=fabConstans.list;
     $rootScope.rateProducts=fabConstans.gallery;
     $rootScope.rateProducts=fabConstans.rateProducts;
     $rootScope.advanceShearch=fabConstans.advanceShearch;
     console.log($rootScope.advanceShearch);

}]);

app.config(function($routeProvider){

    $routeProvider.when("/",{
        controller:"homeCtrl",
        templateUrl:"views/home.html"
    })
    .when("/login/:id?",{
        controller:"loginCtrl",
        templateUrl:"views/login.html"
    })
    .when("/registro",{
        controller:"registroCtrl",
        templateUrl:"views/registro.html"
    })
    .when("/categoria-estampas/:id?",{
        controller:"categoriaCtrl",
        templateUrl:"views/categoria-estampas.html"
    })
    .when("/navegar-autor/:autor?",{
        controller:"autorCtrl",
        templateUrl:"views/navegar-autor.html"
    })
    .when("/navegar/:opcion?",{
        controller:"navegarCtrl",
        templateUrl:"views/navegar.html"
    })
    .when("/ver-estampa/:id",{
        controller:"verEstampaCtrl",
        templateUrl:"views/ver-estampa.html"
    })
    .when("/camisas",{
        controller:"camisasCtrl",
        templateUrl:"views/camisas.html"
    })
    .when("/ver-camisa/:id",{
        controller:"verCamisaCtrl",
        templateUrl:"views/ver-camisa.html"
    })
    .when("/pagar",{
        controller:"pagarCtrl",
        templateUrl:"views/pagar.html"
    })
    .when("/artista",{
        controller:"artistaCtrl",
        templateUrl:"views/artista.html"
    })
    .when("/reportes",{
        controller:"reportesCtrl",
        templateUrl:"views/reportes.html"
    })
    .when("/adminTemas",{
        controller:"adminTemasCtrl",
        templateUrl:"views/adminTemas.html"
    })
    .when("/adminCamisetas",{
        controller:"adminCamisetasCtrl",
        templateUrl:"views/adminCamisetas.html"
    })
    .when("/adminUsuarios",{
        controller:"adminUsuariosCtrl",
        templateUrl:"views/adminUsuarios.html"
    })
    .when("/resumenCompra",{
        controller:"resumenCompraCtrl",
        templateUrl:"views/resumenCompra.html"
    })
    .otherwise({
      redirectTo: '/login'
    });
});
