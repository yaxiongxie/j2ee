angular.module('myApp').controller('core.addRole', function ($scope, $uibModalInstance, obj) {
	
    $scope.role=obj;
    
    $scope.groups=[];
    $scope.selected = [];
    for(var i in obj.authAll){
    	var auth=obj.authAll[i];
    	var index=$.inArray(auth.groupname, $scope.groups);
    	if(index>=0){
    		continue;
    	}
    	$scope.groups.push(auth.groupname);
    }
    
    $scope.updateSelection = function($event, id){
    	var checkbox = $event.target;
    	var action = (checkbox.checked?'add':'remove');
    	if(action == 'add' && $scope.selected.indexOf(id) == -1){
            $scope.selected.push(id);
        }
        if(action == 'remove' && $scope.selected.indexOf(id)!=-1){
            var idx = $scope.selected.indexOf(id);
            $scope.selected.splice(idx,1);
        }
    }
   
    $scope.isSelected = function(id){
    	return $scope.selected.indexOf(id)>=0;
    }

    $scope.ok = function () {
        $uibModalInstance.close($scope.person);
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});