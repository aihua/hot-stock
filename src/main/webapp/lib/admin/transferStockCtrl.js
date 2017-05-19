function TransferStockCtrl($http, $rootScope, toastr, $mdDialog, AdminFactory, $scope) {
    var that = this ;
    that.modelTitle = that.locals.modelTitle;
    that.stock = that.locals.stock;
    function hide(){
        $mdDialog.hide();
        that.locals.updateStocks();
    }

    function cancel() {
        $mdDialog.cancel();
		that.locals.updateStocks();
    }
    function transferStock() {
    	if(!that.transferStockForm.$valid) {
    		toastr.error("Error", "Please fill all the required fields.");
    		return;
    	}
    	
    	if(that.selectedBroker == null || !that.selectedBroker) {
    		toastr.error("Error", "Please Select Broker from list.");
    		return;
    	}
    	
    	AdminFactory.transferStock(that.stock.id, that.selectedBroker.id).success(function(data){
    		toastr.success("Success", "Stock Transferred Successfully.");
    		hide();
    	}).error(function(data){
    		toastr.error("Error", data.error);
    	});
    }
    
    function searchBrokers(username) {
    	if(username == null || username == undefined) {
    		username = '';
    	}
    	return AdminFactory.searchBrokers(username).then(function(data){
    		return data.data;
    	})
    }
    
    angular.extend(this, {
    	transferStock: transferStock,
    	searchBrokers: searchBrokers,
    	hide: hide,
    	cancel: cancel
    });
}

angular
    .module('stockApp')
    .controller('TransferStockCtrl', TransferStockCtrl);