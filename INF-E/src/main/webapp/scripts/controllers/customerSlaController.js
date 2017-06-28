//not used anymore

koekoek.controller('customerSlaController', function($controller, $scope, $http, $routeParams, modalService, messageService, customerSupplierService) {
	
	$scope.openModal = function(modalType, object, customer){
	    switch (modalType) {
	       case 'customer':
		  $scope.customer = object;
		  break;
	       case 'sla':
		  if(object == null){
		      $scope.sla = {};
		      $scope.sla.customer = customer;
		  }else{
		      $scope.sla = object;
		  }								 
		  break;
	    }
	    modalService.openModal("lg", modalType, $scope);
	};
	
	$scope.dismissModal = function() {
		modalService.dismissModal();
	};
    
	var locals = {
	    $scope: $scope, 
	    $http: $http, 
	    $routeParams: $routeParams, 
	    modalService: modalService, 
	    messageService: messageService, 
	    customerSupplierService: customerSupplierService
	};
	
	$controller('customerController', locals);
	$controller('slaController', locals);
});
