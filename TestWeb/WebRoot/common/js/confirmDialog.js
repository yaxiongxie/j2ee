angular.module('myApp').controller('common.confirmDialog', function ($scope, $uibModalInstance, obj) {

    $scope.obj = obj;

    $scope.ok = function () {
        $uibModalInstance.close();
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});