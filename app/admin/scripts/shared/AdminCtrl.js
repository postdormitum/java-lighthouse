var adminModule = angular.module("admin.controllers", []);
adminModule.controller('AdminCtrl', function($scope, $http, $state, $stateParams) {
	$scope.main = {
		brand: 'Lighthouse',
        name: 'Porry Chen' // those which uses i18n can not be replaced for now.
	}    
});