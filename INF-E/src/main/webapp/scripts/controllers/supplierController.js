koekoek.controller('supplierController', ['$scope', '$http', '$routeParams', 'modalService', 'messageService', 'customerSupplierService',
                                           function($scope, $http, $routeParams, modalService, messageService, customerSupplierService) {
	$scope.suppliers = [];
	
	$scope.getSupplierList = function(){
		customerSupplierService.getSupplierList().then(function(result){
			$scope.suppliers = result.data;
	    });
	};

	$scope.getSupplierList();
	
	$scope.openModal = function(supplier){
		$scope.supplier = supplier;
		modalService.openModal("lg", "supplier", $scope);
	};
	
	$scope.dismissModal = function(){
		modalService.dismissModal();
		$scope.supplier = null;
	};
	
	$scope.deleteSupplier = function(supplierId){
		var promise = customerSupplierService.deleteSupplier(supplierId);
		promise.then(function(res){
			if(res.data.success == true){
				messageService.addSuccess("Leverancier verwijderd");
				$scope.getSupplierList();
			}else{
				messageService.addError(res.data.error);
				$scope.getSupplierList();
			}
	    });
	};
	
	$scope.saveSupplier = function(supplierName){
		var promise = customerSupplierService.saveSupplier(supplierName);
		
		promise.then(function(res){
			if(res.data.success == true){
				messageService.addSuccess("Leverancier opgeslagen.");
				$scope.dismissModal();
				$scope.getSupplierList();
			}else{
				messageService.addError(res.data.error);
				$scope.getSupplierList();
			}
	    });
		
	};
}]);
