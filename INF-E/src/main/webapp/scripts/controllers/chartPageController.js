koekoek.controller('chartPageController', ['$scope', '$http', 'serverService', 'measurementService',  '$routeParams',  
                                           function($scope, $http, serverService, measurementService,  $routeParams) {

	$scope.measurements = [ {
		  label : 'Servercheck',
	        value : 'servcheck'
       },
       {
      	  label : 'N2',
            value : 'n2'
     }
  ];
	$scope.dateUntil = null;
	$scope.dateFrom = moment().subtract("hour", 1).format('YYYY-MM-DDTHH:mm');
	$scope.measurementSource = "servcheck";
	$scope.chartData = [];
	$scope.metrics = measurementService.getServercheckCharts();
	$scope.selectedServer;
	$scope.selectedMetric = $scope.metrics[0];
	
	$scope.getServers = function(hostName) {
	    return  serverService.getServerListByHostName(hostName).then(function(res){
		      return res.data;
	    });
	 };
	  
	$scope.addDataToChart = function() {
		var filterName = $scope.selectedServer.hostName + " " + $scope.selectedMetric.name;
		$scope.chartData.push({filterName: filterName, server : $scope.selectedServer, metric :  $scope.selectedMetric});
        var promise = $scope.getChartData();
		promise.then(function(res){
            $scope.loading = true;
            $scope.addSerieToChart(filterName, res.data, $scope.chartData.length);
	    }).success($scope.loading = false);
	};

	$scope.getChartData = function(){
		return measurementService.getChartData($scope.selectedServer.id, $scope.selectedMetric.type, $scope.dateFrom, $scope.dateUntil, $scope.measurementSource);
	};
	
	$scope.addSerieToChart = function(filterName, chartData, colorId){
		$scope.chart.addSeries({
			id: filterName + '-serie',
			name : $scope.measurementSource + " " + filterName,
			data : chartData,
			fillOpacity : 0.25,
			color :  $scope.chart.options.colors[colorId]
		});
		$scope.chart.addAxis({ 
            id: filterName + '-axis',
            title: {
                text: $scope.measurementSource + " " + filterName,
                style : {
					color :  $scope.chart.options.colors[colorId]
				}
            },
            lineWidth: 2,
            lineColor: $scope.chart.options.colors[colorId],
            opposite: false
        });
	};
	
	$scope.changeSource = function(){
		switch($scope.measurementSource){
    	case 'servcheck':
    		$scope.metrics = measurementService.getServercheckCharts();
    		break;
    	case 'n2': 
    		$scope.metrics = measurementService.getN2Charts();
    		break;
		}
	};
	
	$scope.removeFilter = function(filter){
		$scope.chart.get(filter.filterName + '-serie').remove();
		$scope.chart.get(filter.filterName + '-axis').remove();
		
		var index=$scope.chartData.indexOf(filter);
		$scope.chartData.splice(index,1);   
	};
	
	$scope.onTimeSet = function () {
		var newData = $scope.chartData;
		$scope.chartData = [];
		var promises = [];
		angular.forEach(newData, function(value) {
			$scope.chart.get(value.filterName + '-serie').remove();
			$scope.chart.get(value.filterName + '-axis').remove();
			$scope.selectedServer = value.server;
			$scope.selectedMetric = value.metric;
		    promises.push($scope.getChartData());
		    
		    var filterName = $scope.measurementSource + " " + $scope.selectedServer.hostName + " " + $scope.selectedMetric.name;
			$scope.chartData.push({filterName: filterName, server : $scope.selectedServer, metric :  $scope.selectedMetric});
		});
		var i = 0;
		angular.forEach(promises, function(promise) {
			promise.then(function(res){
				$scope.addSerieToChart($scope.chartData[i].filterName, res.data, i);
				i++;
		    });
			
		});
	};
}]);
