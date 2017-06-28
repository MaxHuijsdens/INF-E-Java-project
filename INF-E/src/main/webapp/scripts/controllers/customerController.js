/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


koekoek.controller('customerController', function($scope, $http, $routeParams, modalService, messageService, customerSupplierService) {
		
	$scope.customers = [];
	$scope.slaByCustomer = false;
	$scope.customerName= "";
	
	$scope.getCustomerList = function(){
		customerSupplierService.getCustomerList().then(function(result){
		    $scope.customers = result.data;
	    });
	};
	
	$scope.getCustomerList();
	
	$scope.saveCustomer = function(customer){
		var promise = customerSupplierService.saveCustomer(customer);
		promise.then(function(res){
			if(res.data.success == true){
				messageService.addSuccess("Klant opgeslagen.");
				$scope.dismissModal();
				$scope.getCustomerList();
				$scope.getSlaList(null);
				$scope.customer = null;		
			}else{
				messageService.addError(res.data.error);
				$scope.getCustomerList();
			}
	    });
	};
	
	$scope.deleteCustomer = function(customerId){
		var promise = customerSupplierService.deleteCustomer(customerId);
		promise.then(function(res){
			if(res.data.success == true){
				messageService.addSuccess("Klant verwijderd");
				$scope.getCustomerList();
			}else{
				messageService.addError(res.data.error);
				$scope.getCustomerList();
			}
	    });
	};

});
