var myApp = angular.module('myApp', ['ui.router','services', 'ngResource', 'angular.filter', 'ngAria', 'ngAnimate',
    'ngMessages', 'ngLoadingSpinner', 'ui.bootstrap', 'ngFileUpload','ngCookies']);

myApp.config(function($stateProvider,$urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    var homeState = {
        name: 'home',
        url: '/home',
        template: '<h3>Home</h3>'
    };

    var aboutState = {
        name: 'about',
        url: '/about',
        template: '<h3>Its the UI-Router hello world app!</h3>'
    };

    $stateProvider.state(homeState);
    $stateProvider.state(aboutState);

    $stateProvider.state("otherwise", {url: '/home'});
});


myApp.run(function ($window, $rootScope, $interval, PingService) {
    var ping = function () {
        PingService.ping({noLoader: true}, function (status) {
            $rootScope.online = true;
        }, function (error) {
            $rootScope.online = false;
        });
    };
    $rootScope.pageLoaded = true;
    $rootScope.online = navigator.onLine;
    $window.addEventListener("offline", function () {
        $rootScope.$apply(function () {
            $rootScope.online = false;
        });
    }, false);

    $window.addEventListener("online", function () {
        $rootScope.$apply(function () {
            $rootScope.online = true;
        });
    }, false);
});



myApp.directive("perPage", function () {
    return {
        restrict: "E",
        templateUrl: '/templates/per-page.html',
        require: 'ngModel',
        link: function (scope, element, attrs, ctrl) {
            scope.perPageNumberChange = function (perPageNumber) {
                ctrl.$setViewValue(perPageNumber);
            };
            scope.perPageOptions = [10,25, 50, 100, 250, 500, 1000];
            scope.perPageNumber = scope.perPageOptions[0];
        }
    };
});
myApp.directive("totalPages", function () {
    return {
        restrict: "E",
        template: '<span class="total-pages">Page: {{currentPage}} of {{totalPages(totalItems/perPage)}}</span>',
        link: function (scope, element, attrs, ctrl) {
            scope.totalPages = function (totalPages) {
                pages = Math.ceil(totalPages);
                return pages;
            };
            scope.currentPage = attrs.currentpage;
            scope.totalItems = attrs.totalitems;
            console.log(attrs.totalitems);
            scope.perPage = attrs.perpage;
        }
    };
});
myApp.factory("ConfirmDialogService", ["$q", "$uibModal", function ($q, $uibModal) {
    var _showConfirmDialog = function (title, message) {
        var defer = $q.defer();

        var modalInstance = $uibModal.open({
            animation: true,
            size: "lg",
            backdrop: false,
            templateUrl: '/templates/confirm-dialog.html',
            controller: function ($scope, $uibModalInstance) {
                $scope.title = title;
                $scope.message = message;

                $scope.ok = function () {
                    $uibModalInstance.close();
                    defer.resolve();
                };

                $scope.cancel = function () {
                    $uibModalInstance.dismiss();
                    defer.reject();
                };
            }
        });

        return defer.promise;
    };

    return {
        showConfirmDialog: _showConfirmDialog
    };

}]);
myApp.directive('pagetitle', function () {
    return {
        restrict: 'E',
        scope: {
            title: '@',
        },
        templateUrl: '/templates/pagetitle.html'
    };
});