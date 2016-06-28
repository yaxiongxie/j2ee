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

  

}]);