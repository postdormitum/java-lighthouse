(function() {
  'use strict';
  angular.module('app', ['app.ui.directives']).controller('DashboardController', [
    '$scope', function($scope) {
      $scope.processData = {
        "gap": 160,
        "nodes": [
          {
            "type": "ok",
            "label": "开始",
            "message": ""
          }, {
            "type": "ok",
            "label": "报修",
            "message": "报修日期："
          }, {
            "type": "wait",
            "label": "分单",
            "message": "分单日期："
          }, [
            [
              {
                "type": "ok",
                "label": "派工",
                "message": ""
              }, {
                "type": "ok",
                "label": "填报",
                "message": ""
              }, {
                "type": "ok",
                "label": "确认",
                "message": ""
              }
            ], [
              {
                "type": "ok",
                "label": "派工",
                "message": ""
              }, {
                "type": "wait",
                "label": "填报",
                "message": ""
              }, {
                "type": "will",
                "label": "确认",
                "rollback": 1,
                "message": ""
              }
            ], [
              {
                "type": "will",
                "label": "派工",
                "rollback": 1,
                "message": ""
              }, {
                "type": "will",
                "label": "填报",
                "message": ""
              }, {
                "type": "will",
                "label": "确认",
                "message": ""
              }
            ]
          ], {
            "type": "will",
            "label": "结束",
            "message": ""
          }
        ]
      };
      return $scope.message = '';
    }
  ]);

}).call(this);