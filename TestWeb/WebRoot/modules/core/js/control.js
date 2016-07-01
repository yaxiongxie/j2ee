angular.module("myApp").controller("core.person", ['$scope','$uibModal','$http','toaster',function($scope,$uibModal,$http,toaster){
    $scope.name="xieyaxiong";
    $scope.columns=[
        {name:"id",width:"5%",columnName:"id"},
        {name:"realname",width:"15%",columnName:"realname"},
        {name:"sex",width:"15%",columnName:"sex"},
        {name:"email",width:"20%",columnName:"email"},
        {name:"telephone",width:"15%",columnName:"telephone"},
        {name:"status",width:"15%",columnName:"status"}
    ];
    $scope.operations=[
        {name:"editT",title:"编辑",imgClass:"fa fa-pencil-square-o"},
        {name:"deleteT",title:"删除",imgClass:"fa fa-times"}
	];
    $scope.pageOption={"currentPage":1,"pageSize":12};
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
    	$http.post('core/deletePerson.do',jsonData).success(function(){
    		refreshTable();
    	});
    }
    
    $scope.queryTable=function(){
    	$scope.pageOption.currentPage=1;
    	refreshTable();
    }
    
    $scope.addPerson=function(){
    	var selectnode=$('#tree').treeview('getSelected');
    	var modalInstance = $uibModal.open({
            templateUrl: 'modules/core/addPerson.html',
            controller: 'core.addPerson',
            size: "",
            resolve: {
                obj: function () {
                    return {department:selectnode[0].text};
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
    
    function saveDept(id,name,pid){
    	var selectnode=$('#tree').treeview('getSelected');
    	var jsonData={"id":id,"name":name,"pid":pid};
    	$http.post('core/saveDept.do',jsonData).success(function(){
    	    refreshTree();
    	});
    }
    function deleteDept(){
    	var selectnode=$('#tree').treeview('getSelected');
    	var jsonData={"id":selectnode[0].id};
    	$http.post('core/deleteDept.do',jsonData).success(function(){
    	    refreshTree();
    	});
    }

    function refreshTree(){
    	$http.get('core/loadDeptAll.do').success(function(data) {
        　　　　$('#tree').treeview({color: "#428bca",data: data,showBorder: false});
        　　});
    }
    function refreshTable(){
    	$http.post('core/loadPersonPage.do',$scope.pageOption).success(function(data) {
    		$scope.pageOption.currentPage=data.currentPage;
    		$scope.page=data;
        　　});
    }
    refreshTree();
    refreshTable();

}]);