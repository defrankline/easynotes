var services = angular.module('services', ['ngResource']);

var API_URL = "http://127.0.0.1:9000";

services.factory('PingService', function ($resource) {
    //return $resource('/api/auth/ping', {}, {ping: {method: 'GET'}});
    return 1;
});

services.factory('BrandService', ['$resource', function ($resource) {
    return $resource(API_URL+'/api/brands/:id', {}, {
        update: {method: 'PUT', params: {id: '@id'}},
        paginated: {method: 'GET',params: {page: '@page',perPage:'@perPage'}, url: API_URL+'/api/brands/paginated'},
    });
}]);

services.factory('ProductCategoryService', ['$resource', function ($resource) {
    return $resource(API_URL+'/api/product-categories/:id', {}, {
        update: {method: 'PUT', params: {id: '@id'}},
        paginated: {method: 'GET',params: {page: '@page',perPage:'@perPage'}, url: API_URL+'/api/product-categories/paginated'},
    });
}]);

services.factory('ProductService', ['$resource', function ($resource) {
    return $resource(API_URL+'/api/products/:id', {}, {
        update: {method: 'PUT', params: {id: '@id'}},
        paginated: {method: 'GET',params: {page: '@page',perPage:'@perPage'}, url: API_URL+'/api/products/paginated'},
    });
}]);
