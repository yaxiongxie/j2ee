angular.module("myApp").controller("statistics", ['$scope', '$uibModal', '$http', 'toaster', 'confirmDialog', 'uploadFiles', function($scope, $uibModal, $http, toaster, confirmDialog, uploadFiles){

    $scope.myDataSource = {
        chart: {
            caption: "销售统计",
            subCaption: "",
            numberPrefix: "¥",
            theme: "ocean"
        },
        data: [{
            label: "项目一",
            value: "880000"
        }, {
            label: "项目二",
            value: "730000"
        }, {
            label: "项目三",
            value: "590000"
        }, {
            label: "项目四",
            value: "520000"
        }, {
            label: "项目五",
            value: "330000"
        }]
    };
    
    $scope.myDataSource2 = {
        "chart": {
            "caption": "月份统计",
            "subcaption": "去年",
            "xaxisname": "月份",
            "yaxisname": "金额",
            "numberprefix": "¥",
            "theme": "fint"
        },
        "categories": [{
            "category": [{
                "label": "一月"
            }, {
                "label": "二月"
            }, {
                "label": "三月"
            }, {
                "label": "四月"
            }, {
                "label": "五月"
            }, {
                "label": "六月"
            }, {
                "label": "七月"
            }, {
                "label": "八月"
            }, {
                "label": "九月"
            }, {
                "label": "十月"
            }, {
                "label": "十一月"
            }, {
                "label": "十二月"
            }]
        }],
        "dataset": [{
            "seriesname": "项目一",
            "data": [{
                "value": "16000"
            }, {
                "value": "20000"
            }, {
                "value": "18000"
            }, {
                "value": "19000"
            }, {
                "value": "15000"
            }, {
                "value": "21000"
            }, {
                "value": "16000"
            }, {
                "value": "20000"
            }, {
                "value": "17000"
            }, {
                "value": "25000"
            }, {
                "value": "19000"
            }, {
                "value": "23000"
            }]
        }, {
            "seriesname": "项目二",
            "renderas": "line",
            "showvalues": "0",
            "data": [{
                "value": "15000"
            }, {
                "value": "16000"
            }, {
                "value": "17000"
            }, {
                "value": "18000"
            }, {
                "value": "19000"
            }, {
                "value": "19000"
            }, {
                "value": "19000"
            }, {
                "value": "19000"
            }, {
                "value": "20000"
            }, {
                "value": "21000"
            }, {
                "value": "22000"
            }, {
                "value": "23000"
            }]
        }, {
            "seriesname": "项目三",
            "renderas": "area",
            "showvalues": "0",
            "data": [{
                "value": "4000"
            }, {
                "value": "5000"
            }, {
                "value": "3000"
            }, {
                "value": "4000"
            }, {
                "value": "1000"
            }, {
                "value": "7000"
            }, {
                "value": "1000"
            }, {
                "value": "4000"
            }, {
                "value": "1000"
            }, {
                "value": "8000"
            }, {
                "value": "2000"
            }, {
                "value": "7000"
            }]
        }]
    }
    
    $scope.myDataSource3 = {
        "chart": {
            "caption": "销量统计",
            "subCaption": "",
            "xAxisName": "项目",
            "yAxisName": "金额",
            "numberPrefix": "¥",
            "paletteColors": "#0075c2,#1aaf5d,#f2c500",
            "bgColor": "#ffffff",
            "showBorder": "0",
            "showCanvasBorder": "0",
            "usePlotGradientColor": "0",
            "plotBorderAlpha": "10",
            "legendBorderAlpha": "0",
            "legendBgAlpha": "0",
            "legendShadow": "0",
            "showHoverEffect": "1",
            "valueFontColor": "#ffffff",
            "rotateValues": "1",
            "placeValuesInside": "1",
            "divlineColor": "#999999",
            "divLineDashed": "1",
            "divLineDashLen": "1",
            "canvasBgColor": "#ffffff",
            "captionFontSize": "14",
            "subcaptionFontSize": "14",
            "subcaptionFontBold": "0"
        },
        "categories": [{
            "category": [{
                "label": "项目一"
            }, {
                "label": "项目二"
            }, {
                "label": "项目三"
            }, {
                "label": "项目四"
            }]
        }],
        "dataset": [{
            "seriesname": "去年",
            "data": [{
                "value": "10000"
            }, {
                "value": "11500"
            }, {
                "value": "12500"
            }, {
                "value": "15000"
            }]
        }, {
            "seriesname": "今年",
            "data": [{
                "value": "25400"
            }, {
                "value": "29800"
            }, {
                "value": "21800"
            }, {
                "value": "26800"
            }]
        }]
    }
    
    
    $scope.myDataSource4 = {
        "chart": {
            "caption": "上一周访问量",
            "subCaption": "",
            "captionFontSize": "14",
            "subcaptionFontSize": "14",
            "subcaptionFontBold": "0",
            "paletteColors": "#0075c2,#1aaf5d",
            "bgcolor": "#ffffff",
            "showBorder": "0",
            "showShadow": "0",
            "showCanvasBorder": "0",
            "usePlotGradientColor": "0",
            "legendBorderAlpha": "0",
            "legendShadow": "0",
            "showAxisLines": "0",
            "showAlternateHGridColor": "0",
            "divlineThickness": "1",
            "divLineDashed": "1",
            "divLineDashLen": "1",
            "xAxisName": "",
            "showValues": "0"
        },
        "categories": [{
            "category": [{
                "label": "星期一"
            }, {
                "label": "星期二"
            }, {
                "label": "星期三"
            }, {
                "vline": "true",
                "lineposition": "0",
                "color": "#6baa01",
                "labelHAlign": "center",
                "labelPosition": "0",
                "label": "放假",
                "dashed": "1"
            }, {
                "label": "星期四"
            }, {
                "label": "星期五"
            }, {
                "label": "星期六"
            }, {
                "label": "星期日"
            }]
        }],
        "dataset": [{
            "seriesname": "android",
            "data": [{
                "value": "15123"
            }, {
                "value": "14233"
            }, {
                "value": "25507"
            }, {
                "value": "9110"
            }, {
                "value": "15529"
            }, {
                "value": "20803"
            }, {
                "value": "19202"
            }]
        }, {
            "seriesname": "ios",
            "data": [{
                "value": "13400"
            }, {
                "value": "12800"
            }, {
                "value": "22800"
            }, {
                "value": "12400"
            }, {
                "value": "15800"
            }, {
                "value": "19800"
            }, {
                "value": "21800"
            }]
        }],
        "trendlines": [{
            "line": [{
                "startvalue": "17022",
                "color": "#6baa01",
                "valueOnRight": "1",
                "displayvalue": "平均"
            }]
        }]
    }
    
}
]);
