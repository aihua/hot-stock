function LoginCtrl($state, $timeout,$http,$rootScope, toastr, $mdDialog,$document,$location,PrinicipalFactory,$window,$scope) {
    var that = this ;

    if($rootScope.authenticated   || sessionStorage.getItem('isAuthenticated')){
        $rootScope.authenticated = true;
        $rootScope.loggedInUser =  PrinicipalFactory.getUserName();
    }
    

   $rootScope.fromHash;
	if($document[0] && $document[0].location && $rootScope.isUnAuthenticatedRedirect){
		$rootScope.fromHash = $document[0].location.hash.substr(1);
		$rootScope.isUnAuthenticatedRedirect = false;
	}
	
	$rootScope.$on('loginHandler', function(event, data){
		processLogin(data.credentials);
	})
	
	function validateFormOnSubmit(loginFormData) {
        if(this.loginForm.$invalid) {
        	toastr.error('Enter valid username and password!', 'Error');
        }else{
        	processLogin(loginFormData);
        }
    }
	
	
	function processLogin(loginFormData) {
		var authenticate = function(loginFormData, callback) {
    		var credentials = {username :loginFormData.username}; 
    		var state;
    		$http.post('login', $.param(credentials), {
    		      headers : {
    		        "content-type" : "application/x-www-form-urlencoded"
    		      }
    		    }).success(function(data, status, headers, config) { 
    		    	state = data.state;
    	            $http.get('user', $.param(credentials), {
    	    		      headers : {
    	    		        "content-type" : "application/x-www-form-urlencoded"
    	    		      }
    	    		    }).success(function(data) {
    	                  if (data.name) {
    	                	PrinicipalFactory.setPrincipal(data);
    	              		sessionStorage.setItem("username",  PrinicipalFactory.getUserName());
    	              		$rootScope.authenticated = true;
    	              		$rootScope.username = PrinicipalFactory.getUserName();
    	              		sessionStorage.setItem("isAuthenticated", true);
    	              		$state.go(state);
                            $scope.$emit('$stateChangeSuccess');
    	      			} else {
    	      				toastr.error("Invalid username/password", 'Error');
    	      				$rootScope.authenticated = false;
    	                  }
    	              }).error(function() {
    	            	  toastr.error("Invalid username/password", 'Error');
    	            	  $rootScope.authenticated = false;
    	              });
    		    }).error(function(data) {
    		    	toastr.error("Invalid username/password", 'Error');
    		    	$rootScope.authenticated = false;
    		    });
        }

        authenticate(loginFormData);
        self.login = function() {
            authenticate(loginFormData, function(authenticated) {
                if (authenticated) {
                    self.error = false;
                    $rootScope.authenticated = true;
                    $state.go('doctor.doctorProfileBuilding.personalInfo');
    				resetLoginForm();
    				//cancel();
                } else {
                    $rootScope.authenticated = false;                    
                    this.loginAlertMessages = {
        					alertClass: 'alert-danger',
        					alertMessage: 'Invalid username.',
        					isVisible: true
        				};
        				var that = this;
        				$timeout(function() {
        					that.loginAlertMessages = {
        						alertClass: '',
        						alertMessage: '',
        						isVisible: false
        					};
        				}, 5000);
                }
            })
        };
	}


    
    angular.extend(this, {
        loginData: {},
        validateFormOnSubmit: validateFormOnSubmit
    });
}

angular
    .module('stockApp')
    .controller('LoginCtrl', LoginCtrl);