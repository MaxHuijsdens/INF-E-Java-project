koekoek.controller('serverInfoController', ['$scope', '$http', '$cookies', 'serverService', 'measurementService',  '$routeParams', 'customerSupplierService', 'modalService', 'loginService', 'messageService',
                                            function($scope, $http, $cookies, serverService, measurementService,  $routeParams, customerSupplierService, 
                                            		modalService, loginService, messageService) {
	$scope.views = [ {
        label : 'Info',
           value : 'info'
         },
         {
        label : 'Metrieken',
        value : 'metrics'
       }
    ];
	$scope.measurements = [ {
		  label : 'Servercheck',
	        value : 'servcheck'
         },
         {
        	  label : 'N2',
              value : 'n2'
       }
    ];
	$scope.currentView = 'info';
	$scope.measurementSource = "servcheck";
	$scope.dateUntil = moment().format('YYYY-MM-DDTHH:mm');;
	$scope.dateFrom = moment().subtract("hour", 1).format('YYYY-MM-DDTHH:mm');
	$scope.noteDate = null;
	$scope.serverId = $routeParams.serverId;
        
        
	$scope.showVersionHistory = ($cookies.showVersionHistory === "true");
	$scope.showUserHistory = ($cookies.showUserHistory === "true");
	$scope.showKnownUsers = ($cookies.showKnownUsers === "true");
	$scope.showKnownIptables = ($cookies.showKnownIptables === "true");
       
	$scope.getServer = function(){
		serverService.getServer($scope.serverId).success(function(data){
			$scope.server = data;
	    });
	};
	
	$scope.getCustomerList = function(){
		customerSupplierService.getCustomerList().then(function(result){
			$scope.customers = result.data;
	    });
	};
	
	$scope.getSupplierList = function(){
		customerSupplierService.getSupplierList().then(function(result){
			$scope.suppliers = result.data;
	    });
	};
	
	$scope.getSlaList = function(){
		customerSupplierService.getSlaList().then(function(result){
			$scope.slas = result.data;
	    });
	};
	
	$scope.getServerVersions = function(){
		serverService.getServerVersions($scope.serverId, $scope.showVersionHistory).then(function(result){
			$scope.serverVersions = result.data;
	    });
	};
	
	$scope.getServerUsers = function(){
		if ($scope.showUserHistory){
			serverService.getServerUserChange($scope.serverId, $scope.showKnownUsers).then(function(result){
				$scope.serverUsers = result.data;
		    });
		}else{
			serverService.getServerUsers($scope.serverId, $scope.showKnownUsers).then(function(result){
				$scope.serverUsers = result.data;
		    });
		}	
	};
	
	$scope.getServerNotes = function(){
		serverService.getServerNotes($scope.serverId).then(function(result){
			$scope.notes = result.data;
		});
	};
	
	$scope.getServerIptables = function(){
		serverService.getServerIptables($scope.serverId, $scope.showKnownIptables).then(function(result){
			$scope.iptables = result.data;
		});
	};
	
	$scope.getServer();
	$scope.getServerNotes();
	$scope.getCustomerList();
	$scope.getSupplierList();
	$scope.getSlaList();
	$scope.getServerVersions();
	$scope.getServerUsers();
	$scope.getServerIptables();

	$scope.updateServer = function(validForm){
		if(validForm){
			serverService.updateServer($scope.server).success(function (result) {
				messageService.addSuccess("Server succesvol geupdate.");
		    }).error(function (result, status) {
		    	messageService.addError("Er is een fout opgetreden bij het updates van de server. Status: " + status);
		    });
		}else{
			messageService.addError("Formulier is niet valide.");
		}
		
		
	};

	$scope.getChartData = function(){
		var promises = [];
		$scope.chartData = [];
		switch($scope.measurementSource){
    	case 'servcheck':
    		$scope.charts = measurementService.getServercheckCharts();
    		break;
    	case 'n2': 
    		$scope.charts = measurementService.getN2Charts();
    		break;
		}
		angular.forEach($scope.charts, function(chart) {
			promises[chart.type] =  measurementService.getChartData($scope.serverId, chart.type, 
					$scope.dateFrom, $scope.dateUntil, $scope.measurementSource);
		});
		$scope.chartData = promises;
	};
	
	$scope.saveNote = function (note, incidentDate){
		note.reporter = loginService.user.name;
		note.server = $scope.server;
		note.incidentDate = moment(incidentDate).format('YYYYMMDD-HHmm'), 
		serverService.saveNote(note).then(function(res){
			if(res.data.success == true){
				messageService.addSuccess("Note opgeslagen.");				
				$scope.dismissModal();
				$scope.getServerNotes();
			}else{
				messageService.addError(res.data.error);
				$scope.getServerNotes();
			}
	    });
	};
	
	$scope.deleteNote = function (noteId){
			var promise = serverService.deleteNote(noteId);
			promise.then(function(res){
				if(res.data.success == true){
					messageService.addSuccess("Notitie verwijderd");
					$scope.getServerNotes();
				}else{
					messageService.addError(res.data.error);
					$scope.getServerNotes();
				}
		    });
	};
	
	$scope.openModal = function(object){
		$scope.note = object;
		if(object != null){
		$scope.noteDate = object.incidentDate;
		}
		modalService.openModal("lg", "note", $scope);
	};
	
	$scope.dismissModal = function() {
		$scope.noteDate = moment().format('YYYY-MM-DDTHH:mm');
		modalService.dismissModal();
	};
	
	$scope.resetNoteOrder = function(){
		$scope.orderNote = 'creationDate';  
		$scope.reverseNote = true;
	};
	
	$scope.resetNoteOrder();
	
	$scope.toggleSwitchCheckbox = function(property) {
            
        $scope[property] = $scope[property] === false;
        $cookies[property] = $scope[property];
        switch(property){
        	case 'showVersionHistory':
        		$scope.getServerVersions();
        		break;
        	case 'showUserHistory': case 'showKnownUsers':
        		$scope.getServerUsers();
        		break;
        	case 'showKnownIptables':
        		$scope.getServerIptables();
        		break;
        };
    };
    
    $scope.toggleSwitch = function(){
    	if($scope.currentView == 'metrics'){
    		$scope.getChartData();
    	};
    };
}]);

