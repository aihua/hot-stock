function BuyStockCtrl($window, $scope, $state, $http, toastr, $mdDialog, BrokerFactory) {
	var that = this;
	that.request = {};
	console.log(that.locals)
	that.stock = that.locals.stock;
	
	function sendBuyRequest() {
		if(!that.stock) {
			toastr.error("Error", "Invlaid Stock");
			return;
		}
		
		if(!that.request.price) {
			toastr.error("Error", "Price is Required");
			return;
		}
		that.request.stockId = that.stock.id;
		BrokerFactory.sendBuyRequest(that.request).success(function(data){
			toastr.success("Success", "Request Sent Successfully");
			that.locals.updateStocks();
			hide();
		}).error(function(data){
			toastr.error("Error", data.error);
		})		
	}
	
	function sendSellOffer() {
		if(!that.stock) {
			toastr.error("Error", "Invlaid Stock");
			return;
		}
		
		if(!that.request.price) {
			toastr.error("Error", "Price is Required");
			return;
		}
		that.request.stockId = that.stock.id;
		BrokerFactory.sendSellOffer(that.request).success(function(data){
			toastr.success("Success", "Request Sent Successfully");
			that.locals.updateMyStocks();
			hide();
		}).error(function(data){
			toastr.error("Error", data.error);
		})	
	}
	
	function hide(){
        $mdDialog.hide();
        if(that.locals.updateMyStocks) {
        	that.locals.updateMyStocks();
        } else {
        	that.locals.updateStocks();
        }        
    }

    function cancel() {
        $mdDialog.cancel();
        if(that.locals.updateMyStocks) {
        	that.locals.updateMyStocks();
        } else {
        	that.locals.updateStocks();
        }
    }
	
	angular.extend(this, {
		sendBuyRequest: sendBuyRequest,
		sendSellOffer: sendSellOffer,
		hide: hide,
		cancel: cancel
	});
}
angular
.module('stockApp')
.controller('BuyStockCtrl', BuyStockCtrl);