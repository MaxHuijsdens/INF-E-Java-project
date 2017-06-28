koekoek.directive('autoFocus', function ($timeout) {
    return {
	scope: {
	    trigger: '@autoFocus'
	},
	link: function (scope, element) {
	    scope.$watch('trigger', function (value) {
		console.log('focus to element ' + element[0].id + " " +value );
		if (value == 'true') {
		    $timeout(function () {
			console.log('giving focus to element', element[0].id);
			element[0].focus();
		    });
		}
	    });
	}
    };
});