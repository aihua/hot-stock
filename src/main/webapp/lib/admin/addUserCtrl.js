function AddUserCtrl($http, $rootScope, toastr, $mdDialog, AdminFactory, $scope) {
    var that = this ;
    that.modelTitle = that.locals.modelTitle;
    that.user = {};
    function hide(){
        $mdDialog.hide();
        that.locals.updateBrokers();
    }

    function cancel() {
        $mdDialog.cancel();
        that.locals.updateBrokers();
    }
    function save() {
    	if(!that.addUserForm.$valid) {
    		toastr.error("Error", "Please Enter Username.");
    		return;
    	}
    	
    	AdminFactory.saveBroker(that.user).success(function(data){
    		toastr.success("Success", "User Saved Successfully.");
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
    .controller('AddUserCtrl', AddUserCtrl);