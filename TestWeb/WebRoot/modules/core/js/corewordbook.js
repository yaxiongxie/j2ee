angular.module("myApp").controller("core.wordbook", ['$scope','$uibModal','$http','toaster','confirmDialog','uploadFiles','$ocLazyLoad',function($scope,$uibModal,$http,toaster,confirmDialog,uploadFiles,$ocLazyLoad){
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
        {name:"编号",width:"5%",columnName:"id"},
        {name:"名称",width:"15%",columnName:"name"},
        {name:"编号",width:"10%",columnName:"code"},
        {name:"排序",width:"15%",columnName:"ordernum"},
        {name:"修改人",width:"15%",columnName:"personname"},
		{name:"修改日期",width:"15%",columnName:"createtime"}
    ];
	$scope.operateWidth="10%";
    $scope.operations=[
        {name:"editT",title:"编辑",imgClass:"fa fa-pencil-square-o fa-lg"},
        {name:"deleteT",title:"删除",imgClass:"fa fa-times fa-lg"}
	];
    $scope.pageOption={"currentPage":1,"pageSize":12};
    $scope.pageChanged = function() {
    	refreshTable();
    };
    $scope.deleteBatch=function(){
    	confirmDialog("删除字典","确定删除吗？",function () {
        	var jsonData={ids:$scope.selected.join(",")};
        	$http.post('core/deleteWordbookByIds.do',jsonData).success(function(){
        		refreshTable();
        	});
        });
    }
    
    $scope.clickOperate=function(id,type){
    	if(type=="deleteT"){
    		confirmDialog("删除字典","确定删除吗？",function () {
    			deleteWordbook(id);
            });
    	}else{
    		editWordbook(id);
    	}
    }
    
    function editWordbook(id){
    	var selectnode=$('#tree').treeview('getSelected');
    	var jsonData={"id":id};
    	$http.post('core/loadWordbook.do',jsonData).success(function(data){
    		data.department=selectnode[0].text;
    		var modalInstance = $uibModal.open({
                templateUrl: 'modules/core/addWordbook.html',
                controller: 'core.addWordbook',
                size: "",
                resolve: {
                    obj: function () {
                        return data;
                    },
                    loadMyCtrl:function(){
                        return $ocLazyLoad.load("modules/core/js/addwordbook.js");
                    }
                }
            });
            modalInstance.result.then(function (obj) {
	        	obj.categoryId=selectnode[0].id;
	        	$http.post('core/saveWordbook.do',obj);
            });
    	});
    }
    
    function deleteWordbook(id){
    	var jsonData={"id":id};
    	$http.post('core/deleteWordbook.do',jsonData).success(function(){
    		refreshTable();
    	});
    }
    
    $scope.queryTable=function(){
    	$scope.pageOption.currentPage=1;
    	refreshTable();
    }
    
    $scope.addWordbook=function(){
    	var selectnode=$('#tree').treeview('getSelected');
    	var modalInstance = $uibModal.open({
            templateUrl: 'modules/core/addWordbook.html',
            controller: 'core.addWordbook',
            size: "",
            resolve: {
                obj: function () {
                    return {department:selectnode[0].text};
                },
                loadMyCtrl:function(){
                    return $ocLazyLoad.load("modules/core/js/addwordbook.js");
                }
            }
        });
        modalInstance.result.then(function (obj) {
        	obj.categoryId=selectnode[0].id;
        	$http.post('core/saveWordbook.do',obj);
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
                    return {"title":"新增分类","content":""}
                },
                loadMyCtrl:function(){
                    return $ocLazyLoad.load("common/js/inputname.js");
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
                    return {"title":"编辑分类","content":selectnode[0].text}
                },
                loadMyCtrl:function(){
                    return $ocLazyLoad.load("common/js/inputname.js");
                }
            }
        });
        modalInstance.result.then(function (obj) {
        	var treeObj=selectnode[0];
            saveCategory(selectnode[0].id,obj,selectnode[0].pid);
        });
    }
    
    $scope.deleteCategory=function(){
    	confirmDialog("删除分类","确定删除吗？",function () {
    		deleteCategory();
        });
    }
    
    function saveCategory(id,name,pid){
    	var selectnode=$('#tree').treeview('getSelected');
    	var jsonData={"id":id,"name":name,"pid":pid};
    	$http.post('core/saveWordbookCategory.do',jsonData).success(function(){
    	    refreshTree();
    	});
    }
    function deleteCategory(){
    	var selectnode=$('#tree').treeview('getSelected');
    	var jsonData={"id":selectnode[0].id};
    	$http.post('core/deleteWordbookCategory.do',jsonData).success(function(){
    	    refreshTree();
    	});
    }

    function refreshTree(){
    	$http.get('core/loadWordbookCategoryAll.do').success(function(data) {
        　　　　$('#tree').treeview({color: "#428bca",data: data,showBorder: false,
				onNodeSelected: function(event, data) {
				  	$scope.pageOption.categoryid=data.id;
					$scope.pageOption.currentPage=1;
					refreshTable();
				  }
		  		});
        　　});
    }
    function refreshTable(){
    	$http.post('core/loadWordbookPage.do',$scope.pageOption).success(function(data) {
    		$scope.pageOption.currentPage=data.currentPage;
    		$scope.page=data;
        　　});
    }
    refreshTree();
    refreshTable();
    
}]);