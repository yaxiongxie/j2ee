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
        {name:"编号",width:"5%",columnName:"id"},
        {name:"标题",width:"20%",columnName:"doctitle"},
        {name:"大小",width:"5%",columnName:"docsize"},
        {name:"内容摘要",width:"50%",columnName:"doccontent"},
        {name:"上传时间",width:"12%",columnName:"createtime"}
    ];
	$scope.operateWidth="8%";
    $scope.operations=[
        {name:"downloadT",title:"下载",url:"document/downloadDocument.do",imgClass:"fa fa-download fa-lg"},
        {name:"deleteT",title:"删除",imgClass:"fa fa-times fa-lg"}
	];
    $scope.pageOption={"currentPage":1,"pageSize":12,"categoryid":0,"queryString":""};
    $scope.pageChanged = function() {
    	refreshTable();
    };
    $scope.deleteBatch=function(){
    	confirmDialog("删除文档","确定删除吗？",function () {
        	var jsonData={ids:$scope.selected.join(",")};
        	$http.post('document/deleteDocumentByIds.do',jsonData).success(function(){
        		refreshTable();
        	});
        });
    }
    
    $scope.clickOperate=function(id,type){
    	if(type=="deleteT"){
    		confirmDialog("删除文档","确定删除吗？",function () {
    			deleteDocument(id);
            });
    	}
    }
    
    function deleteDocument(id){
    	var jsonData={"id":id};
    	$http.post('document/deleteDocument.do',jsonData).success(function(){
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
//                    return {categoryid:selectnode[0].id};
                	return {categoryid:1};
                },
                loadMyCtrl:function($ocLazyLoad){
                    return $ocLazyLoad.load("modules/document/js/adddocument.js");
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
                    return {"title":"修改名称","content":selectnode[0].text}
                },
                loadMyCtrl:function($ocLazyLoad){
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
    	$("#leftmenu").hide();
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
    
    
    $scope.$on("enterFun", function (event, dom) {
        $scope.$apply(function () {
        	if(dom.id=='search'){
        		$scope.queryTable();
        	}
        });
    });
    
}]);