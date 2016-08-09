angular.module('myApp').controller('document.addDocument', function ($scope, $uibModalInstance, obj) {
	
    $scope.document=obj;
    
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
    
    $scope.$on('ngLoadpageFinished', function (ngLoadpageFinishedEvent) {
    	 $('#file-0a').fileinput({
    	        language: 'zh',
    	        uploadUrl: 'document/addDocument.do?categoryid='+$scope.document.categoryid,
    	    });
    });
    
});