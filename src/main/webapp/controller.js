var app = angular.module('app',[]);
app.controller('controller',['$scope','service', function ($scope, service){

    $scope.setScope = function(){
        service.setScope($scope);
    };

    $scope.loginAction = function () {
        service.loginAction();
    }
    }]
);