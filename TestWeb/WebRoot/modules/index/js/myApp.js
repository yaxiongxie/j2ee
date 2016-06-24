/**
 * Created by Administrator on 2016/6/16.
 */

(function (ng) {
    'use strict';
    var app = ng.module('ngLoadScript', []);
    app.directive('script', function() {
        return {
            restrict: 'E',
            scope: false,
            link: function(scope, elem, attr)
            {
                if (attr.type==='text/javascript-lazy')
                {
                    var s = document.createElement("script");
                    s.type = "text/javascript";
                    var src = elem.attr('src');
                    if(src!==undefined)
                    {
                        s.src = src;
                    }
                    else
                    {
                        var code = elem.text();
                        s.text = code;
                    }
                    document.head.appendChild(s);
                    elem.remove();
                }
            }
        };
    });
}(angular));

var myApp=angular.module('myApp',['ngLoadScript','ui.router','angular-loading-bar', 'ngAnimate','oc.lazyLoad','ui.bootstrap','angularBootstrapNavTree','toaster']);
myApp.directive('myTable', function() {
    return {
        restrict: 'E',
        templateUrl: 'my-table.html'
    };
});

myApp.directive('fileUpload', function () {
    return {
        scope: true,        //create a new scope
        link: function (scope, el, attrs) {
            el.bind('change', function (event) {
                var files = event.target.files;
                //iterate files since 'multiple' may be specified on the element
                for (var i = 0;i<files.length;i++) {
                    //emit event upward
                    scope.$emit("fileSelected", { file: files[i] });
                }
            });
        }
    };
});

angular.module('myApp').controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, items) {

    $scope.items = items;
    $scope.selected = {
        item: $scope.items[0]
    };

    $scope.ok = function () {
        $uibModalInstance.close($scope.selected.item);
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});

myApp.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.parentSelector = '#loading-bar-container';
    cfpLoadingBarProvider.spinnerTemplate = '<div><span class="fa fa-spinner">Custom Loading Message...</div>';
  }])


myApp.config(function($stateProvider, $urlRouterProvider) {
    //
    $urlRouterProvider.otherwise("/state1");
    //
    $stateProvider
        .state('state1', {
            url: "/state1",
            templateUrl: "content.html"
        })
        
        .state('state2', {
        	url: "/state2",
        	templateUrl: "content.html",
            controller:'testControl',
            resolve:{
                loadMyCtrl:function($ocLazyLoad){
                    return $ocLazyLoad.load("js/control.js");
                }
            }
        })
        .state('table', {
            url: "/table",
            templateUrl: "partials/state2.list.html",
            controller: function($scope) {
                $scope.things = ["A", "Set", "Of", "Things"];
            }
        });
});