koekoek.controller('serversOverviewController', ['$scope', 'serverService', '$location', 'customerSupplierService' ,function($scope, serverService, $location, customerSupplierService) {
	$scope.dataList = [];
	$scope.slaList = [];
	$scope.slaFilter = {};
	$scope.page = 'serverInfo';

	$scope.isEmpty = function(object){
		   for(var i in object){ return false;}
		  return true;
	};
	
	$scope.getDataList = function(){
		var promise = null;
		switch($scope.page){
			case 'serverInfo':
				if($scope.isEmpty($scope.slaFilter) || $scope.slaFilter.sla == null){
					promise = serverService.getServerList();
				}else{
					promise = serverService.getServerListBySla($scope.slaFilter.sla.id);
				}
				
				break;
			case 'statusInfo':
				if($scope.isEmpty($scope.slaFilter) || $scope.slaFilter.sla == null){
					promise = serverService.getServerMeasurementList();
				}else{
					promise = serverService.getServerMeasurementListBySla($scope.slaFilter.sla.id);
				}
				
				break;
			case 'versionInfo':
				if($scope.isEmpty($scope.slaFilter) || $scope.slaFilter.sla == null){
					promise = serverService.getServerVersionList();
				}else{
					promise = serverService.getServerVersionListBySla($scope.slaFilter.sla.id);
				}
				
				break;
		};
		promise.success(function(data){
			$scope.dataList = data;
	    });
		$scope.$broadcast('timer-start');		
	};
	
	$scope.getSlaList = function(customer){
			customerSupplierService.getSlaList().then(function(result){
				$scope.slaList = result.data;
			});
	};
	
	$scope.getDataList();
	$scope.getSlaList();
	
	$scope.rowClickHandler = function(hostname) {
		$location.path('/info/' + hostname);
	};
	
	$scope.fixedTableHeaders = function(tableId){
		var $table = $("#" + tableId);
		$table.floatThead({
			useAbsolutePositioning: true
		});
		$table.floatThead('reflow');
	};
		
	$scope.setPage = function(page){
		$scope.page = page;
		$scope.getDataList();
		angular.element("html input").focus();
	};
		
}]);