angular.module('myApp').controller('core.addPerson', function ($scope, $uibModalInstance, obj) {
	
    $scope.person=obj;

    $scope.ok = function () {
        $uibModalInstance.close($scope.person);
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});