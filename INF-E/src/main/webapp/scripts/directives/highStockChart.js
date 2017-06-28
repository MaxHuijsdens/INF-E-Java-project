
/**
 * Sortable table headers with icons, font for icons:FontAwesome.
 */
koekoek.directive('highStockChart', function() {
    // return the directive link function. (compile function not needed)
    return function (scope, element, attrs) {

	var container = attrs.id;
	var chartName = attrs.name;

	// watch the expression, and update the UI on change.
	scope.$watch('chartData', function (newValue, oldValue) {
	    scope.chartData[container].then(function(result) {	
		     drawChart(result.data);
		     addVersionFlags();
		    });
	}, false);
	var chart;
	var drawChart = function (data) {
	    chart = new Highcharts.StockChart({
		chart: {
		    renderTo: container,
		    type: 'areaspline'
		},
		xAxis: {
		    events: {
			afterSetExtremes: function (e) {
			    if(e.DOMEvent !== undefined){
				if(e.DOMEvent.type == 'mouseup'){
				    angular.forEach(scope.charts, function(chart) {
					var chart = $("#" + chart.type).highcharts();
					chart.xAxis[0].setExtremes(e.min, e.max);
				    });
				}
			    }
			}
		    }
		},
		title : {
			text : chartName
		},
		series : [{
		    id : chartName,
		    name : chartName,
		    color: '#8991a6',
		    data : data,
		    tooltip: {
			valueDecimals: 2
		    }
		}],
		rangeSelector: {
		    enabled: false
		}
	    });
	};

	var addVersionFlags = function(){
	    $http.get('server/serverVersions/'+ scope.serverId + '/startDate/' + scope.dateFrom + '/endDate/' + scope.dateUntil ).then(function(results){
		angular.forEach(results.data, function(result) {
		    chart.xAxis[0].addPlotLine({
			//id: chartName,
			width: 2,
			color : '#428bca',
			value: moment(result.timeOfMeasurement).unix() * 1000,
			label:{
				text: result.version.name + " " + result.version.versionNumber
			},
			zIndex: 999999
		    });
		});
	    });
	};
    };
});