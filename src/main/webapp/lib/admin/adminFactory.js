/**
 * author: Jogireddy Kotam
 * date: 18-May-2017
 */
function AdminFactory($http) {
    var AdminFactory = {};
    
    AdminFactory.getAllStocks = function() {
    	return $http.get("/hot-stock/stocks/all");
    }
    
    AdminFactory.transferStock = function(stockId, userId) {
    	return $http.get("/hot-stock/stocks/transfer/" + stockId + "/" + userId);
    }
    
    AdminFactory.saveStock = function(stock) {
    	return $http.post("/hot-stock/stocks/save", stock);
    }
    
    AdminFactory.searchBrokers = function(username) {
    	return $http.get("/hot-stock/users/brokers?username=" + username);
    }
    
    AdminFactory.tradesForTheDay = function() {
    	return $http.get("/hot-stock/trades/today");
    }
    
	return AdminFactory;	
}
angular
    .module('stockApp')
    .factory('AdminFactory', AdminFactory);