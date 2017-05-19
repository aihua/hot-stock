function BrokerCtrl($window, $scope, $state, $http, toastr, $mdDialog, BrokerFactory) {
	var that = this;
	that.stocks = [];
	that.myStocks = [];
	that.buyIntents = [];
	that.sellIntents = [];
	
	function updateStocks() {
		getStocks();
	}
	
	function updateMyStocks() {
		getMyStocks();
	}
	
	function updateBuyIntents() {
		getBuyIntents();
	}
	
	function updateSellIntents(){
		getSellIntents();
	}
	
	function getStocks() {
		BrokerFactory.getAllActiveStocks().success(function(data){
			that.stocks = data;
		}).error(function(data){
			toastr.error("Error", data.error);
		})
	}
	
	function getMyStocks() {
		BrokerFactory.getMyStocks().success(function(data){
			that.myStocks = data;
		}).error(function(data){
			toastr.error("Error", data.error);
		})
	}
	
	function getBuyIntents() {
		BrokerFactory.getBuyIntents().success(function(data){
			that.buyIntents = data;
		}).error(function(data){
			toastr.error("Error", data.error);
		})
	}
	
	function accept(intent) {
		BrokerFactory.acceptBuy(intent).success(function(data){
			toastr.success("Success", data.message);
			updateBuyIntents();
		}).error(function(data){
			toastr.error("Error", data.error);
		})
	}
	
	function decline(intent) {
		BrokerFactory.declineBuy(intent).success(function(data){
			toastr.success("Success", data.message);
			updateBuyIntents();
		}).error(function(data){
			toastr.error("Error", data.error);
		})
	}
	
	function getSellIntents() {
		BrokerFactory.getSellIntents().success(function(data){
			that.sellIntents = data;
		}).error(function(data){
			toastr.error("Error", data.error);
		})
	}
	
	function acceptSell(intent) {
		BrokerFactory.acceptSell(intent).success(function(data){
			toastr.success("Success", data.message);
			updateSellIntents();
		}).error(function(data){
			toastr.error("Error", data.error);
		})
	}
	
	function declineSell(intent) {
		BrokerFactory.declineSell(intent).success(function(data){
			toastr.success("Success", data.message);
			updateSellIntents();
		}).error(function(data){
			toastr.error("Error", data.error);
		})
	}
	
	
	setInterval(function(){
		updateStocks();		
	}, 5000)
	
	function init() {
		updateStocks();
	}
	init();
	
	function showBuyStockModal(ev, modelTitle, data) {    	
        $mdDialog.show({
            controller: 'BuyStockCtrl',
            controllerAs: 'buyStockObj',
            templateUrl: 'lib/broker/buyStock.html',
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
	
	function showSellStockModal(ev, modelTitle, data) {    	
        $mdDialog.show({
            controller: 'BuyStockCtrl',
            controllerAs: 'buyStockObj',
            templateUrl: 'lib/broker/sellStock.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose:false,
            locals: {
            	stock: data,
            	updateMyStocks: updateMyStocks,
            	modelTitle: modelTitle
            },
            bindToController: true
        });
    }
	
	angular.extend(this, {
		updateStocks: updateStocks,
		updateMyStocks: updateMyStocks,
		updateBuyIntents: updateBuyIntents,
		updateSellIntents: updateSellIntents,
		showBuyStockModal: showBuyStockModal,
		showSellStockModal: showSellStockModal,
		accept: accept,
		decline: decline,
		acceptSell: acceptSell,
		declineSell: declineSell
	});
}
angular
.module('stockApp')
.controller('BrokerCtrl', BrokerCtrl);