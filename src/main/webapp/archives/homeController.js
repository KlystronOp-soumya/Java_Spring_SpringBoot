/**
 * Script that controls the Home.jsp
 */

const URL="http://localhost:9000/RestDemo/webapi/employees/";
//console.log("called"); //debug
var app=angular.module("mainApp",[]);
app.controller("responseContorller",function($scope,$http){
	$http.get(URL)
	  .success(function (response){
	      $scope.showResponse=response;
	   })
	  .error(function(e){
	      $scope.showError=e;
	      
	  });
  //  console.log("called"); //debug
	//$scope.showResponse="Hello World";  //debug
});
