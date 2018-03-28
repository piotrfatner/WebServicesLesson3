var app = angular.module('app',[]);
app.controller('loginCtrl', ['$scope', 'loginService', function ($scope, loginService) {

    $scope.setScope = function(){
        loginService.setScope($scope);
    };
    $scope.loginAction = function () {
        loginService.loginAction();
    };

    $scope.signupAction = function () {
        loginService.signupAction();
    }

    $scope.signUp = function () {
        loginService.signUp();
    };

    $scope.loginIn = function () {
        loginService.LoginIn();
    };
    $scope.testAction = function () {
        loginService.testAction();
    }

    $scope.setScope();

}]);