koekoek.controller('loginController', ['$scope', '$location', 'loginService', 'messageService', function($scope, $location, loginService, messageService) {
	
	$scope.login = function() {
		loginService.login(this.username, this.password, function() {
			messageService.clear();
			$location.path('/activitystream');
		});
	};
}]);