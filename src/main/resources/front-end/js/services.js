var services = angular.module('services', ['ngResource']);

services.factory('AccountTypeService', ['$resource', function ($resource) {
    return $resource('/api/accountTypes/:id', {}, {
        fetchAll: {method: 'GET', url: '/api/accountTypes/fetchAll'},
        paginated: {method: 'GET', url: '/api/accountTypes/paginated'},
        update: {method: 'PUT', params: {id: '@id'}},
        trashed: {method: 'GET', url: '/api/accountTypes/trashed'},
        restore: {method: 'GET', url: '/api/accountTypes/:id/restore'},
        permanentDelete: {method: 'GET', url: '/api/accountTypes/:id/permanentDelete'},
        emptyTrash: {method: 'GET', url: '/api/accountTypes/emptyTrash'},
        accountSubTypes: {method: 'GET', url: '/api/accountTypes/accountSubTypes'},
        chartOfAccounts: {method: 'GET', url: '/api/accountTypes/chartOfAccounts'},
    });
}]);

services.factory('PingService', function ($resource) {
    //return $resource('/api/auth/ping', {}, {ping: {method: 'GET'}});
    return 1;
});
