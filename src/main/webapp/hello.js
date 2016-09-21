angular.module('demo', [])
    .controller('Hello', function ($scope, $http) {

        $http.post('http://localhost:8080/login?email=gmail&password=1234')
           .success(function (response) {
               $scope.user = response.success;
           });
    });