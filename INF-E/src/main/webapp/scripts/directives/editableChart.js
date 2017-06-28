koekoek.directive('editableChart', function() {   	
    return {
	restrict: 'AE',
	template: "<loading></loading><div id=\"chart-container\"></div>",
	link: function(scope, element, attrs) {
	    scope.chart = new Highcharts.StockChart({
		chart: {
		    renderTo: 'chart-container',
		    type: 'areaspline'
		},
		title : {
		    text : "Chart"
		},
		rangeSelector: {
		    enabled: false
		}
	    });	
	}
    };
});
