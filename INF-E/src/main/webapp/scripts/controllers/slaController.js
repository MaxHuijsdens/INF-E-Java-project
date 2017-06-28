/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
koekoek.controller('slaController', function($scope, $http, $routeParams, modalService, messageService, customerSupplierService) {
	
	$scope.slas = [];
	$scope.slaByCustomer = false;
	$scope.customerName= "";
	
	$scope.getSlaList = function(customer){
		if(customer){
			customerSupplierService.getSlaListByCustomer(customer.id).then(function(result){
				$scope.slas = result.data;
			});
			$scope.customerName = customer.name;
			$scope.slaByCustomer = true;
		}else{
			customerSupplierService.getSlaList().then(function(result){
				$scope.slas = result.data;
			});
			$scope.slaByCustomer = false;
		};
	};
	
	$scope.getSlaList(null);
	
	$scope.deleteSla = function(slaId){
		var promise = customerSupplierService.deleteSla(slaId);
		promise.then(function(res){
			if(res.data.success == true){
				messageService.addSuccess("SLA verwijderd");
				$scope.getSlaList();
			}else{
				messageService.addError(res.data.error);
				$scope.getSlaList();
			}
	    });
	};
	
	$scope.saveSla = function(sla){
		var promise = customerSupplierService.saveSla(sla);
		promise.then(function(res){
			if(res.data.success == true){
				messageService.addSuccess("SLA opgeslagen");
				$scope.dismissModal();
				$scope.getSlaList();
				$scope.sla = null;		
			}else{
				messageService.addError(res.data.error);
				$scope.getSlaList();
			}
	    });		
	};

});

