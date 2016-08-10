angular.module("myApp").controller("core.log", ['$scope','$uibModal','$http','toaster','confirmDialog','$ocLazyLoad',function($scope,$uibModal,$http,toaster,confirmDialog,$ocLazyLoad){
    $scope.name="xieyaxiong";
    $scope.columns=[
        {name:"编号",width:"5%",columnName:"id"},
        {name:"姓名",width:"10%",columnName:"username"},
		{name:"访问功能",width:"10%",columnName:"modelname"},
        {name:"访问路径",width:"30%",columnName:"accessurl"},
        {name:"时间",width:"20%",columnName:"createtime"}
    ];
    
    $scope.pageOption={"currentPage":1,"pageSize":13};
    $scope.pageChanged = function() {
    	refreshTable();
    };
    $scope.selectRowId=0;
    
 
    
    $scope.queryTable=function(){
    	$scope.pageOption.currentPage=1;
    	refreshTable();
    }
    
    function refreshTable(){
    	$http.post('core/loadLogPage.do',$scope.pageOption).success(function(data) {
    		$scope.pageOption.currentPage=data.currentPage;
    		$scope.page=data;
        　　});
    }
    refreshTable();

}]);