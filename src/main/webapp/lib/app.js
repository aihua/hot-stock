var app = angular.module("stockApp", ['ui.router',
                                      'oc.lazyLoad',
                                      'ngMaterial',
                                      'ngAnimate',
                                      'toastr',
                                      'stockApp.broker',
                                      'stockApp.admin']);

angular.module('stockApp')
.config(function ($httpProvider) {
	$httpProvider.interceptors.push(function($q, $rootScope, $timeout) {
    var numberOfHttpRequests = 0;
    return {
        request: function (config) {
            numberOfHttpRequests += 1;
            $rootScope.waitingForHttp = true;
            
            if(sessionStorage.getItem('isAuthenticated')) {
            	$rootScope.authenticated = true;
            	$rootScope.username = sessionStorage.getItem('username');
            }
            return config;
        },
        requestError: function (error) {
            numberOfHttpRequests -= 1;
            $rootScope.waitingForHttp = (numberOfHttpRequests !== 0);
            return $q.reject(error);
        },
        response: function (response) {
            numberOfHttpRequests -= 1;
            $rootScope.waitingForHttp = (numberOfHttpRequests !== 0);
            return response;
        },
        responseError: function (error) {
            numberOfHttpRequests -= 1;
            $rootScope.waitingForHttp = (numberOfHttpRequests !== 0);
            return $q.reject(error);
        }
    };
  });
});

angular
.module('stockApp')
.config(function(toastrConfig, $ocLazyLoadProvider, $locationProvider, $urlRouterProvider, $stateProvider) {
	angular.extend(toastrConfig, {
        allowHtml: false,
        closeButton: true,
        closeHtml: '<button>&times;</button>',
        extendedTimeOut: 1000,
        iconClasses: {
            error: 'toast-error',
            info: 'toast-info',
            success: 'toast-success',
            warning: 'toast-warning'
        },
        messageClass: 'toast-message',
        onHidden: null,
        onShown: null,
        onTap: null,
        progressBar: false,
        tapToDismiss: true,
        templates: {
            toast: 'directives/toast/toast.html',
            progressbar: 'directives/progressbar/progressbar.html'
        },
        preventDuplicates: false,
        preventOpenDuplicates: true,
        timeOut: 5000,
        titleClass: 'toast-title',
        toastClass: 'toast'
    });
    $locationProvider.html5Mode(true);//.hashPrefix('!');	// This line is commented because, for html5 mode url rewriting for redirecting to entry point of the application has to be done on serverside.

    $urlRouterProvider
        .otherwise('/');

    $stateProvider
        .state('login', {
            url: '/',
            templateUrl: 'lib/login/login.html',
            controller: 'LoginCtrl',
            controllerAs: 'loginObj',
            resolve: {
                fwDependencies: function($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        'lib/login/loginCtrl.js',
                        'lib/common-factory/info-factory.js'
                    ]);
                }
            }
        })
        /*
        $stateProvider
        .state('root', {
            url: '/',
            templateUrl: 'lib/login/login.html',
            controller: 'LoginCtrl',
            controllerAs: 'loginObj',
            resolve: {
                fwDependencies: function($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        'lib/login/loginCtrl.js',
                        'lib/common-factory/info-factory.js'
                    ]);
                }
            }
        })*/
});

function ParentCtrl($document, $state) {
    var that = this;  

    angular.extend(this, {
    	$state: $state
    });
}

angular
    .module('stockApp')
    .controller('ParentCtrl', ParentCtrl);
