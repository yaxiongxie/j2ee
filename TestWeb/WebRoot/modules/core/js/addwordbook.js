angular.module('myApp').controller('core.addWordbook',['$scope','$uibModalInstance','obj', function ($scope, $uibModalInstance, obj) {
	
    $scope.wordbook=obj;

    $scope.ok = function () {
        $uibModalInstance.close($scope.wordbook);
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

}]);