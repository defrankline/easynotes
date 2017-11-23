var services = angular.module('services', ['ngResource']);

var API_URL = "http://127.0.0.1:9000";

services.factory('BrandService', ['$resource', function ($resource) {
    return $resource(API_URL+'/api/brands/:id', {}, {
        fetchAll: {method: 'GET', url: API_URL+'/api/brands'},
        paginated: {method: 'GET', url: API_URL+'/api/brands/paginated'},
        update: {method: 'PUT', params: {id: '@id'}},
    });
}]);

services.factory('PingService', function ($resource) {
    //return $resource('/api/auth/ping', {}, {ping: {method: 'GET'}});
    return 1;
});
