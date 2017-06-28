koekoek.factory('modalService', function($modal) {
	var instance = {
			modal: {}
	};

	instance.openModal = function(size, modalType, scope){
		instance.modal.modalInstance = $modal.open({
		      templateUrl: 'views/directive/' + modalType + 'Modal.html',
		      size: size,
		      scope: scope,
		    });
	};
	
	instance.dismissModal = function() {
		instance.modal.modalInstance.close();
	};

	return instance;
});
