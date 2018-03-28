var app = angular.module('app');
app.service('loginService',['$http','$window', function ($http, $window) {
    var self = this;
    self.scope = null;
    var loginDto;
    self.setScope = function(scope){
        self.scope = scope;
        self.scope.invalidCreditentials = false;
        self.scope.loginForm = true;
        self.scope.singupForm = false;
        self.scope.modalHeader = "";
        self.scope.modalBody = "";
        self.scope.test = "xd";
        self.scope.test22 = "xdddlooool";
    };
    this.loginAction = function () {
        console.log(document.getElementById("login").value);
        console.log(document.getElementById("pwd").value);

            console.log("Logged");
            loginDto ={ login: document.getElementById("login").value,
                password: document.getElementById("pwd").value
            };
            console.log(loginDto);
            $http.post("/login", loginDto).success(
                function (data) {
                    console.log(data);
                    if(data == ''){
                        self.scope.invalidCreditentials = true;
                    }
                    else{
                        self.scope.invalidCreditentials = false;
                        $window.sessionStorage.setItem('userInfo-token',data);
                        window.location.href="../../views/home.html";

                    }
                }
                ).error("GTOx2!");
    };
    this.testAction = function () {
        console.log("function test!");
    }

    this.signupAction = function () {
        console.log(document.getElementById("login2").value);
        console.log(document.getElementById("pwd2").value);
        console.log(document.getElementById("pwd2-repeat").value);
        //$('#myModal').modal('show');
        if (document.getElementById("pwd2").value!=document.getElementById("pwd2-repeat").value) {
            self.scope.modalHeader = "Błąd";
            self.scope.modalBody = "Hasła nie są takie same.";
            $('#myModal').modal('show');
            return false;
        }
        //var crypto = require('./aes.js');
        var text = "The quick brown fox jumps over the lazy dog.";
        var secret = "René Über";
        var encrypted = CryptoJS.AES.encrypt(text, secret);
        encrypted = encrypted.toString();
        console.log("Cipher text: " + encrypted);
        self.scope.formData = {
            login:document.getElementById("login2").value,
            pwd:document.getElementById("pwd2").value,
            firstName:document.getElementById("firstName").value,
            lastName:document.getElementById("lastName").value,
            email:document.getElementById("email").value
        };
        console.log(self.scope.formData);
        $http.post("/signup", self.scope.formData).success(
            function (data) {
                self.scope.modalHeader = "Sukces!";
                self.scope.modalBody = "Rejestracja przebiegła pomyślnie. Możesz się teraz zalogować!";
                $('#myModal').modal('show');
                $("#signingUp").trigger('reset');
                return false;
            }
        ).error(
            function (data) {
                self.scope.modalHeader = "Błąd";
                self.scope.modalBody = data;
                $('#myModal').modal('show');
                return false;
            }
        );

    };

    this.signUp = function () {
        self.scope.loginForm = false;
        self.scope.singupForm = true;
    }

    this.LoginIn = function () {
        self.scope.singupForm = false;
        self.scope.loginForm= true;
    }
}]);