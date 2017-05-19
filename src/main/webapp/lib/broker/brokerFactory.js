/**
 * author: Jogireddy Kotam
 * date: 18-May-2017
 */
function BrokerFactory($http) {
    var BrokerFactory = {};
    
    BrokerFactory.getAllActiveStocks = function() {
    	return $http.get("/hot-stock/stocks/all/active");
    }
    
    BrokerFactory.getMyStocks = function() {
    	return $http.get("/hot-stock/stocks/user");
    }
    
    BrokerFactory.sendBuyRequest = function(data) {
    	return $http.post("/hot-stock/trades/buy-request", data);
    }
    
    BrokerFactory.sendSellOffer = function(data) {
    	return $http.post("/hot-stock/trades/sell-offer", data);
    }
    
    BrokerFactory.getBuyIntents = function() {
    	return $http.get("/hot-stock/trades/buy/intents");
    }
    
    BrokerFactory.getSellIntents = function() {
    	return $http.get("/hot-stock/trades/sell/intents");
    }
    
    BrokerFactory.acceptBuy = function(intent) {
    	return $http.get("/hot-stock/trades/buy/accept/" + intent.id);
    }
    
    BrokerFactory.declineBuy = function(intent) {
    	return $http.get("/hot-stock/trades/buy/decline/" + intent.id);
    }
    
    BrokerFactory.acceptSell = function(intent) {
    	return $http.get("/hot-stock/trades/sell/accept/" + intent.id);
    }
    
    BrokerFactory.declineSell = function(intent) {
    	return $http.get("/hot-stock/trades/sell/decline/" + intent.id);
    }
    
    return BrokerFactory;	
}
angular
    .module('stockApp')
    .factory('BrokerFactory', BrokerFactory);