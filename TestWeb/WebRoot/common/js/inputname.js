angular.module('myApp').controller('common.inputName', function ($scope, $uibModalInstance, obj) {

    $scope.obj = obj;

    $scope.ok = function () {
        $uibModalInstance.close($scope.obj.content);
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});