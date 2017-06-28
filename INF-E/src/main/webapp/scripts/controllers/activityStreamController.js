koekoek.controller('activityStreamController', ['$scope', '$http', '$routeParams', '$location', 'serverService',
                                           function($scope, $http, $routeParams, $location, serverService) {
	$scope.notes = [];
	
	$scope.getNoteList = function(){
		serverService.getNoteList().then(function(result){
			$scope.notes = result.data;
	    });
	};
	
	$scope.getNoteList();
	
	$scope.goToInfo = function(id) {
		$location.path('/info/' + id);
	};
	
	$scope.fixedTableHeaders = function(tableId){
		var $table = $("#" + tableId);
		$table.floatThead({
			useAbsolutePositioning: true
		});
		$table.floatThead('reflow');
	};
}]);
