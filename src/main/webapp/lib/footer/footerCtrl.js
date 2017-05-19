function FooterCtrl($window, $scope, $state, $http) {
	var that = this;

	angular.extend(this, {
		$state: $state,
	});
}
angular
.module('stockApp')
.controller('FooterCtrl', FooterCtrl);