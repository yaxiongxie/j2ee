angular.module("myApp").controller("core.role", ['$scope','$uibModal','$http','toaster','confirmDialog',function($scope,$uibModal,$http,toaster,confirmDialog){
    $scope.name="xieyaxiong";
    $scope.columns=[
        {name:"id",width:"5%",columnName:"id"},
        {name:"name",width:"30%",columnName:"name"},
        {name:"pcount",width:"5%",columnName:"pcount"},
        {name:"names",width:"45%",columnName:"names"}
    ];
    $scope.operations=[
        {name:"addT",title:"添加人员",imgClass:"fa fa-plus"},
        {name:"editT",title:"编辑",imgClass:"fa fa-pencil-square-o"},
        {name:"deleteT",title:"删除",imgClass:"fa fa-times"}
	];
    $scope.pageOption={"currentPage":1,"pageSize":15};
    $scope.pageChanged = function() {
    	refreshTable();
    };
    $scope.selectRowId=0;
    
    $scope.clickOperate=function(id,type){
    	$scope.selectRowId=id;
    	if(type=="deleteT"){
    		confirmDialog("删除角色","确定删除吗？",function(){
    			deleteRole(id);
    		})
    	}else if(type=="addT"){
    		addAuth(id);
    	}else{
    		editRole(id);
    	}
    }
    
    function addAuth(id){
    	$http.post('core/role/getDepartmentTree.do',{id:id}).success(function(data){
    		var modalInstance = $uibModal.open({
                templateUrl: 'modules/core/addAuth.html',
                controller: 'core.addAuth',
                size: "",
                resolve: {
                    obj: function () {
                        return data;
                    },
                    loadMyCtrl:function($ocLazyLoad){
                        return $ocLazyLoad.load("modules/core/js/addAuth.js");
                    }
                }
            });

            modalInstance.result.then(function (obj) {
            	$http.post('core/addAuth.do',{id:$scope.selectRowId,ps:obj}).success(function(data){
            		refreshTable();
            	});
            });
    	});
    }
    
    function editRole(id){
    	var jsonData={"id":id};
    	$http.post('core/loadRole.do',jsonData).success(function(data){
    		var modalInstance = $uibModal.open({
                templateUrl: 'modules/core/addRole.html',
                controller: 'core.addRole',
                size: "",
                resolve: {
                    obj: function () {
                        return data;
                    },
                    loadMyCtrl:function($ocLazyLoad){
                        return $ocLazyLoad.load("modules/core/js/addRole.js");
                    }
                }
            });
            modalInstance.result.then(function (obj) {
            	$http.post('core/saveRole.do',obj).success(function(){
            		refreshTable();
            	});
            });
    	});
    }
    
    function deleteRole(id){
    	var jsonData={"id":id};
    	$http.post('core/deleteRole.do',jsonData).success(function(){
    		refreshTable();
    	});
    }
    
    $scope.queryTable=function(){
    	$scope.pageOption.currentPage=1;
    	refreshTable();
    }
    
    $scope.addRole=function(){
    	$http.get('core/getAuthAll.do').success(function(data){
    		var modalInstance = $uibModal.open({
                templateUrl: 'modules/core/addRole.html',
                controller: 'core.addRole',
                size: "",
                resolve: {
                    obj: function () {
                        return {authAll:data,id:0};
                    },
                    loadMyCtrl:function($ocLazyLoad){
                        return $ocLazyLoad.load("modules/core/js/addRole.js");
                    }
                }
            });
            modalInstance.result.then(function (obj) {
            	$http.post('core/saveRole.do',obj);
            });
    	});
    	
    }
    function refreshTable(){
    	$http.post('core/loadRolePage.do',$scope.pageOption).success(function(data) {
    		$scope.pageOption.currentPage=data.currentPage;
    		$scope.page=data;
        　　});
    }
    refreshTable();

}]);