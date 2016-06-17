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

var myApp=angular.module('myApp',['ngLoadScript','ui.router','oc.lazyLoad','ui.bootstrap']);

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