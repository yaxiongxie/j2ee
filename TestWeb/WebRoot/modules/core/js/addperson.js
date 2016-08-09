angular.module('myApp').controller('core.addPerson',['$scope','$uibModalInstance','obj', function ($scope, $uibModalInstance, obj) {
	
    $scope.person=obj;

    $scope.ok = function () {
    	$scope.person.fileObj={model: {relateId:1,tableName:"coreperson"}, files: $scope.files };
        $uibModalInstance.close($scope.person);
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };

    $scope.files = [];
    $scope.$on("fileSelected", function (event, args) {
        $scope.$apply(function () {
        	$scope.files = [];
            $scope.files.push(args.file);
        });
    });
    
}]);