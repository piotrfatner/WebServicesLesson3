var app = angular.module('app');
app.service('service',['$http','$window', function ($http, $window) {
    var self = this;
    self.scope = null;
    self.setScope = function (scope) {
        self.scope = scope;
        self.scope.test ="xdd";
    };

    this.loginAction = function () {
        console.log("aaa");
        console.log(document.getElementById("login").value);
    };
}]);