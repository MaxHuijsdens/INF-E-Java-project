koekoek.controller('manageController', ['$scope', '$http', '$routeParams', 'messageService', 'userIptableService',
                                           function($scope, $http, $routeParams, messageService, userIptableService) {
	
	$scope.users = [];
	$scope.iptables = [];
	
	$scope.getUserList = function(){
		userIptableService.getUserList().then(function(result){
			$scope.users = result.data;
	    });
	};
	
	$scope.getIptableList = function(){
		userIptableService.getIptableList().then(function(result){
			$scope.iptables = result.data;
	    });
	};

	$scope.getUserList();
	$scope.getIptableList();
	
	$scope.saveUsers = function(){	
		userIptableService.saveUsers($scope.users).then(function(res){
			if(res.data.success == true){
				messageService.addSuccess("Gebruikers opgeslagen.");
			}else{
				messageService.addError(res.data.error);
			}
	    });
	};
	
	$scope.saveIptables = function(){	
		userIptableService.saveIptables($scope.iptables).then(function(res){
			if(res.data.success == true){
				messageService.addSuccess("Iptables opgeslagen.");
			}else{
				messageService.addError(res.data.error);
			}
	    });
	};
}]);
