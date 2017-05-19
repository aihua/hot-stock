function AdminCtrl($window, $scope, $state, $http, $mdDialog, AdminFactory) {
	var that = this;
	that.stocks = [];
	that.trades = [];
	that.activeTabIndex = 0;
	
	this.query = {
	        order: 'nameToLower',
	        limit: 100,
	        page: 1
	    };
	function onPaginate(page, limit) {
        angular.extend({}, that.query, {page: page, limit: limit});
    }

    function onReorder(order) {
        angular.extend({}, that.query, {order: order});
    }
    
    function showAddStockModal(ev, modelTitle, data) {    	
        $mdDialog.show({
            controller: 'AddStockCtrl',
            controllerAs: 'addStockObj',
            templateUrl: 'lib/admin/add-stock.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose:false,
            locals: {
            	updateStocks: updateStocks,
            	modelTitle: modelTitle
            },
            bindToController: true
        });
    }
    
    function showTransferStockModal(ev, modelTitle, data) {    	
        $mdDialog.show({
            controller: 'TransferStockCtrl',
            controllerAs: 'transferStockObj',
            templateUrl: 'lib/admin/transfer-stock.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose:false,
            locals: {
            	stock: data,
            	updateStocks: updateStocks,
            	modelTitle: modelTitle
            },
            bindToController: true
        });
    }
    
    function allStocks() {
    	AdminFactory.getAllStocks().success(function(data){
    		that.stocks = data;
    	}).error(function(data){
    		toastr.error("Error", data.error);
    	})
    }
    
    function tradesForTheDay() {
    	AdminFactory.tradesForTheDay().success(function(data){
    		that.trades = data;
    	}).error(function(data){
    		toastr.error("Error", data.error);
    	})
    }
    
    function updateStocks() {
    	allStocks();
    }
    
    function updateTrades() {
    	tradesForTheDay();
    }
    
    function init() {
    	tradesForTheDay();
    }
    init();

	angular.extend(this, {
		$state: $state,
		showAddStockModal: showAddStockModal,
		showTransferStockModal: showTransferStockModal,
		updateStocks: updateStocks,
		updateTrades: updateTrades
	});
}
angular
.module('stockApp')
.controller('AdminCtrl', AdminCtrl);