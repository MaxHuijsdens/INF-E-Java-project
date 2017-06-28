koekoek.directive('replaceValues', function() {
    return {
	restrict: 'AE',
	transclude: true,
	scope: {
	    possibleValues: '=',
	    returnValues: '=',
	    value: '@'
	},
	link: function(scope, element, attrs) {
	    for(var i = 0; i < scope.possibleValues.length; i++){
		if(scope.value == scope.possibleValues[i]){
		    var template = '<div>' + scope.returnValues[i] + '</div>';
		    element.html(template);
		};
	    };
	}
    };
});
