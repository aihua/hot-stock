/**
 * author: Jogireddy Kotam
 * date: 17-May-2017
 */
function PrinicipalFactory($http, $rootScope) {
    var PrinicipalFactory = {};
    
    PrinicipalFactory.loginData = null;

    PrinicipalFactory.setPrincipal = function(data) {
		if (!data || data == "") {
			$state.go('login');
			$rootScope.isUnAuthenticatedRedirect = true;
		} else {
			PrinicipalFactory.loginData = data;
			$rootScope.authenticated = true;
		}
	}

    PrinicipalFactory.resetPrincipal = function(data){
    	PrinicipalFactory.loginData = null;
    }
    
    PrinicipalFactory.getUserName = function(){
    	if(PrinicipalFactory.loginData == undefined || PrinicipalFactory.loginData == null){
    		var data = PrinicipalFactory.getPrincipalObject();
        	if(data!=undefined){
        			PrinicipalFactory.setPrincipal(data);
        			return PrinicipalFactory.loginData.principal.username;
        	}
    	}
    	else {
	    	if(PrinicipalFactory.loginData != undefined && 
	    			PrinicipalFactory.loginData.principal != null){
	    		return PrinicipalFactory.loginData.principal;
	    	}
    	}
    }    
    
    PrinicipalFactory.getPrincipalObject = function(){
    	var userData;
    	$http.get('user', function(data){
    		userData = data;
    	});
    	return userData;
    }
    
	return PrinicipalFactory;	
}
angular
    .module('stockApp')
    .factory('PrinicipalFactory', PrinicipalFactory);