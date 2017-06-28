koekoek.service('measurementService', function($http){	
	var measurementTypes = [{ type: "load", name: "Load", measurementType : '' },
	   					 { type: 'networkIn', name: 'Network in',  measurementType : '' }, 
						 { type:'networkOut', name: 'Network out',  measurementType : '' },
						 { type:'diskIo', name:'Disk IO',measurementType : '' }, 
						 { type: 'roundtripTime', name: 'Round-trip time', measurementType : '' },
						 { type:'ioWait', name:'IO Wait', measurementType :'' },
						 { type:'freeRam',name: 'Free RAM', measurementType :'' },
						 { type:'freeSwap', name:'Free Swap', measurementType :'' },
						 { type:'processesRunning',name: 'Number of processes',measurementType : '' }];
	
	var servercheckMeasurementTypes = [{ type: "cpuUsage", name: "CPU usage", measurementType : '' },
	          	   					 { type: 'diskUsage', name: 'Disk usage',  measurementType : '' }, 
	        						 { type:'totalMemory', name: 'Total memory',  measurementType : '' },
	        						 { type:'inodeUsage', name:'Inode usage',measurementType : '' }];
	
	var n2MeasurementTypes = [{ type: "cpuUsagePercent", name: "CPU usage", measurementType : '' },
	 	   					 { type: 'diskUsagePercent', name: 'Disk usage',  measurementType : '' }];	 
	
	this.getServercheckCharts = function(){
		return measurementTypes.concat(servercheckMeasurementTypes);
	};
	
	
	this.getN2Charts = function(){
		return measurementTypes.concat(n2MeasurementTypes);
	};
	
	this.getChartData = function(serverId, chartType, begin, end, source){	
        return $http.get('measurement/server/'+ serverId + '/graph/'+ chartType +'/start/' + begin + '/end/' + end + '/source/' + source);
	};
});