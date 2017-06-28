koekoek.service('userIptableService', function($http){	
	   
	this.getUserList = function(){
		return $http.get("user/userList");
	};
	
	this.getIptableList = function(){
		return $http.get("iptable/iptableList");
	};
	
	this.saveUsers = function(users){
		return $http.post("user/saveUserList", angular.toJson(users));
	};
	
	this.saveIptables = function(iptables){
		return $http.post("iptable/saveIptableList", angular.toJson(iptables));
	};
});