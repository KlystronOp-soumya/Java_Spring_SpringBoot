 //script to controll the search 
console.log("callled");
 var  searchApp =angular.module("mySearchApp",[]);
 const URL="http://localhost:9000/RestDemo/webapi/employees/search/";
searchApp.controller("searchCtrl",function($scope,$http){
    console.log($scope.emp_id);
    $scope.getEmployee =function(){
        //ajax
        var id=$scope.emp_id;
        console.log("ID:"+id);
        var url=URL+id;
        console.log(url);
        $http.get(url)
          .success(function(response){
                
               $scope.found = true;
               console.log("fetched data: "+response);
               $scope.fetchedEmployee=response;
              
          })
        .catch( function(error){
            console.log("error:"+error);
        } );
    }

});


