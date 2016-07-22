angular.module("myApp").controller("document", ['$scope','$uibModal','$http','toaster','confirmDialog','uploadFiles',function($scope,$uibModal,$http,toaster,confirmDialog,uploadFiles){
    $scope.name="xieyaxiong";
    $scope.selected=[];
    $scope.updateSelection = function($event, id){
    	var checkbox = $event.target;
    	if(id=='all'){
    		 $scope.selected=[];
    		$("input[name='cbname']").each(function(){
    			$(this).prop("checked",checkbox.checked); 
    			if(checkbox.checked){
    				$scope.selected.push($(this).attr("cid"));
    			}
    		})
    		return ;
    	}
    	var action = (checkbox.checked?'add':'remove');
    	if(action == 'add' && $scope.selected.indexOf(id) == -1){
            $scope.selected.push(id);
        }
        if(action == 'remove' && $scope.selected.indexOf(id)!=-1){
            var idx = $scope.selected.indexOf(id);
            $scope.selected.splice(idx,1);
        }
    }
    $scope.columns=[
        {name:"id",width:"5%",columnName:"id"},
        {name:"doctitle",width:"15%",columnName:"doctitle"},
        {name:"docsize",width:"5%",columnName:"docsize"},
        {name:"doccontent",width:"40%",columnName:"doccontent"},
        {name:"createtime",width:"15%",columnName:"createtime"}
    ];
    $scope.operations=[
        {name:"editT",title:"编辑",imgClass:"fa fa-pencil-square-o"},
        {name:"deleteT",title:"删除",imgClass:"fa fa-times"}
	];
    $scope.pageOption={"currentPage":1,"pageSize":12,"categoryid":0,"queryString":""};
    $scope.pageChanged = function() {
    	refreshTable();
    };
    $scope.deleteBatch=function(){
    	confirmDialog("删除人员","确定删除吗？",function () {
        	var jsonData={ids:$scope.selected.join(",")};
        	$http.post('core/deletePersonByIds.do',jsonData).success(function(){
        		refreshTable();
        	});
        });
    }
    
    $scope.clickOperate=function(id,type){
    	if(type=="deleteT"){
    		confirmDialog("删除人员","确定删除吗？",function () {
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
    
    $scope.addDocument=function(){
    	var selectnode=$('#tree').treeview('getSelected');
    	var modalInstance = $uibModal.open({
            templateUrl: 'modules/document/addDocument.html',
            controller: 'document.addDocument',
            size: "lg",
            resolve: {
                obj: function () {
                    return {categoryid:selectnode[0].id};
                },
                loadMyCtrl:function($ocLazyLoad){
                    return $ocLazyLoad.load("modules/document/js/addDocument.js");
                }
            }
        });
    }
    
    $scope.addCategory=function(){
    	var selectnode=$('#tree').treeview('getSelected');
    	var modalInstance = $uibModal.open({
            templateUrl: 'common/inputName.html',
            controller: 'common.inputName',
            size: "sm",
            resolve: {
                obj: function () {
                    return {"title":"新建文件夹","content":""}
                },
                loadMyCtrl:function($ocLazyLoad){
                    return $ocLazyLoad.load("common/js/inputName.js");
                }
            }
        });
        modalInstance.result.then(function (obj) {
            saveCategory(0,obj,selectnode[0].id);
        });
    }
    $scope.editCategory=function(){
    	var selectnode=$('#tree').treeview('getSelected');
    	var modalInstance = $uibModal.open({
            templateUrl: 'common/inputName.html',
            controller: 'common.inputName',
            size: "sm",
            resolve: {
                obj: function () {
                    return {"title":"修改名称","content":selectnode[0].text}
                },
                loadMyCtrl:function($ocLazyLoad){
                    return $ocLazyLoad.load("common/js/inputName.js");
                }
            }
        });
        modalInstance.result.then(function (obj) {
        	var treeObj=selectnode[0];
            saveCategory(selectnode[0].id,obj,selectnode[0].pid);
        });
    }
    
    $scope.deleteCategory=function(){
    	confirmDialog("删除文件夹","确定删除吗？",function () {
    		deleteCategory();
        });
    }
    
    function saveCategory(id,name,pid){
    	var selectnode=$('#tree').treeview('getSelected');
    	var jsonData={"id":id,"name":name,"pid":pid};
    	$http.post('document/saveCategory.do',jsonData).success(function(){
    	    refreshTree();
    	});
    }
    function deleteCategory(){
    	var selectnode=$('#tree').treeview('getSelected');
    	var jsonData={"id":selectnode[0].id};
    	$http.post('document/deleteCategory.do',jsonData).success(function(){
    	    refreshTree();
    	});
    }

    function refreshTree(){
    	$http.get('document/loadCategory.do').success(function(data) {
        　　　　$('#tree').treeview({color: "#428bca",data: data,showBorder: false});
        　　});
    }
    function refreshTable(){
    	$http.post('document/loadDocumentPage.do',$scope.pageOption).success(function(data) {
    		$scope.pageOption.currentPage=data.currentPage;
    		$scope.page=data;
        　　});
    }
    refreshTree();
    refreshTable();
    
}]);