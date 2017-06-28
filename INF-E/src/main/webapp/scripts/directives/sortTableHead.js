/**
 * Sortable table headers with icons, font for icons:FontAwesome.
 */
koekoek.directive('sortTableHead', function() {
    return {
	restrict: 'A',
	transclude: true,
	scope: {
	    order: '=',
	    by: '=',
	    reverse : '='
	},
	template: '<th class="selectable" data-ng-click="onClick()" >' +
	    '<span ng-transclude class="pull-left"></span>' +
	    '<i class="fa fa-sort pull-right" data-ng-class="{\'fa-sort-desc\' : order === by && !reverse,  \'fa-sort-asc\' : order === by && reverse, \'fa-sort\': !order === by}"></i>' +
	    '</th>',
	link: function(scope) {
	    scope.onClick = function() {
		if (scope.order === scope.by) {
		    scope.reverse = !scope.reverse;
		} else {
		    scope.by = scope.order;
		    scope.reverse = false;
		}
	    };
	}
    };
});
