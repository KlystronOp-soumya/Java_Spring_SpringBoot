/**
 * 
 */
console.log(" search1 callled");
 var  searchApp =angular.module("search1Controller",[]);
 const URL_SEARCH="http://localhost:9000/RestDemo/webapi/employees/search/";
searchApp.controller("search1Controller",function($scope,$http){
    //console.log($scope.emp_id);
    $scope.getEmployee =function(){
        //ajax
        var id=$scope.emp_id;
        //console.log("ID:"+id);
        var url=URL_SEARCH+id;
        //console.log(url);
        $http.get(url)
          .then(function(response){
                
               $scope.found = true;
               console.log("fetched data: "+response.data);
               $scope.fetchedEmployee=response.data;
              
          })
        .catch( function(error){
            console.log("error:"+error);
        } );
    }

});