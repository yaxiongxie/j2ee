angular.module('myApp').controller('core.addAuth',['$scope','$uibModalInstance','obj', function ($scope, $uibModalInstance, obj) {
	
    $scope.obj=obj;
    
    $scope.ok = function () {
    	var selectnodes=$('#tree').treeview('getChecked');
    	var selectArr=[];
    	for(var i in selectnodes){
    		if(selectnodes[i].flag=="person"){
    			selectArr.push(selectnodes[i].id);
    		}
    	}
        $uibModalInstance.close(selectArr.join(","));
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    
    $uibModalInstance.rendered.then(function (obj) {
    	$('#tree').treeview({color: "#428bca",data: $scope.obj,showCheckbox:true,showBorder: false});
    });
}]);