/**
 * This script cotrols the view : home1Controller
 */
console.log("showAll called");
const URL="http://localhost:9000/RestDemo/webapi/employees/";
//console.log("called"); //debug
var showAll=angular.module("showAllController",[]);

showAll.controller("showAllController",function($scope,$http){
    $http.get(URL)
      .then(function (response){
          $scope.showEmployees=response.data;
       })
      .catch(function(e){
          $scope.showError=e;
          
      });
  //  console.log("called"); //debug
    //$scope.showResponse="Hello World";  //debug
});