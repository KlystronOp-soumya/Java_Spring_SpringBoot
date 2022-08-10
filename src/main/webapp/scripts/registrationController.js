/**
 * this script controlls the registration
 */

console.log("Registration controller called");
var registrationApp=angular.module("registrationController",[]);
const URL_REGISTER="http://localhost:9000/RestDemo/webapi/employees/register/";

registrationApp.controller("registrationController",($http,$scope)=>{
    /**
     * get the fields from the form
     * create json object
     * create a URL header
     * make a post request
     * advance:Create a service here that stores all the fecthed result then send this to the index page
     */
    
    //create an object
    let gotEmployeeDetails={};
    //call the function
    $scope.registerNewEmployee = () =>{
        
    
         gotEmployeeDetails={
            "emp_id":parseInt($scope.emp_id),
        "fname":$scope.fname,
        "lname":$scope.lname,
        "position":$scope.position,
        "salary":parseFloat($scope.salary)
         };
  
         //Uncomment to debug
       /* 
        console.log("Debugging print");
        console.log(`The fetched data: id:${gotEmployeeDetails.emp_id} Name:${gotEmployeeDetails.fname} ${gotEmployeeDetails.lname} Designation:${gotEmployeeDetails.position} Salary:${gotEmployeeDetails.salary}`);
        console.log(JSON.stringify(gotEmployeeDetails))*/
        //fetchCallback();
         $scope.body=JSON.stringify(gotEmployeeDetails);
         $http.post(URL_REGISTER,$scope.body,[{header:{"content-type":"application/json"}}])
            .then((response)=>{
                console.log(response.data);
    
            }).catch((err)=>{
                console.error(err);
            });
      };
    
      //try to implement this as a service
      
      /*const fetchCallback = ($scope,$http) => {
         // console.log("Inside the Callback");
        $http.post(URL_REGISTER,[{
            body:JSON.stringify(gotEmployeeDetails),
            headers:{"content-type":"application/json","charset":"UTF-8"}
        }]).then((response)=>{
            console.log(response.data);

        }).catch((err)=>{
            console.error(err);
        });
      } */
     
    
});