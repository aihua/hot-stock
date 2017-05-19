angular.module('stockApp.admin',[ ]);

angular.module('stockApp.admin')
    .config(function($ocLazyLoadProvider, $stateProvider) {
        $stateProvider           
            .state('admin', {
                url: '/admin',
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
            .state('admin.home', {
                url: '/dashboard',
                templateUrl: 'lib/admin/admin.html',
                controller: 'AdminCtrl',
                controllerAs: 'adminObj',
                resolve: {
                    fwDependencies: function($ocLazyLoad) {
                        return $ocLazyLoad.load([
                         'lib/common-factory/info-factory.js',
                         'lib/admin/adminCtrl.js',
                         'lib/admin/addStockCtrl.js',
                         'lib/admin/transferStockCtrl.js',
                         'lib/admin/adminFactory.js'
                        ]);
                    }
                }
            })
    });
