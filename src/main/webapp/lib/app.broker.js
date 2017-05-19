angular.module('stockApp.broker',[ ]);

angular.module('stockApp.broker')
    .config(function($ocLazyLoadProvider, $stateProvider) {
        $stateProvider           
            .state('broker', {
                url: '/broker',
                abstract: true,
                template: '<div ui-view></div>',
                resolve: {
                    fwDependencies: function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                         'lib/common-factory/info-factory.js'
                        ]);
                    }
                }
            })
            .state('broker.home', {
                url: '/dashboard',
                templateUrl: 'lib/broker/broker.html',
                controller: 'BrokerCtrl',
                controllerAs: 'brokerObj',
                resolve: {
                    fwDependencies: function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                         'lib/common-factory/info-factory.js',
                         'lib/broker/brokerCtrl.js',
                         'lib/broker/brokerFactory.js',
                         'lib/broker/buyStockCtrl.js'
                        ]);
                    }
                }
            })
    });
