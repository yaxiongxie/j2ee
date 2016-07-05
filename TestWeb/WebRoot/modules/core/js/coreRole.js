angular.module("myApp").controller("core.role", ['$scope','$uibModal','$http','toaster',function($scope,$uibModal,$http,toaster){
    $scope.name="xieyaxiong";
    $scope.columns=[
        {name:"id",width:"5%",columnName:"id"},
        {name:"name",width:"30%",columnName:"name"},
        {name:"pcount",width:"5%",columnName:"pcount"},
        {name:"names",width:"45%",columnName:"names"}
    ];
    $scope.operations=[
        {name:"editT",title:"编辑",imgClass:"fa fa-pencil-square-o"},
        {name:"deleteT",title:"删除",imgClass:"fa fa-times"}
	];
    $scope.pageOption={"currentPage":1,"pageSize":15};
    $scope.pageChanged = function() {
    	refreshTable();
    };
    $scope.clickOperate=function(id,type){
    	if(type=="deleteT"){
    		var selectnode=$('#tree').treeview('getSelected');
        	var modalInstance = $uibModal.open({
                templateUrl: 'common/confirmDialog.html',
                controller: 'common.confirmDialog',
                size: "sm",
                resolve: {
                    obj: function () {
                        return {"title":"删除人员","content":"确定删除吗？"}
                    },
                    loadMyCtrl:function($ocLazyLoad){
                        return $ocLazyLoad.load("common/js/confirmDialog.js");
                    }
                }
            });
            modalInstance.result.then(function () {
            	deletePerson(id);
            });
    	}else{
    		editPerson(id);
    	}
    }
    
    function editPerson(id){
    	var selectnode=$('#tree').treeview('getSelected');
    	var jsonData={"id":id};
    	$http.post('core/loadPerson.do',jsonData).success(function(data){
    		data.department=selectnode[0].text;
    		var modalInstance = $uibModal.open({
                templateUrl: 'modules/core/addPerson.html',
                controller: 'core.addPerson',
                size: "",
                resolve: {
                    obj: function () {
                        return data;
                    },
                    loadMyCtrl:function($ocLazyLoad){
                        return $ocLazyLoad.load("modules/core/js/addPerson.js");
                    }
                }
            });
            modalInstance.result.then(function (obj) {
            	obj.departmentId=selectnode[0].id;
            	$http.post('core/savePerson.do',obj).success(function(){
            		toaster.pop('success', "保存成功");
            		refreshTable();
            	});
            });
    	});
    }
    
    function deletePerson(id){
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
                        return {authAll:data};
                    },
                    loadMyCtrl:function($ocLazyLoad){
                        return $ocLazyLoad.load("modules/core/js/addRole.js");
                    }
                }
            });
            modalInstance.result.then(function (obj) {
            	obj.departmentId=selectnode[0].id;
            	$http.post('core/saveRole.do',obj).success(function(){
            		toaster.pop('success', "保存成功");
            	});
            });
    	});
    	
    }
    
    $scope.addDept=function(){
    	var selectnode=$('#tree').treeview('getSelected');
    	var modalInstance = $uibModal.open({
            templateUrl: 'common/inputName.html',
            controller: 'common.inputName',
            size: "sm",
            resolve: {
                obj: function () {
                    return {"title":"新增部门","content":""}
                },
                loadMyCtrl:function($ocLazyLoad){
                    return $ocLazyLoad.load("common/js/inputName.js");
                }
            }
        });
        modalInstance.result.then(function (obj) {
            saveDept(0,obj,selectnode[0].id);
        });
    }
    $scope.editDept=function(){
    	var selectnode=$('#tree').treeview('getSelected');
    	var modalInstance = $uibModal.open({
            templateUrl: 'common/inputName.html',
            controller: 'common.inputName',
            size: "sm",
            resolve: {
                obj: function () {
                    return {"title":"编辑部门","content":selectnode[0].text}
                },
                loadMyCtrl:function($ocLazyLoad){
                    return $ocLazyLoad.load("common/js/inputName.js");
                }
            }
        });
        modalInstance.result.then(function (obj) {
        	var treeObj=selectnode[0];
            saveDept(selectnode[0].id,obj,selectnode[0].pid);
        });
    }
    
    $scope.deleteDept=function(){
    	var selectnode=$('#tree').treeview('getSelected');
    	var modalInstance = $uibModal.open({
            templateUrl: 'common/confirmDialog.html',
            controller: 'common.confirmDialog',
            size: "sm",
            resolve: {
                obj: function () {
                    return {"title":"删除部门","content":"确定删除吗？"}
                },
                loadMyCtrl:function($ocLazyLoad){
                    return $ocLazyLoad.load("common/js/confirmDialog.js");
                }
            }
        });
        modalInstance.result.then(function () {
        	deleteDept();
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