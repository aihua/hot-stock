function AddStockCtrl($http, $rootScope, toastr, $mdDialog, AdminFactory, $scope) {
    var that = this ;
    that.modelTitle = that.locals.modelTitle;
    console.log(that.locals)
    that.stock = {};
    function hide(){
        $mdDialog.hide();
        that.locals.updateStocks();
    }

    function cancel() {
        $mdDialog.cancel();
		that.locals.updateStocks();
    }
    function save() {
    	if(!that.addStockForm.$valid) {
    		toastr.error("Error", "Please fill all the required fields.");
    		return;
    	}
    	
    	AdminFactory.saveStock(that.stock).success(function(data){
    		toastr.success("Success", "Stock Saved Successfully.");
    		hide();
    	}).error(function(data){
    		toastr.error("Error", data.error);
    	});
    }
    
    angular.extend(this, {
    	save: save,
    	hide: hide,
    	cancel: cancel
    });
}

angular
    .module('stockApp')
    .controller('AddStockCtrl', AddStockCtrl);