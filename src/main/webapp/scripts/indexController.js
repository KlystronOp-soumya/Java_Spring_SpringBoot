/**
 * Index controller
 * also provides routing
 * Since other controller are at different files we have to register those
 */

console.log("Index called");

var mainApp=angular.module("mainApp",['ngRoute','showAllController','search1Controller','registrationController']);
mainApp.controller("indexController",()=>{});

mainApp.config(function($routeProvider){
    $routeProvider
      /*.when("",{
         templateUrl: "index.html",
         
      })*/
        
      .when("/all_employees",{
          templateUrl:"showAll.html",
          controller:"showAllController"
         
              
      })
      .when("/search",{
          templateUrl:"search1.html",
          controller:"search1Controller"
      })
      .when("/register",{
          templateUrl:"register.html",
         // controller:"registrationController"
      })
     .otherwise({
          template:alert("redirecting")
      });
    
});

