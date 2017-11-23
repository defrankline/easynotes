function BrandCtrl ($scope,DataModel) {
    $scope.title = "Brands";
    $scope.items = DataModel;
};

BrandCtrl .resolve = {
    DataModel: function (BrandService,$timeout, $q) {
        var deferred = $q.defer();
        $timeout(function () {
            BrandService.query(function (data) {
                deferred.resolve(data);
            });
        }, 900);
        return deferred.promise;
    }
};