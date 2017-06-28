koekoek.directive('determineRowColor', function() {
    return function(scope, element, attrs) {
	switch(attrs.status){
	    case "WARN":
		scope.rowColor = "warning";
		break;
	    case "STALE":
		scope.rowColor = "warning";
		break;
	    case "ERROR":
		scope.rowColor = "danger";
		break;
	    default:
		scope.rowColor = "";
		break;
	}
    };
});