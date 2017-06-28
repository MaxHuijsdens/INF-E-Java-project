koekoek.controller('HeaderController', ['$scope', '$location', 'loginService', 'messageService',
    function($scope, $location, loginService, messageService) {
		
		$scope.toggleDropdown = function($event) {
	        $event.preventDefault();
	        $scope.status.collapsed = !$scope.status.collapsed;
	    };
    
        $scope.logout = function() {
            loginService.logout(function() {
                messageService.clear();
                $location.path('/');
            });
        };
    }
]);
