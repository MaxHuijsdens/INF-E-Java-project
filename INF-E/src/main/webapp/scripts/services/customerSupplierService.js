koekoek.service('customerSupplierService', function($http, $q){	
	
	this.saveCustomer = function(customer){	
		if(!customer.id){
			return $http.post('customer', angular.toJson(customer));
		}else{
			return $http.post('customer/' +  customer.id, angular.toJson(customer));
		}
	};
	
	this.deleteCustomer = function(customerId){
    	return $http.delete("customer/delete/" + customerId);
    };
    
    this.deleteSupplier = function(supplierId){
    	return $http.delete("supplier/delete/" + supplierId);
    };
    
	this.saveSupplier = function(supplier){	
		if(!supplier.id){
			return $http.post('supplier', angular.toJson(supplier));
		}else{
			return $http.post('supplier/' +  supplier.id, angular.toJson(supplier));
		}
	};
	
	this.saveSla = function(sla){
		if(!sla.id){
			return $http.post('sla', angular.toJson(sla));
		}else{
			return $http.post('sla/' +  sla.id, angular.toJson(sla));
		}
    	
    };
    
    this.deleteSla = function(slaId){
    	return $http.delete("sla/delete/" + slaId);
    };
    
    this.getSlaList = function(){
    	return $http.get('sla/slaList');
    };
    
    this.getSlaListByCustomer = function(customerId){
    	return $http.get('sla/slaListByCustomer/' + customerId);
    };
    
    this.getSupplierList = function(){
    	return $http.get('supplier/supplierList');
    };
    
    this.getCustomerList = function(){
    	return $http.get('customer/customerList');
    };
    
    
    
});
