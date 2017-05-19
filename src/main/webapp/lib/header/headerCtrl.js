
function HeaderCtrl($scope, $window, $rootScope, $state, $http,$location,PrinicipalFactory) {
	
	$rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
        if (toState.name == 'login' && $rootScope.authenticated  ) {
        	event.preventDefault();
         }else if(toState.name != 'login' && !$rootScope.authenticated){
         	$state.go('login');
         }
      });
	
	$rootScope.$on('$stateChangeError', function(e, toState, toParams, fromState, fromParams, error){
	    if(error === "Not Authorized"){
	        toastr.error("You do not have permissions to view this page","Access Denied");
	    }
	});
	
	$rootScope.$on('logoutHandler', function(info,message){
		logOut(message);
	})
	
	function logOut(message) {
		PrinicipalFactory.resetPrincipal();
	    $http.post('logout', {})
	    	.success(function() {
			    $rootScope.authenticated = false;
			    sessionStorage.removeItem('isAuthenticated');
			    sessionStorage.removeItem('username');
			    PrinicipalFactory.resetPrincipal();
			    $state.go('login');	    	    
	    	}).error(function(data) {
	    		$rootScope.authenticated = false;
			    PrinicipalFactory.resetPrincipal();
			    sessionStorage.removeItem('isAuthenticated');
			    sessionStorage.removeItem('username');
			    PrinicipalFactory.resetPrincipal();
			    $state.go('login');	
	    	});
	}
	
	angular.extend(this, {
		$state: $state,		
		logOut: logOut
	});
}


angular
	.module('stockApp')
	.controller('HeaderCtrl', HeaderCtrl);