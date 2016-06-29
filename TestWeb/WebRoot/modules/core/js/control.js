angular.module("myApp").controller("core.person", ['$scope','$uibModal','$http','toaster',function($scope,$uibModal,$http,toaster){
    $scope.name="xieyaxiong";
    $scope.columns=[
        {name:"编号",width:"5%",columnName:"id"},
        {name:"设备mac",width:"15%",columnName:"mac"},
        {name:"操作类型",width:"15%",columnName:"operatetype"},
        {name:"操作状态",width:"35%",columnName:"operatestatus"},
        {name:"操作内容",width:"15%",columnName:"operatecontent"},
        {name:"操作时间",width:"15%",columnName:"createtime"}
    ];
    
    $scope.tableData=[
                    {id:"1",mac:"28-d9-8a-05-10-52",operatetype:"add",operatestatus:"open",operatecontent:"content",createtime:"2016"},
                    {id:"2",mac:"28-d9-8a-05-10-53",operatetype:"add",operatestatus:"open",operatecontent:"content",createtime:"2017"},
                    {id:"1",mac:"28-d9-8a-05-10-52",operatetype:"add",operatestatus:"open",operatecontent:"content",createtime:"2016"},
                    {id:"2",mac:"28-d9-8a-05-10-53",operatetype:"add",operatestatus:"open",operatecontent:"content",createtime:"2017"},
                    {id:"1",mac:"28-d9-8a-05-10-52",operatetype:"add",operatestatus:"open",operatecontent:"content",createtime:"2016"},
                    {id:"2",mac:"28-d9-8a-05-10-53",operatetype:"add",operatestatus:"open",operatecontent:"content",createtime:"2017"},
                    {id:"1",mac:"28-d9-8a-05-10-52",operatetype:"add",operatestatus:"open",operatecontent:"content",createtime:"2016"},
                    {id:"2",mac:"28-d9-8a-05-10-53",operatetype:"add",operatestatus:"open",operatecontent:"content",createtime:"2017"}, {id:"1",mac:"28-d9-8a-05-10-52",operatetype:"add",operatestatus:"open",operatecontent:"content",createtime:"2016"},
                    {id:"2",mac:"28-d9-8a-05-10-53",operatetype:"add",operatestatus:"open",operatecontent:"content",createtime:"2017"},
                    {id:"1",mac:"28-d9-8a-05-10-52",operatetype:"add",operatestatus:"open",operatecontent:"content",createtime:"2016"},
                    {id:"2",mac:"28-d9-8a-05-10-53",operatetype:"add",operatestatus:"open",operatecontent:"content",createtime:"2017"}
                ];
    
    $scope.pageChanged = function() {
    };
    $scope.maxSize = 6;
    $scope.bigTotalItems = 586;
    $scope.bigCurrentPage = 1;
    
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
    refreshTree();

}]);