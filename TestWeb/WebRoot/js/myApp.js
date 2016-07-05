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

var myApp=angular.module('myApp',['ngLoadScript','ui.router','angular-loading-bar', 'ngAnimate','oc.lazyLoad','ui.bootstrap','angularBootstrapNavTree','toaster','ng-bootstrap-datepicker','elif'],function($httpProvider) {
	  // Use x-www-form-urlencoded Content-Type
	 $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
	    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

	  /**
	   * The workhorse; converts an object to x-www-form-urlencoded serialization.
	   * @param {Object} obj
	   * @return {String}
	   */ 
	  var param = function(obj) {
	    var query = '', name, value, fullSubName, subName, subValue, innerObj, i;

	    for(name in obj) {
	      value = obj[name];

	      if(value instanceof Array) {
	        for(i=0; i<value.length; ++i) {
	          subValue = value[i];
	          fullSubName = name + '[' + i + ']';
	          innerObj = {};
	          innerObj[fullSubName] = subValue;
	          query += param(innerObj) + '&';
	        }
	      }
	      else if(value instanceof Object) {
	        for(subName in value) {
	          subValue = value[subName];
	          fullSubName = name + '[' + subName + ']';
	          innerObj = {};
	          innerObj[fullSubName] = subValue;
	          query += param(innerObj) + '&';
	        }
	      }
	      else if(value !== undefined && value !== null)
	        query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
	    }

	    return query.length ? query.substr(0, query.length - 1) : query;
	  };

	  // Override $http service's default transformRequest
	  $httpProvider.defaults.transformRequest = [function(data) {
	    return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
	  }];
	});

myApp.directive('myTable', function() {
    return {
        restrict: 'E',
        templateUrl: 'common/my-table.html'
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

myApp.filter('sex', function() {
	  return function(input) {
	    if(input==1){
	    	return 'man';
	    }else{
	    	return 'woman';
	    }
	  };
	});
myApp.filter('personStatus', function() {
	  return function(input) {
	    if(input==1){
	    	return 'formal';
	    }else{
	    	return 'trial';
	    }
	  };
	})

//angular.module('myApp').controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, items) {
//
//    $scope.items = items;
//    $scope.selected = {
//        item: $scope.items[0]
//    };
//
//    $scope.ok = function () {
//        $uibModalInstance.close($scope.selected.item);
//    };
//
//    $scope.cancel = function () {
//        $uibModalInstance.dismiss('cancel');
//    };
//});

myApp.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.parentSelector = '#loading-bar-container';
    cfpLoadingBarProvider.spinnerTemplate = '<div><span class="fa fa-spinner">Custom Loading Message...</div>';
  }])


myApp.config(function($stateProvider, $urlRouterProvider) {
    //
    $urlRouterProvider.otherwise("/index");
    //
    $stateProvider
        .state('index', {
            url: "/index",
            templateUrl: "modules/index/newindex.html"
        })
        .state('core/person', {url: "/core/person",templateUrl: "modules/core/corePerson.html",controller:'core.person',resolve:{loadMyCtrl:function($ocLazyLoad){return $ocLazyLoad.load("modules/core/js/corePerson.js");}}})
         .state('core/role', {url: "/core/role",templateUrl: "modules/core/coreRole.html",controller:'core.role',resolve:{loadMyCtrl:function($ocLazyLoad){return $ocLazyLoad.load("modules/core/js/coreRole.js");}}})
        .state('table', {
            url: "/table",
            templateUrl: "partials/state2.list.html",
            controller: function($scope) {
                $scope.things = ["A", "Set", "Of", "Things"];
            }
        });
});