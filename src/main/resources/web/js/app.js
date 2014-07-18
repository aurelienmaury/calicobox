'use strict';

var bus_name = 'org.rtnp.calico';
var gwez = angular.module('gwez', ['ngRoute']);

gwez.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/', { templateUrl: 'ng/home.html' });
    $routeProvider.otherwise({redirectTo: '/'});
}]);
