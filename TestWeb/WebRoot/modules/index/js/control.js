angular.module("myApp").controller("testControl", ['$scope','$uibModal','$http','toaster',function($scope,$uibModal,$http,toaster){
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
                    {id:"2",mac:"28-d9-8a-05-10-53",operatetype:"add",operatestatus:"open",operatecontent:"content",createtime:"2017"}
                ];
    
    $scope.pageChanged = function() {
    };
    $scope.maxSize = 6;
    $scope.bigTotalItems = 586;
    $scope.bigCurrentPage = 1;

    $scope.example_treedata= [{
        label: 'Languages',
        children: ['Jade','Less','Coffeescript']
    }];

    $scope.my_tree_handler=function(branch){
    }
    $scope.items = ['item1', 'item2', 'item3'];
    $scope.open = function (size) {

        var modalInstance = $uibModal.open({
            templateUrl: 'modal.html',
            controller: 'ModalInstanceCtrl',
            size: size,
            resolve: {
                items: function () {
                    return $scope.items;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $scope.selected = selectedItem;
        });
    };

    //a simple model to bind to and send to the server
    $scope.model = {
        name: "",
        comments: ""
    };

    //an array of files selected
    $scope.files = [];

    //listen for the file selected event
    $scope.$on("fileSelected", function (event, args) {
        $scope.$apply(function () {
            //add the file object to the scope's files collection
            $scope.files.push(args.file);
        });
    });

    //the save method
    $scope.save = function() {
        $http({
            method: 'POST',
            url: "../../updateFiles.do",
            //IMPORTANT!!! You might think this should be set to 'multipart/form-data'
            // but this is not true because when we are sending up files the request
            // needs to include a 'boundary' parameter which identifies the boundary
            // name between parts in this multi-part request and setting the Content-type
            // manually will not set this boundary parameter. For whatever reason,
            // setting the Content-type to 'false' will force the request to automatically
            // populate the headers properly including the boundary parameter.
            headers: { 'Content-Type': undefined },
            //This method will allow us to change how the data is sent up to the server
            // for which we'll need to encapsulate the model data in 'FormData'
            transformRequest: function (data) {
                var formData = new FormData();
                //need to convert our json object to a string version of json otherwise
                // the browser will do a 'toString()' on the object which will result
                // in the value '[Object object]' on the server.
                formData.append("jsonData", angular.toJson(data.model));
                //now add all of the assigned files
                for (var i = 0; i < data.files.length; i++) {
                    //add each file to the form data and iteratively name them
                    formData.append("file" + i, data.files[i]);
                }
                return formData;
            },
            //Create an object that contains the model and files which will be transformed
            // in the above transformRequest method
            data: { model: $scope.model, files: $scope.files }
        }).
        success(function (data, status, headers, config) {
        	alert(data.status);
            toaster.pop('success', "保存成功");
            alert("success!");
        }).
        error(function (data, status, headers, config) {
            alert("failed!");
            toaster.pop('success', "保存成功");
        });
    };

    var tree = [
        {
            text: "Parent 1",
            nodes: [
                {
                    text: "Child 1",
                    nodes: [
                        {
                            text: "Grandchild 1"
                        },
                        {
                            text: "Grandchild 2"
                        }
                    ]
                },
                {
                    text: "Child 2"
                }
            ]
        },
        {
            text: "Parent 2"
        },
        {
            text: "Parent 3"
        },
        {
            text: "Parent 4"
        },
        {
            text: "Parent 5"
        }
    ];

    $('#tree').treeview({color: "#428bca",data: tree,showCheckbox:true,showBorder: false});

}]);